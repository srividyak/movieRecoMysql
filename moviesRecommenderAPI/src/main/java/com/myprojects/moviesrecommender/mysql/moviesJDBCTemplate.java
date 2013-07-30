/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojects.moviesrecommender.mysql;

import com.myprojects.moviesrecommender.baseClasses.movie;
import com.myprojects.moviesrecommender.dataInterface.MoviesDAO;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Srividya.K
 */
public class moviesJDBCTemplate extends dataSourceTemplate implements MoviesDAO {

    public movie getMovie(String movieId) {
        movie m = null;
        String sql = "select * from movies where movieid=?";
        m = jdbcTemplateObject.queryForObject(sql, new Object[]{movieId}, new movieMapper());
        return m;
    }

    public List<movie> getAllMovies() {
        String sql = "select * from movies";
        List<movie> movies = jdbcTemplateObject.query(sql, new movieMapper());
        return movies;
    }
    
    public List<movie> getAllMovies(long offset, int count) {
        String sql = "select * from movies limit " + offset + "," + count;
        List<movie> movies = jdbcTemplateObject.query(sql, new movieMapper());
        return movies;
    }
    
    public long getMoviesCount() {
        String sql = "select count(*) from movies";
        Long count = jdbcTemplateObject.queryForObject(sql, Long.class);
        return count.longValue();
    }
    
}
