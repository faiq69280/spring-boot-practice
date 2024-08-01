package com.example.security;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@PropertySource("classpath:application.properties")
public class CustomPasswordEncoder implements PasswordEncoder{
    @Autowired
    Logger logger;

    String secretKey;


    Mac sha256_HMAC;

    public CustomPasswordEncoder(String secretKey) throws NoSuchAlgorithmException, InvalidKeyException {
        this.secretKey = secretKey; //random-uuid

    }

    @Override
    public String encode(CharSequence password){
        try {
            sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256_HMAC.init(secret);
        }
        catch(NoSuchAlgorithmException noSuchAlgorithmException){
            logger.log(Level.WARNING,noSuchAlgorithmException.getMessage());
        }
        catch(InvalidKeyException invalidKeyException){
            logger.log(Level.WARNING,invalidKeyException.getMessage());
        }
        return Hex.encodeHexString(sha256_HMAC.doFinal(password.toString().getBytes(StandardCharsets.UTF_8)));
    }

    public void generateSalt(){
        this.secretKey = UUID.randomUUID().toString();
    }
    public String getSalt(){
        return secretKey;
    }
    public void setSalt(String salt){
        this.secretKey = salt;
    }




    public boolean matches(CharSequence rawPassword, String encodedPassword){
         return encode(rawPassword).equals(encodedPassword);
    }

}
