package org.example.backend.controller;

import org.example.backend.model.Booking;
import org.example.backend.model.Tshirt;
import org.example.backend.service.TshirtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("api/tshirt")
public class TshirtController {

    @Autowired
    private TshirtService tshirtService;


    @GetMapping
    public List<Tshirt> getAllTshirts() {
        return tshirtService.getAllTshirts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tshirt> getTshirtById(@PathVariable Long id) {
        Tshirt tshirt = tshirtService.getTshirtById(id);
        if (tshirt == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(tshirt);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addTshirt(@RequestBody Tshirt tshirt){
        tshirtService.addTshirt(tshirt);
        return ResponseEntity.status(201).body("Tshirt added successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTshirt(@PathVariable Long id){
        tshirtService.deleteTshirt(id);
        return ResponseEntity.ok("Tshirt deleted successfully");
    }


    @PostMapping("/{id}")
        public ResponseEntity<String> updateTshirt(@PathVariable Long id, @RequestBody Tshirt newTshirt) {
            Tshirt oldTshirt = tshirtService.getTshirtById(id);

            if (oldTshirt == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tshirt not found");
            }


            // Only update fields that are not null
            if (newTshirt.getTshirtName() != null) {
                oldTshirt.setTshirtName(newTshirt.getTshirtName());
            }
            if (newTshirt.getTshirtSize() != null) {
                oldTshirt.setTshirtSize(newTshirt.getTshirtSize());
            }

            if (newTshirt.getPrice() != null) {
                oldTshirt.setPrice(newTshirt.getPrice());
            }

            tshirtService.updateTshirt(oldTshirt);
            return ResponseEntity.ok("Tshirt updated");

        }


}
