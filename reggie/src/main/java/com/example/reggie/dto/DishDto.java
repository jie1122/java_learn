package com.example.reggie.dto;

import com.example.reggie.domain.Dish;
import com.example.reggie.domain.DishFlavor;
import lombok.Data;

import java.util.List;

@Data
public class DishDto extends Dish {
    private List<DishFlavor> flavors;

    private String categoryName;

    private Integer copies;
}