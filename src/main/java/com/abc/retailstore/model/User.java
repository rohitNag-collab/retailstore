package com.abc.retailstore.model;

import java.time.LocalDate;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {

  private int userId;
  private String firstName;
  private String lastName;
  private boolean isEmployee;
  private boolean isAffiliate;
  private LocalDate createDate;
}
