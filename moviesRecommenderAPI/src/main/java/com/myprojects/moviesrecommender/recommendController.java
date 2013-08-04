/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myprojects.moviesrecommender;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author srividyak
 */
@Controller
@RequestMapping("/user")
public class recommendController {
    
    @RequestMapping(method = RequestMethod.GET)
    public String getRecommendations() {
        return "Hello world";
    }
}
