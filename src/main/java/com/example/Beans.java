package com.example;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Configuration
public class Beans {


    @Bean
    @Qualifier("ArrayList")
    public List<ChatMessage> arrayList(){
        return new ArrayList<ChatMessage>();
    }

    @Bean
    public Logger logger(){
        return Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    }

}
