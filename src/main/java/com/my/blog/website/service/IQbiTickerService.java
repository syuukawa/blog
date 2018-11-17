package com.my.blog.website.service;

import com.my.blog.website.bqientity.BqiTicker;
import com.my.blog.website.coinmarketcap.entity.Ticker;
import com.my.blog.website.entity.BqiTickerTable;
import com.my.blog.website.modal.Vo.TickerVo;

import java.io.IOException;
import java.util.List;

/**
 * Created by wangq on 2017/3/20.
 */
public interface IQbiTickerService {

    /**
     * <p>Description: 根据名称获取虚拟币的详细信息</p>
     * <p>param  </p>
     * <p>author zhouhe</p>
     */
    List<BqiTickerTable> GetBqiTickerList();


}
