/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojects.moviesrecommender.mysql;

import com.myprojects.moviesrecommender.baseClasses.rating;
import com.myprojects.moviesrecommender.dataInterface.RatingDAO;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Srividya.K
 */
public class ratingJDBCTemplate extends dataSourceTemplate implements RatingDAO {
    
    public List<rating> getRatingOfMovie(String movieId) {
        String sql = "select * from rating where movieid=?";
        return jdbcTemplateObject.query(sql, new Object[]{movieId}, new ratingMapper());
    }

    public List<rating> getRatingByUser(String userId) {
        String sql = "select * from rating where userid=?";
        return jdbcTemplateObject.query(sql, new Object[]{userId}, new ratingMapper());
    }

    public rating getRatingOfMovieByUser(String userId, String movieId) {
        String sql = "select * from rating where userid=? and movieid=?";
        return jdbcTemplateObject.queryForObject(sql, new Object[]{userId, movieId}, new ratingMapper());
    }
    
}
