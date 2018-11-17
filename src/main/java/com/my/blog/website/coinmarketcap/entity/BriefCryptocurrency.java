package com.my.blog.website.coinmarketcap.entity;


//虚拟币的简要信息
public class BriefCryptocurrency {

//  "id": 1,
//  "name": "Bitcoin",
//  "symbol": "BTC",
//  "website_slug": "bitcoin"

    private String id;
    private String name;
    private String symbol;
    private String website_slug;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getWebsite_slug() {
        return website_slug;
    }

    public void setWebsite_slug(String website_slug) {
        this.website_slug = website_slug;
    }

}
