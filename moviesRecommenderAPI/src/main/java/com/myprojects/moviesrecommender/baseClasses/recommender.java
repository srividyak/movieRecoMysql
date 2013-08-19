/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojects.moviesrecommender.baseClasses;

import com.myprojects.moviesrecommender.mysql.moviesJDBCTemplate;
import com.myprojects.moviesrecommender.mysql.ratingJDBCTemplate;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author srividyak
 */
public class recommender {

    private String userId;
    private moviesJDBCTemplate moviesDataFetcher;
    private ratingJDBCTemplate ratingDataFetcher;
    private static int maxMoviesPerCall = 1000;
    private static int numThreads = 10;
    ExecutorService executorService;

    public recommender(moviesJDBCTemplate moviesDataFetcher, ratingJDBCTemplate ratingDataFetcher) {
        this.moviesDataFetcher = moviesDataFetcher;
        this.ratingDataFetcher = ratingDataFetcher;
    }

    public List<movie> getRecommendations(final List<rating> ratings) {
        List<String> movieIds = new LinkedList<String>();
        List<String> shortlistedMovieIds = new LinkedList<String>();
        List<movie> recommendedMovies = new LinkedList<movie>();
        float totalRate = 0;
        for (rating r : ratings) {
            totalRate += r.getRate();
            movieIds.add(r.getMovieId());
        }
        float avgRate = totalRate / ratings.size();
        for (rating r : ratings) {
            if (r.getRate() >= avgRate) {
                shortlistedMovieIds.add(r.getMovieId());
            }
        }
        List<movie> moviesRated = moviesDataFetcher.getMoviesIn(shortlistedMovieIds);
        List<movie> moviesNotRated = moviesDataFetcher.getMoviesNotIn(movieIds, 10);
        HashSet<Integer> set = new HashSet<Integer>();
        for (movie m : moviesRated) {
            int[] g = m.getGenres();
            for (int i = 0; i < g.length; i++) {
                set.add(g[i]);
            }
        }
        for(movie m : moviesNotRated) {
            int[] g = m.getGenres();
            for (int i = 0; i < g.length; i++) {
                if(set.contains(g[i])) {
                    recommendedMovies.add(m);
                }
            }
        }
        return recommendedMovies;
    }

    public List<movie> recommend(String userId) {
        this.userId = userId;
        List<rating> ratings = ratingDataFetcher.getRatingByUser(userId);
        Collections.sort(ratings);
        List<movie> recos = getRecommendations(ratings);
        return recos;
    }
}
