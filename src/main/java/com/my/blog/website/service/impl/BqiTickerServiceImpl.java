package com.my.blog.website.service.impl;

import com.my.blog.website.entity.BqiTickerTable;
import com.my.blog.website.repository.BqiTickerRepository;
import com.my.blog.website.service.IQbiTickerService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by jiutian on 2018/11/13.
 */
@Service("bqiTickerService")
public class BqiTickerServiceImpl implements IQbiTickerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BqiTickerServiceImpl.class);


    @Autowired
    private BqiTickerRepository bqiTickerRepository;

    @Override
    public List<BqiTickerTable> GetBqiTickerList() {
        List<BqiTickerTable> bqiTickerTables = bqiTickerRepository.findAll();
        for (BqiTickerTable bqiTickerTable : bqiTickerTables) {

            String volume_24h_usd = bqiTickerTable.getVolume_24h_usd();
            volume_24h_usd = formatNum(volume_24h_usd.substring(0, volume_24h_usd.indexOf(".")), true);
            bqiTickerTable.setVolume_24h_usd(volume_24h_usd);

            String market_cap_usd = bqiTickerTable.getMarket_cap_usd();
            market_cap_usd = formatNum(market_cap_usd.substring(0, market_cap_usd.indexOf(".")), true);
            bqiTickerTable.setMarket_cap_usd(market_cap_usd);

            String available_supply = bqiTickerTable.getAvailable_supply();
            available_supply = formatNum(available_supply.substring(0, available_supply.indexOf(".")), true);
            bqiTickerTable.setAvailable_supply(available_supply);

        }
        return bqiTickerTables;
    }

    /**
     * <pre>
     * 数字格式化显示
     * 小于万默认显示 大于万以1.7万方式显示最大是9999.9万
     * 大于亿以1.1亿方式显示最大没有限制都是亿单位
     * make by dongxh 2017年12月28日上午10:05:22
     * </pre>
     *
     * @param num   格式化的数字
     * @param kBool 是否格式化千,为true,并且num大于999就显示999+,小于等于999就正常显示
     * @return
     */
    public static String formatNum(String num, Boolean kBool) {
        StringBuffer sb = new StringBuffer();
        if (!StringUtils.isNumeric(num))
            return "0";
        if (kBool == null)
            kBool = false;

        BigDecimal b0 = new BigDecimal("1000");
        BigDecimal b1 = new BigDecimal("10000");
        BigDecimal b2 = new BigDecimal("100000000");
        BigDecimal b3 = new BigDecimal(num);

        String formatNumStr = "";
        String nuit = "";

//        // 以千为单位处理
//        if (kBool) {
//            if (b3.compareTo(b0) == 0 || b3.compareTo(b0) == 1) {
//                return "999+";
//            }
//            return num;
//        }

        // 以万为单位处理
        if (b3.compareTo(b1) == -1) {
            sb.append(b3.toString());
        } else if ((b3.compareTo(b1) == 0 && b3.compareTo(b1) == 1)
                || b3.compareTo(b2) == -1) {
            formatNumStr = b3.divide(b1).toString();
            nuit = "万";
        } else if (b3.compareTo(b2) == 0 || b3.compareTo(b2) == 1) {
            formatNumStr = b3.divide(b2).toString();
            nuit = "亿";
        }
        if (!"".equals(formatNumStr)) {
            int i = formatNumStr.indexOf(".");
            if (i == -1) {
                sb.append(formatNumStr).append(nuit);
            } else {
                i = i + 1;
                String v = formatNumStr.substring(i, i + 1);
                if (!v.equals("0")) {
                    sb.append(formatNumStr.substring(0, i + 1)).append(nuit);
                } else {
                    sb.append(formatNumStr.substring(0, i - 1)).append(nuit);
                }
            }
        }
        if (sb.length() == 0)
            return "0";
        return sb.toString();
    }


}
