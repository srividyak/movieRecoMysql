/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojects.moviesrecommender.mysql;

import com.myprojects.moviesrecommender.dataInterface.DataSourceDAO;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author srividyak
 */
public class dataSourceTemplate implements DataSourceDAO {

    protected DataSource dataSource;
    protected JdbcTemplate jdbcTemplateObject;
    
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    
}
