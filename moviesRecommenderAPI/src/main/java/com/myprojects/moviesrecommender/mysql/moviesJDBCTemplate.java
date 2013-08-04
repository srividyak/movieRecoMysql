/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojects.moviesrecommender.mysql;

import com.myprojects.moviesrecommender.baseClasses.movie;
import com.myprojects.moviesrecommender.dataInterface.MoviesDAO;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Srividya.K
 */
public class moviesJDBCTemplate extends dataSourceTemplate implements MoviesDAO {
    
    public static long totalMoviesInDB = 10681;

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
    
    public List<movie> getMovies(long offset, int count) {
        String sql = "select * from movies limit " + offset + "," + count;
        List<movie> movies = jdbcTemplateObject.query(sql, new movieMapper());
        return movies;
    }
    
    /**
     * get movies from movies table where movieid in/not in ids
     * @param ids list of ids
     * @param includeFlag in/not in indicator
     * @param numRecords max num of records to be fetched. if 0, fetches all
     * @return 
     */
    public List<movie> getMovies(List<String> ids, boolean includeFlag, long numRecords) {
        long size = ids.size();
        List<movie> movies = new LinkedList<movie>();
        if (size > 0) {
            String inClause = "";
            for (long i = 0; i < size; i++) {
                inClause += "?,";
            }
            inClause = inClause.substring(0, inClause.length() - 1);
            inClause = ((includeFlag) ? "in " : "not in ") + "(" + inClause + ")";
            String sqlIn = "select * from movies where movieid " + inClause;
            if(numRecords > 0) {
                Random r = new Random();
                long offset = r.nextLong() % totalMoviesInDB;
                if(offset + numRecords > totalMoviesInDB) {
                    offset -= numRecords;
                    offset = (offset < 0) ? 0 : offset;
                }
                sqlIn += " limit " + offset + " ," + numRecords;
            }
            movies = jdbcTemplateObject.query(sqlIn, ids.toArray(), new movieMapper());
        }
        return movies;
    }
    
    public List<movie> getMoviesIn(List<String> ids) {
        return getMovies(ids, true, 0);
    }
    
    public List<movie> getMoviesNotIn(List<String> ids) {
        return getMovies(ids, false, 0);
    }
    
    public List<movie> getMoviesIn(List<String> ids, int maxRecords) {
        return getMovies(ids, true, maxRecords);
    }
    
    public List<movie> getMoviesNotIn(List<String> ids, int maxRecords) {
        return getMovies(ids, false, maxRecords);
    }
    
    public long getMoviesCount() {
        String sql = "select count(*) from movies";
        Long count = jdbcTemplateObject.queryForObject(sql, Long.class);
        return count.longValue();
    }
    
}
