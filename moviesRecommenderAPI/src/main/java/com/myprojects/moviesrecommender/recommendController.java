/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojects.moviesrecommender;

import com.google.gson.Gson;
import com.myprojects.moviesrecommender.baseClasses.movie;
import com.myprojects.moviesrecommender.baseClasses.recommender;
import com.myprojects.moviesrecommender.mysql.moviesJDBCTemplate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author srividyak
 */
@Controller
@RequestMapping("/user")
public class recommendController {
   
    @Autowired
    private recommender recommender;
    
    @RequestMapping(value = "{userid}", method = RequestMethod.GET)
    @ResponseBody
    public String getRecommendations(@PathVariable String userid) {
        List<movie> movieList = recommender.recommend(userid);
        Gson gson = new Gson();
        return gson.toJson(movieList);
    }
}
