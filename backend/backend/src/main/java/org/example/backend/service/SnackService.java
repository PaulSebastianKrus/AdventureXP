package org.example.backend.service;

import org.example.backend.model.Snack;
import org.example.backend.repository.BookingRepository;
import org.example.backend.repository.SnackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SnackService {



    @Autowired
    private SnackRepository snackRepository;

    public SnackService(SnackRepository snackRepository) {
        this.snackRepository = snackRepository;
    }

    public Snack addSnack(Snack snack) {
        if (!snackRepository.isSnackExists(snack.getSnackName(), snack.getSnackSize())) {
            snackRepository.addSnack(snack);
            return snack;
        }
        return null;
    }



    public void deleteSnack(Long snackId) {
        snackRepository.deleteSnack(snackId);
    }

    public List<Snack> getAllSnacks() {
        return snackRepository.getAllSnacks();
    }



    public Snack getSnackById(Long snackId) {
        return snackRepository.getSnackById(snackId);
    }

    public Snack updateSnack(Snack snack) {
        return snackRepository.updateSnack(snack);
    }














}
