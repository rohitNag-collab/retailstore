package com.abc.retailstore.model;

import lombok.*;

import java.time.LocalDate;

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
