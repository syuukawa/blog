package com.my.blog.website.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.my.blog.website.bqientity.BqiTicker;
import com.my.blog.website.coinmarketcap.entity.Ticker;
import com.my.blog.website.dao.AttachVoMapper;
import com.my.blog.website.dao.TickerVoMapper;
import com.my.blog.website.modal.Vo.AttachVo;
import com.my.blog.website.modal.Vo.AttachVoExample;
import com.my.blog.website.modal.Vo.TickerVo;
import com.my.blog.website.service.IAttachService;
import com.my.blog.website.service.ITickerService;
import com.my.blog.website.utils.DateKit;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * Created by wangq on 2017/3/20.
 */
@Service
public class TickerServiceImpl implements ITickerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TickerServiceImpl.class);

    @Resource
    private TickerVoMapper tickerDao;

    @Override
    public TickerVo selectBySymbol(String symbol) {
        if (null != symbol) {
            return tickerDao.selectBySymbol(symbol);
        }
        return null;
    }

    @Cacheable(value = "demo", key = "#name")
    @Override
    public TickerVo selectByName(String name) {
        if (null != name) {
            return tickerDao.selectByName(name);
        }
        return null;
    }


    /**
     * <p>Description: 根据ID获取虚拟币的详细信息</p>
     * <p>param  </p>
     * <p>author zhouhe</p>
     */
    @Cacheable(value = "demo", key = "#id")
    @Override
    public Ticker GetTicker(String id) throws IOException {
        Ticker returnResult = new Ticker();
        String url = "http://api.coinmarketcap.com/v2/ticker/";
        url = url + id;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
            String result = EntityUtils.toString(response.getEntity());
            JSONObject accountJson = JSONObject.parseObject(result);
            Gson gson = new Gson();
            returnResult = gson.fromJson(accountJson.get("data").toString(), Ticker.class);
//            System.out.println(gson.toJson(returnResult));
        }
        httpGet.releaseConnection();
        return returnResult;
    }

//  https://public.bqi.com/public/v1/ticker?code=0chain&convert=CNY&start=0&limit=1&isKLine=1

    /**
     * <p>Description: 根据ID获取虚拟币的详细信息</p>
     * <p>param  </p>
     * <p>author zhouhe</p>
     */
    @Cacheable(value = "demo", key = "#name")
    @Override
    public BqiTicker GetBqiTicker(String name) throws IOException {
        BqiTicker returnResult = new BqiTicker();
        String url = "https://public.bqi.com/public/v1/ticker?code=" + name;
        url = url + "&convert=CNY&start=0&limit=1&isKLine=1";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
            String result = EntityUtils.toString(response.getEntity());
//            JSONObject accountJson = JSONObject.parseObject(result);
            Gson gson = new Gson();
            returnResult = gson.fromJson(result.replace("[","").replace("]",""), BqiTicker.class);
//            System.out.println(gson.toJson(returnResult));
        }
        httpGet.releaseConnection();
        return returnResult;
    }

}
