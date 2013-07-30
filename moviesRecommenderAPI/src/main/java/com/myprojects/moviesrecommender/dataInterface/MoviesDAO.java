/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojects.moviesrecommender.dataInterface;

import com.myprojects.moviesrecommender.baseClasses.movie;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Srividya.K
 */
public interface MoviesDAO {
    
    public void setDataSource(DataSource ds);
    
    public movie getMovie(String movieId);
    
    public List<movie> getAllMovies();
}
