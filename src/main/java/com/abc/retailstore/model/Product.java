package com.abc.retailstore.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Product {

  private int productId;
  private String productName;
  private double rate;
  private Category productCategory;
}
