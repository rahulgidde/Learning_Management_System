package com.bridgelabz.lmsapplication.configuration;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary getCloudinary() {
        Cloudinary cloudinary = null;
        Map config = new HashMap();
        config.put("cloud_name", System.getenv().get("cloudinary.cloud_name"));
        config.put("api_key", System.getenv().get("cloudinary.api_key"));
        config.put("api_secret", System.getenv().get("cloudinary.api_secret"));
        cloudinary = new Cloudinary(config);
        return cloudinary;
    }
}
