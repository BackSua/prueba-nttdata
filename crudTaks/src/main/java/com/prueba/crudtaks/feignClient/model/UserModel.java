package com.prueba.crudtaks.feignClient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private String name;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private Date registrationDate;
}
