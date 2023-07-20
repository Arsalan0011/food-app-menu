package com.foodmenuapp.service;

import com.foodmenuapp.model.Food;
import com.foodmenuapp.reponse.MenuResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class FoodService {

    public MenuResponse uploadExcelFile(File file, String foodType) {
        int count = 0;
        List<Food> foodMenuList = new ArrayList<>();

        try {
        InputStream inputStream =new FileInputStream(file);



        Workbook workbook = new XSSFWorkbook(inputStream) ;
            Sheet sheet;
            if (foodType !=null && foodType.equals("lunch")) {
               sheet  = workbook.getSheetAt(0);
            }
            else if(foodType !=null && foodType.equals("dinner")){
                 sheet = workbook.getSheetAt(1);
            }
            else{
                return new MenuResponse(foodMenuList,"invalid food type");
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue; // Skip empty rows
                }
                try {
                    Food food = new Food();
                    food.setDate(row.getCell(0).getDateCellValue());
                    food.setDay(row.getCell(1).getStringCellValue());
                    food.setMainDish(row.getCell(2).getStringCellValue());
                    food.setSideDish(row.getCell(3).getStringCellValue());
                    food.setSweet(row.getCell(4).getStringCellValue());
                    food.setColdDrink(row.getCell(5).getStringCellValue());
                    food.setFruit(row.getCell(6).getStringCellValue());
                    food.setSpecialDays(row.getCell(7).getStringCellValue());
                    food.setRegularSalad(row.getCell(8).getStringCellValue());

                    foodMenuList.add(food);
                    count++;
                } catch (Exception e) {
                    System.err.println("Error processing row " + (i + 1) + ": " + e.getMessage());
                }
            }

            System.out.println("Processed " + count + " rows.");

        } catch (IOException e) {
            System.err.println("Error reading Excel file: " + e.getMessage());
        }

        return new MenuResponse(foodMenuList,"success");
    }
}