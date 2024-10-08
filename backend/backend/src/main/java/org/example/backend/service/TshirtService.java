package org.example.backend.service;


import org.example.backend.model.Tshirt;
import org.example.backend.repository.TshirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TshirtService {

    @Autowired
    private TshirtRepository tshirtRepository;

    public TshirtService(TshirtRepository tshirtRepository) {
        this.tshirtRepository = tshirtRepository;
    }

    public void addTshirt(Tshirt tshirt) {
        tshirtRepository.addTshirt(tshirt);
    }

    public void deleteTshirt(Long tshirtId) {
        tshirtRepository.deleteTshirt(tshirtId);
    }

    public Tshirt updateTshirt(Tshirt tshirt) {
        return tshirtRepository.updateTshirt(tshirt);
    }

    public Tshirt getTshirtById(Long tshirtId) {
        return tshirtRepository.getTshirtByID(tshirtId);
    }

    public List<Tshirt> getAllTshirts() {
        return tshirtRepository.getAllTshirts();
    }

}
