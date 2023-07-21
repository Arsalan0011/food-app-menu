package com.foodmenuapp.controller;

import com.foodmenuapp.model.Food;
import com.foodmenuapp.reponse.MenuResponse;
import com.foodmenuapp.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@RestController
public class FoodController {
    @Autowired
    private FoodService foodService;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/getFoodMenu")
    public ResponseEntity<MenuResponse> getFoodMenu(@RequestParam("foodType") String foodType) {
        try {
            TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
            String defaultLocation = "/Users/aamin/Downloads/food-app-menu-main/food-july.xlsx";
            File file = new File(defaultLocation);
            MenuResponse response = foodService.getFoodMenu(file,foodType);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new MenuResponse(null,"Some exception occured"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    @GetMapping("/getFoodMenu")
//    public String getFoodMenu(@RequestParam("foodType") String foodType, Model model) {
//        try {
//            // Assuming you have the logic to fetch menu items based on the foodType
//            String defaultLocation = "/Users/aamin/Downloads/food-app-menu-main/food-july.xlsx";
//            File file = new File(defaultLocation);
//            List<Food> menuItems = foodService.uploadExcelFile(file,foodType);
//            model.addAttribute("menuItems", menuItems);
//            return "index"; // This will return the same template with the populated table.
//        } catch (Exception e) {
//            // Handle exception if needed
//            return "error"; // Return an error page
//        }
//    }
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/searchMenuByDate")
    public ResponseEntity<MenuResponse> searchMenuByDate(
            @RequestParam("foodType") String foodType   ,
            @RequestParam("date")String inputDate)
    {
        try{
            String defaultLocation = "/Users/aamin/Downloads/food-app-menu-main/food-july.xlsx";
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(inputDate);
            File file = new File(defaultLocation);
            MenuResponse response= foodService.searchMenuByDate(file,date,foodType);
            return new ResponseEntity<>(response, HttpStatus.OK);
            }
        catch (Exception e) {
        return new ResponseEntity<>(new MenuResponse(null,"Some exception occured"),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        try {
            String filename = file.getOriginalFilename();
            String filePath = Paths.get("").toAbsolutePath().toString() + File.separator + filename;
            File dest = new File(filePath);
            file.transferTo(dest);

            return ResponseEntity.ok("File uploaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload the file.");
        }
    }
}
