package com.foodmenuapp.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Food {
    private Date date;
    private String day;
    private  String mainDish;
    private String sideDish;
    private String sweet;
    private String coldDrink;
    private String fruit;
    private String specialDays;
    private String regularSalad;
}
