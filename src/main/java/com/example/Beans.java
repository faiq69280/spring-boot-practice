package com.example;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
@Configuration
public class Beans {


    @Bean
    @Qualifier("ArrayList")
    public List<ChatMessage> arrayList(){
        return new ArrayList<ChatMessage>();
    }

}
