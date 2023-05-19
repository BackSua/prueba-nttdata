package com.prueba.crudtaks.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "taks")
public class TaksEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taks_id")
    private Long id;
    @Column(name = "taks_name", nullable = false, length = 250)
    private String name;
    @Column(name = "taks_description", nullable = false)
    private String description;
    @Column(name = "taks_status", nullable = false)
    private String status;
    @Column(name= "taks_initial_date")
    private Date initialDate;
    @Column(name= "taks_final_date")
    private Date finalDate;
    @Column(name= "task_user_id", nullable = false)
    private Long userId;
}
