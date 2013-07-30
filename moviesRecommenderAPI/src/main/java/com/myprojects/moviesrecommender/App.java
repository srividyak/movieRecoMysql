package com.myprojects.moviesrecommender;

import com.myprojects.moviesrecommender.baseClasses.recommender;
import com.myprojects.moviesrecommender.mysql.moviesJDBCTemplate;
import com.myprojects.moviesrecommender.mysql.ratingJDBCTemplate;
import com.myprojects.moviesrecommender.mysql.tagsJDBCTemplate;
import java.util.Date;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("appConfig.xml");
        moviesJDBCTemplate mTemplate = (moviesJDBCTemplate) context.getBean("moviesJDBCTemplate");
        ratingJDBCTemplate rTemplate = (ratingJDBCTemplate) context.getBean("ratingJDBCTemplate");
        tagsJDBCTemplate tTemplate = (tagsJDBCTemplate) context.getBean("tagsJDBCTemplate");
        long time = new Date().getTime();
        recommender reco = (recommender) context.getBean("recommender");
        System.out.println((new Date().getTime()) - time);
    }
}
