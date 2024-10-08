package org.example.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tshirts")
public class Tshirt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shirt_id")
    private Long tshirtId;

    @Column (name = "shirtname")
    private String tshirtName;
    @Column (name = "shirtsize")
    private String tshirtSize;
    @Column (name = "price")
    private Long price;

    public Long getTshirtId() {
        return tshirtId;
    }

    public void setTshirtId(Long tshirtId) {
        this.tshirtId = tshirtId;
    }

    public String getTshirtName() {
        return tshirtName;
    }

    public void setTshirtName(String tshirtName) {
        this.tshirtName = tshirtName;
    }

    public String getTshirtSize() {
        return tshirtSize;
    }

    public void setTshirtSize(String tshirtSize) {
        this.tshirtSize = tshirtSize;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

}
