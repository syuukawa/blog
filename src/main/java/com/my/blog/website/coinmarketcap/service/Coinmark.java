package com.my.blog.website.coinmarketcap.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import com.my.blog.website.coinmarketcap.entity.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Coinmark implements ICoinmark {


    /**
     * <p>Description: 虚拟币的简要信息列表获取</p>
     * <p>param  </p>
     * <p>author zhouhe</p>
     */
    @Override
    public List<BriefCryptocurrency> GetListings() throws IOException {

        final String url = "https://api.coinmarketcap.com/v2/listings/";
        List<BriefCryptocurrency> cryptocurrencies = new ArrayList<>();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        HttpResponse response = httpClient.execute(httpGet);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
            String result = EntityUtils.toString(response.getEntity());
//            System.out.println(result);
            JSONObject accountJson = JSONObject.parseObject(result);
            cryptocurrencies = (List<BriefCryptocurrency>) JSONArray.parseArray(accountJson.get("data").toString(), BriefCryptocurrency.class);
//            for (BriefCryptocurrency cryptocurrency : cryptocurrencyList) {
//                System.out.println(cryptocurrency.getName());
//            }
        }
        httpGet.releaseConnection();
        return cryptocurrencies;
    }


    /**
     * <p>Description: 根据ID获取虚拟币的详细信息</p>
     * <p>param  </p>
     * <p>author zhouhe</p>
     */
    @Override
    public Ticker GetTicker(String id) throws IOException {
        Ticker returnResult = new Ticker();
        String url = "https://api.coinmarketcap.com/v2/ticker/";
        url = url + id;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        HttpResponse response = httpClient.execute(httpGet);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
            String result = EntityUtils.toString(response.getEntity());
//            System.out.println(result);
            JSONObject accountJson = JSONObject.parseObject(result);
            Gson gson = new Gson();
            returnResult = gson.fromJson(accountJson.get("data").toString(), Ticker.class);
            System.out.println(gson.toJson(returnResult));
        }
        httpGet.releaseConnection();
        return returnResult;
    }


    /**
     * <p>Description: 根据ID获取虚拟币的详细信息列表</p>
     * <p>Description: 按条件查询虚拟币列表信息</p>
     * <p>param  </p>
     * <p>author zhouhe</p>
     */
    @Override
    public TreeMap<String, Ticker> GetTickerList(String start, String limit, String sort) throws IOException {

        TreeMap<String, Ticker> treeMapList = new TreeMap<String, Ticker>();

        String url = "";
        Gson gson = new Gson();
        //1:::
        if (StringUtils.isEmpty(start) && StringUtils.isEmpty(limit) && StringUtils.isEmpty(sort)) {
            url = "https://api.coinmarketcap.com/v2/ticker/";
        } else if (StringUtils.isEmpty(start) && StringUtils.isNotEmpty(limit) && StringUtils.isEmpty(sort)) {
            url = "https://api.coinmarketcap.com/v2/ticker/";
            url = url + "?limit=" + limit;
        } else if (StringUtils.isEmpty(start) && StringUtils.isNotEmpty(limit) && StringUtils.isNotEmpty(sort)) {
            url = "https://api.coinmarketcap.com/v2/ticker/";
            url = url + "?limit=" + limit;
            url = url + "&sort=" + sort;
        } else if (StringUtils.isNotEmpty(start) && StringUtils.isNotEmpty(limit) && StringUtils.isEmpty(sort)) {
            url = "https://api.coinmarketcap.com/v2/ticker/";
            url = url + "?start=" + start;
            url = url + "&limit=" + limit;
        } else if (StringUtils.isNotEmpty(start) && StringUtils.isNotEmpty(limit) && StringUtils.isNotEmpty(sort)) {
            url = "https://api.coinmarketcap.com/v2/ticker/";
            url = url + "?start=" + start;
            url = url + "&limit=" + limit;
            url = url + "&sort=" + sort;
        }

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        HttpResponse response = httpClient.execute(httpGet);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
            String result = EntityUtils.toString(response.getEntity());
            JSONObject accountJson = JSONObject.parseObject(result);
            String dataJson = "[" + accountJson.get("data").toString() + "]";
            List<Object> objectList = (List<Object>) gson.fromJson(dataJson, Object.class);
            System.out.println("======= : " + objectList);
            LinkedTreeMap<String, String> linkedTreeMap = (LinkedTreeMap<String, String>) objectList.get(0);
//            System.out.println(linkedTreeMap);

            Ticker ticker = null;
            for (Map.Entry<String, String> entry : linkedTreeMap.entrySet()) {
                ticker = new Ticker();

                ObjectMapper mapper = new ObjectMapper();
                NoUSDTicker noUSDTicker = mapper.convertValue(entry.getValue(), NoUSDTicker.class);
                BeanUtils.copyProperties(noUSDTicker, ticker);

//                System.out.println(noUSDTicker);
                LinkedTreeMap<String, Object> quotes = (LinkedTreeMap<String, Object>) noUSDTicker.getQuotes();
                for (Map.Entry<String, Object> entry2 : quotes.entrySet()) {
                    ObjectMapper mapper2 = new ObjectMapper();
                    USDQuotes usdQuotes = mapper2.convertValue(entry2.getValue(), USDQuotes.class);
//                    System.out.println(usdQuotes);
                    ticker.getQuotes().setUSD(usdQuotes);
                }
                treeMapList.put(entry.getKey(), ticker);
            }

        }
        httpGet.releaseConnection();

        return treeMapList;
    }

    public static void main(String[] args) throws Exception {

        Coinmark coinmark = new Coinmark();
//        coinmark.GetListings();
//        coinmark.GetTicker("1");
        coinmark.GetTickerList("1","100","id");
    }

//
//    /**
//     * <p>Description: 根据ID获取虚拟币的详细信息列表</p>
//     * <p>param  </p>
//     * <p>author zhouhe</p>
//     */
//    @Override
//    public TreeMap<String, Ticker> GetTickerList() throws IOException {
//
//        TreeMap<String, Ticker> treeMapList = new TreeMap<String, Ticker>();
//
//        Gson gson = new Gson();
//        //1:::
//        final String url = "https://api.coinmarketcap.com/v2/ticker/";
//
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpGet httpGet = new HttpGet(url);
//
//        HttpResponse response = httpClient.execute(httpGet);
//
//        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
//            String result = EntityUtils.toString(response.getEntity());
//            JSONObject accountJson = JSONObject.parseObject(result);
//            String dataJson = "[" + accountJson.get("data").toString() + "]";
//            List<Object> objectList = (List<Object>) gson.fromJson(dataJson, Object.class);
//            System.out.println(objectList);
//            LinkedTreeMap<String, String> linkedTreeMap = (LinkedTreeMap<String, String>) objectList.get(0);
//            System.out.println(linkedTreeMap);
//
//            Ticker ticker = null;
//            for (Map.Entry<String, String> entry : linkedTreeMap.entrySet()) {
//                ticker = new Ticker();
//
//                ObjectMapper mapper = new ObjectMapper();
//                NoUSDTicker noUSDTicker = mapper.convertValue(entry.getValue(), NoUSDTicker.class);
//                BeanUtils.copyProperties(noUSDTicker, ticker);
//
//                System.out.println(noUSDTicker);
//                LinkedTreeMap<String, Object> quotes = (LinkedTreeMap<String, Object>) noUSDTicker.getQuotes();
//                for (Map.Entry<String, Object> entry2 : quotes.entrySet()) {
//                    ObjectMapper mapper2 = new ObjectMapper();
//                    USDQuotes usdQuotes = mapper2.convertValue(entry2.getValue(), USDQuotes.class);
//                    System.out.println(usdQuotes);
//                    ticker.getQuotes().setUSD(usdQuotes);
//                }
//                treeMapList.put(entry.getKey(), ticker);
//            }
//
//        }
//        httpGet.releaseConnection();
//
//        return treeMapList;
//    }
}
