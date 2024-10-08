package org.example.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "snacks")
public class Snack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "snack_id")
    private Long snackId;


    @Column(name = "snackname")
    private String snackName;

    @Column(name = "snacksize")
    private String snackSize;

    @Column(name = "price")
    private Long price;


    public Long getSnackId() {
        return snackId;
    }
    public void setSnackId(Long snackId) {
        this.snackId = snackId;
    }
    public String getSnackName() {
        return snackName;
    }
    public void setSnackName(String snackName) {
        this.snackName = snackName;
    }
    public String getSnackSize() {
        return snackSize;
    }
    public void setSnackSize(String snackSize) {
        this.snackSize = snackSize;
    }
    public Long getPrice() {
        return price;
    }
    public void setPrice(Long price) {
        this.price = price;
    }










}















