package com.foodmenuapp.reponse;

import com.foodmenuapp.model.Food;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class    MenuResponse {
    private List<Food> foodList=new ArrayList<>();
    private String message;
}
