package com.abc.retailstore.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bill {

  private User user;
  private double totalAmount;
  private double groceryAmount;
}
