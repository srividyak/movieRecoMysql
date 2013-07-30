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
public interface TagsDAO {
    
    public void setDataSource(DataSource ds);
    
    public com.myprojects.moviesrecommender.baseClasses.tags getTagsOfMovieByUser(String userId, String movieId);
    
    public List<com.myprojects.moviesrecommender.baseClasses.tags> getTagsOfMovie(String movieId);
    
    public List<com.myprojects.moviesrecommender.baseClasses.tags> getTagsByUser(String userid);
}
