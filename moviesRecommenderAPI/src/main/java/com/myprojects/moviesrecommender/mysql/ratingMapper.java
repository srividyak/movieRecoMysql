/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojects.moviesrecommender.mysql;

import com.myprojects.moviesrecommender.baseClasses.rating;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Srividya.K
 */
public class ratingMapper implements RowMapper<rating>{

    public rating mapRow(ResultSet rs, int rowNum) throws SQLException {
        String userId = rs.getString("userid");
        String movieId = rs.getString("movieid");
        float rating = rs.getFloat("rating");
        long timestamp = rs.getLong("timestamp");
        return new rating(userId, movieId, rating, timestamp);
    }
    
}
