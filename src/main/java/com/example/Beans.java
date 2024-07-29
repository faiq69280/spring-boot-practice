package com.example;


import com.example.security.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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


    @Bean
    @Qualifier("customPasswordEncoder")
    public CustomPasswordEncoder passwordEncoder(@Value("${spring.encoding.secretKey}") String secretKey) throws NoSuchAlgorithmException, InvalidKeyException {
        return new CustomPasswordEncoder(secretKey);
    }

}
