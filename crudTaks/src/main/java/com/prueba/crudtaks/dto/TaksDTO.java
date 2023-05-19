package com.prueba.crudtaks.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TaksDTO {
    String name;
    String description;
    Long userId;
}
