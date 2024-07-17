package com.abc.retailstore.service;

import com.abc.retailstore.model.Bill;
import com.abc.retailstore.model.Category;
import com.abc.retailstore.model.Product;
import com.abc.retailstore.model.User;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

  private static final Map<Integer, Bill> billMap = new HashMap<>();
  private static final Map<Integer, User> userMap = new HashMap<>();

  public static Bill getBill(Integer billId) {
    if (billMap.isEmpty()) {

      billMap.put(
          1, Bill.builder().billID(1).userId(1).totalAmount(12000).products(getProducts()).build());
    }
    return billMap.get(billId);
  }

  public static List<Product> getProducts() {
    Product kiwi =
        Product.builder()
            .productId(1)
            .productName("Kiwi")
            .productCategory(Category.GROCERY)
            .rate(1000)
            .build();

    Product shirt =
        Product.builder()
            .productId(1)
            .productName("USPolo")
            .productCategory(Category.CLOTHES)
            .rate(10000)
            .build();

    Product trunk =
        Product.builder()
            .productId(1)
            .productName("Jockey")
            .productCategory(Category.CLOTHES)
            .rate(1000)
            .build();

    return List.of(kiwi, shirt, trunk);
  }

  public static User getUser(Integer userId) {

    if (userMap.isEmpty()) {
      userMap.put(
          1,
          User.builder()
              .userId(1)
              .createDate(LocalDate.parse("2023-02-23"))
              .firstName("Rohit")
              .lastName("Rawat")
              .isAffiliate(false)
              .isEmployee(true)
              .build());
    }
    return userMap.get(userId);
  }
}
