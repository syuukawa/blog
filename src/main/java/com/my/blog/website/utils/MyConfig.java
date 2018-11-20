package com.my.blog.website.utils;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyConfig {

    @Value("${picture.server}")
    private String pictureServer;

    public String getPictureServer() {
        return pictureServer;
    }

    public void setPictureServer(String pictureServer) {
        this.pictureServer = pictureServer;
    }
}
