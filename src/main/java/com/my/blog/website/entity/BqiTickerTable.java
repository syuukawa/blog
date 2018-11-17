package com.my.blog.website.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "t_bqi_ticker")
public class BqiTickerTable implements Serializable {
// "id": "0chain",
//         "name": "0chain",
//         "symbol": "ZCN",
//         "rank": 0,
//         "logo": "http://static.bqi.com/coin/20180703/0chain.webp",
//         "price_usd": 0.1514,
//         "price_btc": 0.00002052,
//         "volume_24h_usd": 21315,
//         "market_cap_usd": 0,
//         "available_supply": 40000000,
//         "total_supply": 400000000,
//         "max_supply": 400000000,
//         "percent_change_1h": 0.85,
//         "percent_change_24h": -1.88,
//         "percent_change_7d": -3.94,
//         "last_updated": 1536112725,
//         "KLine": "0.1624,0.1594,0.1611,0.1561,0.1510,0.1501,0.1552,0.1251,0.1255,0.2178,0.2197,0.1454,0.1482,0.1484,0.1520,0.1572,0.1569,0.1581,0.1553,0.1658,0.1661,0.1549,0.1515,0.1529,0.1507,0.1508,0.1558,0.1487"
//IDENTITY：主键由数据库自动生成（主要是自动增长型）
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    //名称
    private String name;

    private String symbol;

    private String rank;

    private String logo;

    //价格
    private String price_usd;

    private String price_btc;

    //成交额(24h)
    private String volume_24h_usd;

    //市值
    private String market_cap_usd;

    //流通量
    private String available_supply;

    private String total_supply;

    private String max_supply;

    private String percent_change_1h;

    //涨幅(24h)
    private String percent_change_24h;

    //涨幅(7d)
    private String percent_change_7d;

    private String last_updated;

    @Column(length = 1000)
    private String KLine;

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

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(String price_usd) {
        this.price_usd = price_usd;
    }

    public String getPrice_btc() {
        return price_btc;
    }

    public void setPrice_btc(String price_btc) {
        this.price_btc = price_btc;
    }

    public String getVolume_24h_usd() {
        return volume_24h_usd;
    }

    public void setVolume_24h_usd(String volume_24h_usd) {
        this.volume_24h_usd = volume_24h_usd;
    }

    public String getMarket_cap_usd() {
        return market_cap_usd;
    }

    public void setMarket_cap_usd(String market_cap_usd) {
        this.market_cap_usd = market_cap_usd;
    }

    public String getAvailable_supply() {
        return available_supply;
    }

    public void setAvailable_supply(String available_supply) {
        this.available_supply = available_supply;
    }

    public String getTotal_supply() {
        return total_supply;
    }

    public void setTotal_supply(String total_supply) {
        this.total_supply = total_supply;
    }

    public String getMax_supply() {
        return max_supply;
    }

    public void setMax_supply(String max_supply) {
        this.max_supply = max_supply;
    }

    public String getPercent_change_1h() {
        return percent_change_1h;
    }

    public void setPercent_change_1h(String percent_change_1h) {
        this.percent_change_1h = percent_change_1h;
    }

    public String getPercent_change_24h() {
        return percent_change_24h;
    }

    public void setPercent_change_24h(String percent_change_24h) {
        this.percent_change_24h = percent_change_24h;
    }

    public String getPercent_change_7d() {
        return percent_change_7d;
    }

    public void setPercent_change_7d(String percent_change_7d) {
        this.percent_change_7d = percent_change_7d;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public String getKLine() {
        return KLine;
    }

    public void setKLine(String KLine) {
        this.KLine = KLine;
    }

    @Override
    public String toString() {
        return "BqiTickerTable{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", rank='" + rank + '\'' +
                ", logo='" + logo + '\'' +
                ", price_usd='" + price_usd + '\'' +
                ", price_btc='" + price_btc + '\'' +
                ", volume_24h_usd='" + volume_24h_usd + '\'' +
                ", market_cap_usd='" + market_cap_usd + '\'' +
                ", available_supply='" + available_supply + '\'' +
                ", total_supply='" + total_supply + '\'' +
                ", max_supply='" + max_supply + '\'' +
                ", percent_change_1h='" + percent_change_1h + '\'' +
                ", percent_change_24h='" + percent_change_24h + '\'' +
                ", percent_change_7d='" + percent_change_7d + '\'' +
                ", last_updated='" + last_updated + '\'' +
                ", KLine='" + KLine + '\'' +
                '}';
    }
}
