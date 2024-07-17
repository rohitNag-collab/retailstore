package com.abc.retailstore.model;

import java.util.List;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Bill {

  private int billID;
  private int userId;
  private double totalAmount;
  private List<Product> products;
}
