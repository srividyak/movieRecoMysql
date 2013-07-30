/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojects.moviesrecommender.baseClasses;

import com.myprojects.moviesrecommender.mysql.moviesJDBCTemplate;
import com.myprojects.moviesrecommender.mysql.ratingJDBCTemplate;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author srividyak
 */
public class recommender {

    private String userId;
    private moviesJDBCTemplate moviesDataFetcher;
    private ratingJDBCTemplate ratingDataFetcher;
    private long totalNumMovies;
    private static int maxMoviesPerCall = 1000;
    private static int numThreads = 10;
    ExecutorService executorService;

    public recommender(String userId, moviesJDBCTemplate moviesDataFetcher, ratingJDBCTemplate ratingDataFetcher) {
        this.userId = userId;
        this.moviesDataFetcher = moviesDataFetcher;
        this.ratingDataFetcher = ratingDataFetcher;
        this.recommend();
    }

    public void getAllMoviesByIds(final HashMap<String, Boolean> ratings) {
        this.totalNumMovies = moviesDataFetcher.getMoviesCount();
        List<Future<List<movie>>> movieFutures = null;
        List<movie> movieResult = new LinkedList<movie>();
        List<Callable<List<movie>>> callables = new LinkedList<Callable<List<movie>>>();
        for (long i = 0; i < totalNumMovies; i += maxMoviesPerCall) {
            final long offset = i;
            final int count = maxMoviesPerCall;
            Callable<List<movie>> callable = new Callable<List<movie>>() {
                public List<movie> call() throws Exception {
                    List<movie> movies = recommender.this.moviesDataFetcher.getAllMovies(offset, count);
                    List<movie> filteredMovies = new LinkedList<movie>();
                    for (movie m : movies) {
                        if (ratings.containsKey(m.getMovieId())) {
                            filteredMovies.add(m);
                        }
                    }
                    return filteredMovies;
                }
            };
            callables.add(callable);
        }
        try {
            movieFutures = executorService.invokeAll(callables);
            for (Future<List<movie>> movieFuture : movieFutures) {
                try {
                    movieResult.addAll(movieFuture.get());
                } catch (ExecutionException ex) {
                    Logger.getLogger(recommender.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(recommender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void recommend() {
        executorService = Executors.newFixedThreadPool(10);
        List<rating> ratings = ratingDataFetcher.getRatingByUser(userId);
        Collections.sort(ratings);
        HashMap<String,Boolean> ratingMap = new HashMap<String, Boolean>();
        for (rating r : ratings) {
            ratingMap.put(r.getMovieId(), Boolean.TRUE);
        }
    }
}
