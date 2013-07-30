/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojects.moviesrecommender.mysql;

import com.myprojects.moviesrecommender.baseClasses.movie;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Srividya.K
 */
public class movieMapper implements RowMapper<movie>{

    public movie mapRow(ResultSet rs, int rowNum) throws SQLException {
        String movieId = rs.getString("movieid");
        String title = rs.getString("title");
        String genres = rs.getString("genre");
        String[] genreArr;
        int[] genreInt;
        int len = genres.length();
        if(len > 2) {
            genres = genres.substring(1, len - 1);
            genreArr = genres.split(",");
            int index = 0;
            genreInt = new int[genreArr.length];
            for(String genre : genreArr) {
                genreInt[index++] = Integer.parseInt(genre.trim());
            }
        } else {
            genreInt = new int[0];
        }
        return new movie(movieId, title, genreInt);
    }
    
}
