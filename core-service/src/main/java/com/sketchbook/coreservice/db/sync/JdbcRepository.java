package com.sketchbook.coreservice.db.sync;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String findCompanyNameById(Integer id) {
        String sql = "select name from team_management.company where id = " + id + ";";
        List<String> names = jdbcTemplate.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString(1);
            }
        });
        return names.get(0);
    }
}
