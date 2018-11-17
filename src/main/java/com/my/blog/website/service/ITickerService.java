package com.my.blog.website.service;

import com.github.pagehelper.PageInfo;
import com.my.blog.website.bqientity.BqiTicker;
import com.my.blog.website.coinmarketcap.entity.Ticker;
import com.my.blog.website.modal.Vo.AttachVo;
import com.my.blog.website.modal.Vo.TickerVo;

import java.io.IOException;

/**
 * Created by wangq on 2017/3/20.
 */
public interface ITickerService {


    /**
     * 根据附件id查询附件
     *
     * @param id
     * @return
     */
    TickerVo selectBySymbol(String symbol);


    /**
     * 根据附件id查询附件
     *
     * @param id
     * @return
     */
    TickerVo selectByName(String name);

    /**
     * <p>Description: 根据ID获取虚拟币的详细信息</p>
     * <p>param  </p>
     * <p>author zhouhe</p>
     */
    Ticker GetTicker(String id) throws IOException;


    /**
     * <p>Description: 根据名称获取虚拟币的详细信息</p>
     * <p>param  </p>
     * <p>author zhouhe</p>
     */
    BqiTicker GetBqiTicker(String name) throws IOException;

}
