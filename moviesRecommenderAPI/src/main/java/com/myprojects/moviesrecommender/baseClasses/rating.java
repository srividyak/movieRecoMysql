/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojects.moviesrecommender.baseClasses;

/**
 *
 * @author Srividya.K
 */
public class rating implements Comparable<rating> {

    String userId;
    String movieId;
    float rate;
    long timestamp;

    public rating(String userId, String movieId, float rate, long timestamp) {
        this.userId = userId;
        this.movieId = movieId;
        this.rate = rate;
        this.timestamp = timestamp;
    }

    /**
     * Does a reverse order sort
     * @param o
     * @return 
     */
    public int compareTo(rating o) {
        if (this.getMovieId() == o.getMovieId() && this.getUserId() == o.getUserId()) {
            if(this.getTimestamp() == o.getTimestamp()) {
                return 0;
            } else if(this.getTimestamp() < o.getTimestamp()) {
                return 1;
            } else {
                return -1;
            }
        } else {
            if (this.getRate() == o.getRate()) {
                return 0;
            } else if (this.getRate() < o.getRate()) {
                return 1;
            } else {
                return -1;
            }
        }

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

    public float getRate() {
        return rate;
    }
}
