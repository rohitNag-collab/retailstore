package com.abc.retailstore.service;

import com.abc.retailstore.exception.BillNotFoundException;
import com.abc.retailstore.model.Bill;
import com.abc.retailstore.model.Category;
import com.abc.retailstore.model.Product;
import com.abc.retailstore.model.User;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class NetPayableAmountService implements INetPayableAmountService {

  private static final Logger log = LoggerFactory.getLogger(NetPayableAmountService.class);

  @Override
  public double calculateNetPayableAmount(Integer billId) throws BillNotFoundException {

    Bill billDetails = Utils.getBill(billId);

    if (billDetails == null) {
      throw new BillNotFoundException("Bill not found, contact support : support@retail");
    }
    User userDetails = Utils.getUser(billDetails.getUserId());

    log.info("User Details : {}", userDetails);
    log.info("Bill Details : {}", billDetails);
    boolean isCustomerMoreThan2YearsOld =
        userDetails.getCreateDate().isBefore(LocalDate.now().minusYears(2));

    double totalAmount = billDetails.getTotalAmount();
    double groceryAmount =
        billDetails.getProducts().stream()
            .filter(product -> Category.GROCERY.equals(product.getProductCategory()))
            .map(Product::getRate)
            .reduce(0.00, Double::sum);
    double nonGroceryAmount = totalAmount - groceryAmount;

    double fixedDiscount = calculateFixedDiscount(totalAmount);
    double percentageDiscount =
        calculatePercentageDiscount(nonGroceryAmount, isCustomerMoreThan2YearsOld, userDetails);

    log.info(
        "Bill ID : {}, user ID : {}, userName : {}, fixedDiscount : {}, percentageDiscount : {}",
        billId,
        userDetails.getUserId(),
        userDetails.getFirstName(),
        fixedDiscount,
        percentageDiscount);

    return totalAmount - fixedDiscount - percentageDiscount;
  }

  private double calculatePercentageDiscount(
      double nonGroceryAmount, boolean isCustomerMoreThan2YearsOld, User user) {
    if (user.isEmployee()) {
      return nonGroceryAmount * 0.30;
    } else if (user.isAffiliate()) {
      return nonGroceryAmount * 0.10;
    } else if (isCustomerMoreThan2YearsOld) {
      return nonGroceryAmount * 0.05;
    }
    return 0;
  }

  private double calculateFixedDiscount(double totalAmount) {
    return (totalAmount / 100) * 5;
  }
}
