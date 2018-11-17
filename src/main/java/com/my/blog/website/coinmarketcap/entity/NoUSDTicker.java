package com.my.blog.website.coinmarketcap.entity;

import com.google.gson.internal.LinkedTreeMap;

public class NoUSDTicker {

//                "id": 1,
//                "name": "Bitcoin",
//                "symbol": "BTC",
//                "website_slug": "bitcoin",
//                "rank": 1,
//                "circulating_supply": 17349312.0,
//                "total_supply": 17349312.0,
//                "max_supply": 21000000.0,
//                "quotes": {
//                  "USD": {
//                    "price": 6345.33839675,
//                    "volume_24h": 4242159409.69687,
//                    "market_cap": 110087255591.0,
//                    "percent_change_1h": -0.06,
//                    "percent_change_24h": -1.98,
//                    "percent_change_7d": -1.98
//        }
//    },
//            "last_updated": 1540889352

    private String id;
    private String name;
    private String symbol;
    private String website_slug;
    private String rank;
    private String circulating_supply;
    private String last_updated;
    private String max_supply;
    private String total_supply;
    private LinkedTreeMap<String, Object> quotes;

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

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getCirculating_supply() {
        return circulating_supply;
    }

    public void setCirculating_supply(String circulating_supply) {
        this.circulating_supply = circulating_supply;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public String getMax_supply() {
        return max_supply;
    }

    public void setMax_supply(String max_supply) {
        this.max_supply = max_supply;
    }

    public String getTotal_supply() {
        return total_supply;
    }

    public void setTotal_supply(String total_supply) {
        this.total_supply = total_supply;
    }

    public LinkedTreeMap<String, Object> getQuotes() {
        return quotes;
    }

    public void setQuotes(LinkedTreeMap<String, Object> quotes) {
        this.quotes = quotes;
    }
}
