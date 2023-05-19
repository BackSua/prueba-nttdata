package com.prueba.crudtaks.feignClient.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoModel {

    String name;
    String lastName;
    String email;
    String userName;
}
