package org.example.backend.repository;

import org.example.backend.model.Snack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SnackRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public boolean isSnackExists(String snackName, String snackSize) {
        String query = "SELECT COUNT(*) FROM snacks WHERE snackname = ? AND snacksize = ?";
        Integer count = jdbcTemplate.queryForObject(query, new Object[]{snackName, snackSize}, Integer.class);
        return count != null && count > 0;
    }

    public void addSnack(Snack snack) {
        String insertQuery = "INSERT INTO snacks (snackname, snacksize, price) VALUES (?, ?, ?)";
        jdbcTemplate.update(insertQuery, snack.getSnackName(), snack.getSnackSize(), snack.getPrice());
    }

    public void deleteSnack(Long snackId) {
        String deleteQuery = "DELETE FROM snacks WHERE snack_id = ?";
        jdbcTemplate.update(deleteQuery, snackId);
    }

    public List<Snack> getAllSnacks() {
        String query = "SELECT * FROM snacks";
        return jdbcTemplate.query(query, (rs, rowNum) -> {
            Snack snack = new Snack();
            snack.setSnackId(rs.getLong("snack_id"));
            snack.setSnackName(rs.getString("snackname"));
            snack.setSnackSize(rs.getString("snacksize"));
            snack.setPrice(rs.getLong("price"));
            return snack;
        });
    }
    public Snack getSnackById(Long id) {
        String query = "SELECT * FROM snacks WHERE snack_id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, (rs, rowNum) -> {
            Snack snack = new Snack();
            snack.setSnackId(rs.getLong("snack_id"));
            snack.setSnackName(rs.getString("snackname"));
            snack.setSnackSize(rs.getString("snacksize"));
            snack.setPrice(rs.getLong("price"));
            return snack;
        });
    }

    public Snack updateSnack(Snack snack) {
        StringBuilder query = new StringBuilder("UPDATE snacks SET ");
        List<Object> parameters = new ArrayList<>();

        if (snack.getSnackName() != null) {
            query.append("snackname = ?, ");
            parameters.add(snack.getSnackName());
        }
        if (snack.getSnackSize() != null) {
            query.append("snacksize = ?, ");
            parameters.add(snack.getSnackSize());
        }
        if (snack.getPrice() != null) {
            query.append("price = ?, ");
            parameters.add(snack.getPrice());
        }

        if (!parameters.isEmpty()) {
            query.setLength(query.length() - 2); // Remove last comma and space
            query.append(" WHERE snack_id = ?");
            parameters.add(snack.getSnackId());

            int rowsUpdated = jdbcTemplate.update(query.toString(), parameters.toArray());
            if (rowsUpdated > 0) {
                return snack;
            }
        }
        return null;
    }


}
