package com.abc.retailstore.model;

import java.time.LocalDate;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

  private String firstName;
  private String lastName;
  private boolean isEmployee;
  private boolean isAffiliate;
  private LocalDate createDate;
}
