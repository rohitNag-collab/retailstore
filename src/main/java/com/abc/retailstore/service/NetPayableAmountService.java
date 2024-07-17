package com.abc.retailstore.service;

import com.abc.retailstore.model.Bill;
import com.abc.retailstore.model.User;
import java.time.LocalDate;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class NetPayableAmountService implements INetPayableAmountService {

  @Override
  public double calculateNetPayableAmount(Bill bill) {

    User user = bill.getUser();
    boolean isCustomerMoreThan2YearsOld =
        user.getCreateDate().isBefore(LocalDate.now().minusYears(2));

    double totalAmount = bill.getTotalAmount();
    double groceryAmount = bill.getGroceryAmount();
    double nonGroceryAmount = totalAmount - groceryAmount;

    double fixedDiscount = calculateFixedDiscount(totalAmount);
    double percentageDiscount =
        calculatePercentageDiscount(bill, nonGroceryAmount, isCustomerMoreThan2YearsOld);

    return totalAmount - fixedDiscount - percentageDiscount;
  }

  private double calculatePercentageDiscount(
      Bill bill, double nonGroceryAmount, boolean isCustomerMoreThan2YearsOld) {
    if (bill.getUser().isEmployee()) {
      return nonGroceryAmount * 0.30;
    } else if (bill.getUser().isAffiliate()) {
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
