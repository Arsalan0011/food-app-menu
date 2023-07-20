package com.foodmenuapp.controller;

import com.foodmenuapp.reponse.MenuResponse;
import com.foodmenuapp.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@RestController
public class FoodController {
    @Autowired
    private FoodService foodService;

    @GetMapping("/getFoodMenu")
    public ResponseEntity<MenuResponse> getFoodMenu(@RequestParam("foodType") String foodType) {
        try {
            String defaultLocation = "/Users/aamin/Desktop/food-menu-app/food-july.xlsx";
            File file = new File(defaultLocation);
            MenuResponse response = foodService.uploadExcelFile(file,foodType);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
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
