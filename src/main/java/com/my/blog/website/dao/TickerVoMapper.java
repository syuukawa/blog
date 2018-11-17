package com.my.blog.website.dao;

import com.my.blog.website.modal.Vo.TickerVo;
import org.springframework.stereotype.Component;

@Component
public interface TickerVoMapper {
    TickerVo selectBySymbol(String symbol);

    TickerVo selectByName(String name);
}
