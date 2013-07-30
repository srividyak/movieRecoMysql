/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojects.moviesrecommender.baseClasses;

/**
 *
 * @author Srividya.K
 */
public class tags {
    String tag;
    String userId;
    String movieId;
    long timestamp;

    public tags(String tag, String userId, String movieId, long timestamp) {
        this.tag = tag;
        this.userId = userId;
        this.movieId = movieId;
        this.timestamp = timestamp;
    }

    public String getTag() {
        return tag;
    }

    public String getUserId() {
        return userId;
    }

    public String getMovieId() {
        return movieId;
    }

    public long getTimestamp() {
        return timestamp;
    }
    
    
}
