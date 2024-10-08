package org.example.backend.controller;

import org.example.backend.model.Booking;
import org.example.backend.model.Snack;
import org.example.backend.service.BookingService;
import org.example.backend.service.SnackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/snack")
public class SnackController {

    @Autowired
    private SnackService snackService;



    @GetMapping
    public List<Snack> getAllSnacks() {
        return snackService.getAllSnacks();
    }



    @GetMapping("/{id}")
    public ResponseEntity<Snack> getSnackByiD(@PathVariable Long id) {
        Snack snack = snackService.getSnackById(id);
        return ResponseEntity.ok(snack);
    }


    @PostMapping("/add")
    public ResponseEntity<String> addSnack(@RequestBody Snack snack){
        snackService.addSnack(snack);
        return ResponseEntity.status(HttpStatus.CREATED).body("Snack added successfully");
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSnack(@PathVariable Long id){
        snackService.deleteSnack(id);
        return ResponseEntity.ok("Snack deleted successfully");
    }



    @PostMapping("/{id}")
    public ResponseEntity<String> updateSnack(@PathVariable Long id, @RequestBody Snack newSnack) {
        Snack oldSnack = snackService.getSnackById(id);

        if (oldSnack == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Snack not found");
        }


        // Only update fields that are not null
        if (newSnack.getSnackName() != null) {
            oldSnack.setSnackName(newSnack.getSnackName());
        }
        if (newSnack.getSnackSize() != null) {
            oldSnack.setSnackSize(newSnack.getSnackSize());
        }

        if (newSnack.getPrice() != null) {
            oldSnack.setPrice(newSnack.getPrice());
        }


        snackService.updateSnack(oldSnack);
        return ResponseEntity.ok("Snack updated");

    }









}
