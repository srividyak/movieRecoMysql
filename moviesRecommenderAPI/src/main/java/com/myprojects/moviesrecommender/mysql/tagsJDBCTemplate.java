/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojects.moviesrecommender.mysql;

import com.myprojects.moviesrecommender.dataInterface.TagsDAO;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Srividya.K
 */
public class tagsJDBCTemplate extends dataSourceTemplate implements TagsDAO {
    
    public com.myprojects.moviesrecommender.baseClasses.tags getTagsOfMovieByUser(String userId, String movieId) {
        String sql = "select * from tags where userid=? and movieid=?";
        return jdbcTemplateObject.queryForObject(sql, new Object[]{userId, movieId}, new tagsMapper());
    }

    public List<com.myprojects.moviesrecommender.baseClasses.tags> getTagsOfMovie(String movieId) {
        String sql = "select * from tags where movieid=?";
        return jdbcTemplateObject.query(sql, new Object[]{movieId}, new tagsMapper());
    }

    public List<com.myprojects.moviesrecommender.baseClasses.tags> getTagsByUser(String userid) {
        String sql = "select * from tags where userid=?";
        return jdbcTemplateObject.query(sql, new Object[]{userid}, new tagsMapper());
    }
    
}
