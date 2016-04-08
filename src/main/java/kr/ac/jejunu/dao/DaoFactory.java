package kr.ac.jejunu.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by JKKim on 2016. 4. 1..
 */
@Configuration
public class DaoFactory {
    @Bean
    public UserDao userDao() {
        return new UserDao(ConnectionMaker());
    }

    @Bean
    public ConnectionMaker ConnectionMaker() {
        return  new SimpleConnectionMaker();
    }
}
