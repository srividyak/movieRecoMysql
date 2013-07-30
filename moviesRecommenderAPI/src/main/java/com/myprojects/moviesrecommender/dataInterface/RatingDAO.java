/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojects.moviesrecommender.dataInterface;

import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Srividya.K
 */
public interface RatingDAO {
    
    public void setDataSource(DataSource ds);
    
    public List<com.myprojects.moviesrecommender.baseClasses.rating> getRatingOfMovie(String movieId);
    
    public List<com.myprojects.moviesrecommender.baseClasses.rating> getRatingByUser(String userId);
    
    public com.myprojects.moviesrecommender.baseClasses.rating getRatingOfMovieByUser(String userId, String movieId);
}
