package com.my.blog.website.coinmarketcapfetch.entity;


//列表中显示的虚拟币的内容
public class ListCoinInfo {

    private String num;

    private String Name;

    private String MarketCap;

    private String Price;

    private String Volume24h;

    private String CirculatingSupply;

    private String Change24h;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMarketCap() {
        return MarketCap;
    }

    public void setMarketCap(String marketCap) {
        MarketCap = marketCap;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getVolume24h() {
        return Volume24h;
    }

    public void setVolume24h(String volume24h) {
        Volume24h = volume24h;
    }

    public String getCirculatingSupply() {
        return CirculatingSupply;
    }

    public void setCirculatingSupply(String circulatingSupply) {
        CirculatingSupply = circulatingSupply;
    }

    public String getChange24h() {
        return Change24h;
    }

    public void setChange24h(String change24h) {
        Change24h = change24h;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
