package com.my.blog.website.coinmarketcap.service;


import com.my.blog.website.coinmarketcap.entity.BriefCryptocurrency;
import com.my.blog.website.coinmarketcap.entity.Ticker;

import java.io.IOException;
import java.util.List;
import java.util.TreeMap;


public interface ICoinmark {

    /**
     * <p>Description: 虚拟币的简要信息列表获取</p>
     * <p>param  </p>
     * <p>author zhouhe</p>
     */
    List<BriefCryptocurrency> GetListings() throws IOException;


    /**
     * <p>Description: 根据ID获取虚拟币的详细信息</p>
     * <p>param  </p>
     * <p>author zhouhe</p>
     */
    Ticker GetTicker(String id) throws IOException;


    /**
     * <p>Description: 根据ID获取虚拟币的详细信息列表</p>
     * <p>param  </p>
     * <p>author zhouhe</p>
     */
    TreeMap<String, Ticker> GetTickerList(String start, String limit, String sort) throws IOException;
//    Example: https://api.coinmarketcap.com/v2/ticker/
//    Example: https://api.coinmarketcap.com/v2/ticker/?limit=10  //获取指定的记录数
//    Example: https://api.coinmarketcap.com/v2/ticker/?limit=10&sort=id  //排序
//    Example: https://api.coinmarketcap.com/v2/ticker/?start=101&limit=10 //翻页获取记录
//    Example: https://api.coinmarketcap.com/v2/ticker/?start=101&limit=10&sort=id //翻页排序获取记录数
}
