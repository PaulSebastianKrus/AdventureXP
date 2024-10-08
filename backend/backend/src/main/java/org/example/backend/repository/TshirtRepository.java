package org.example.backend.repository;

import org.example.backend.model.Booking;
import org.example.backend.model.Tshirt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TshirtRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void addTshirt(Tshirt tshirt) {
    String query = "INSERT INTO tshirts (shirtname, shirtsize, price) VALUES (?, ?, ?)";
    jdbcTemplate.update(query, tshirt.getTshirtName(), tshirt.getTshirtSize(), tshirt.getPrice());
    }

    public void deleteTshirt(Long tshirtId) {
        String query = "DELETE FROM tshirts WHERE shirt_id = ?";
        jdbcTemplate.update(query, tshirtId);
    }

    public Tshirt updateTshirt(Tshirt tshirt) {
        StringBuilder query = new StringBuilder("UPDATE tshirts SET ");
        List<Object> parameters = new ArrayList<>();

        if (tshirt.getTshirtName() != null) {
            query.append("activityname = ?, ");
            parameters.add(tshirt.getTshirtName());
        }
        if (tshirt.getTshirtSize() != null) {
            query.append("date = ?, ");
            parameters.add(tshirt.getTshirtSize());
        }

        if (tshirt.getPrice() != null) {
            query.append("people = ?, ");
            parameters.add(tshirt.getPrice());
        }

        if (!parameters.isEmpty()) {
            query.setLength(query.length() - 2); // Remove last comma and space
            query.append(" WHERE shirt_id = ?");
            parameters.add(tshirt.getTshirtId());

            int rowsUpdated = jdbcTemplate.update(query.toString(), parameters.toArray());
            if (rowsUpdated > 0) {
                return tshirt;
            }
        }
        return null;
    }


    public List<Tshirt> getAllTshirts() {
        String query = "SELECT * FROM tshirts";
        return jdbcTemplate.query(query, (rs, rowNum) -> {
            Tshirt tshirt = new Tshirt();
            tshirt.setTshirtId(rs.getLong("shirt_id"));
            tshirt.setTshirtName(rs.getString("shirtname"));
            tshirt.setTshirtSize(rs.getString("shirtsize"));
            tshirt.setPrice(rs.getLong("price"));
            return tshirt;
        });
    }
    
    public Tshirt getTshirtByID(Long id) {
        String query = "SELECT * FROM tshirts WHERE shirt_id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, (rs, rowNum) -> {
            Tshirt tshirt = new Tshirt();
            tshirt.setTshirtId(rs.getLong("shirt_id"));
            tshirt.setTshirtName(rs.getString("shirtname"));
            tshirt.setTshirtSize(rs.getString("shirtsize"));
            tshirt.setPrice(rs.getLong("price"));
            return tshirt;
        });
    }



}
