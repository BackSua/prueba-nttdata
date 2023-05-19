package com.prueba.cruduser.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "user_name", nullable = false, length = 250)
    private String name;
    @Column(name = "user_last_name", nullable = false, length = 250)
    private String lastName;
    @Column(name = "user_email")
    private String email;
    @Column(name = "user_username", nullable = false, length = 150)
    private String userName;
    @Column(name ="user_password", nullable = false, length = 10)
    private String password;
    @Column(name= "user_regis_date")
    private Date registrationDate;
}
