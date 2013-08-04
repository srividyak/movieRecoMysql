/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojects.moviesrecommender.baseClasses;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Srividya.K
 */
public class movie {

    public String movieId;
    String title;
    int[] genres;
    String[] genreStrings;
    public static String[] genreMap = {
        "action", "adventure", "animation", "children", "comedy", "crime", "documentary",
        "drama", "fantasy", "film-noir", "horror", "musical", "mystery", "romance",
        "sci-fi", "thriller", "war", "western"
    };

    public movie(String movieId, String title, int[] genres) {
        this.movieId = movieId;
        this.title = title;
        this.genres = genres;
        this.generateGenreHash();
    }

    private void generateGenreHash() {
        genreStrings = new String[genres.length];
        int index = 0;
        for (int genre : genres) {
            try {
                genreStrings[index] = genreMap[genre];
            } catch (Exception e) {
                genreStrings[index] = "";
            }
            index++;
        }
    }

    public String getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public int[] getGenres() {
        return genres;
    }

    public String[] getGenreStrings() {
        return genreStrings;
    }
    
    public String getAllGenres() {
        return StringUtils.join(genreStrings, ",");
    }
}
