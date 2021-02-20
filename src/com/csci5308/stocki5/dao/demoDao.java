package com.csci5308.stocki5.dao;

import com.csci5308.stocki5.model.demo;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


public class demoDao implements IDemoDao {
    private JdbcTemplate jdbcTemplate;

    public demoDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public demo getActor(int id) {
        return null;
    }
}
