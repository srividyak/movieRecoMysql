/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojects.moviesrecommender.mysql;

import com.myprojects.moviesrecommender.baseClasses.tags;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Srividya.K
 */
public class tagsMapper implements RowMapper<tags>{

    public tags mapRow(ResultSet rs, int rowNum) throws SQLException {
        String movieId = rs.getString("movieid");
        String userId = rs.getString("userid");
        String tag = rs.getString("tag");
        long timestamp = rs.getLong("timestamp");
        return new tags(tag, userId, movieId, timestamp);
    }
    
}
