package com.my.blog.website.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import com.my.blog.website.bqientity.BqiTicker;
import com.my.blog.website.coinmarketcap.entity.Ticker;
import com.my.blog.website.constant.WebConst;
import com.my.blog.website.dao.MetaVoMapper;
import com.my.blog.website.dao.TickerVoMapper;
import com.my.blog.website.dto.Types;
import com.my.blog.website.entity.BqiTickerTable;
import com.my.blog.website.exception.TipException;
import com.my.blog.website.modal.Vo.AttachVo;
import com.my.blog.website.modal.Vo.ContentVo;
import com.my.blog.website.modal.Vo.ContentVoExample;
import com.my.blog.website.modal.Vo.TickerVo;
import com.my.blog.website.repository.BqiTickerRepository;
import com.my.blog.website.service.*;
import com.my.blog.website.utils.DateKit;
import com.my.blog.website.utils.TaleUtils;
import com.my.blog.website.utils.Tools;
import com.vdurmont.emoji.EmojiParser;
import com.my.blog.website.dao.ContentVoMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/3/13 013.
 */
@Service
public class ContentServiceImpl implements IContentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContentServiceImpl.class);

    @Resource
    private ContentVoMapper contentDao;

    @Resource
    private MetaVoMapper metaDao;

    @Resource
    private IRelationshipService relationshipService;

    @Resource
    private IMetaService metasService;

    @Resource
    private IAttachService attachService;

    @Resource
    private TickerVoMapper tickerDao;

    @Resource
    private ITickerService tickerService;

    @Autowired
    private BqiTickerRepository bqiTickerRepository;

    @Override
    public void publish(ContentVo contents) {
        if (null == contents) {
            throw new TipException("文章对象为空");
        }
        if (StringUtils.isBlank(contents.getTitle())) {
            throw new TipException("文章标题不能为空");
        }
        if (StringUtils.isBlank(contents.getContent())) {
            throw new TipException("文章内容不能为空");
        }
        int titleLength = contents.getTitle().length();
        if (titleLength > WebConst.MAX_TITLE_COUNT) {
            throw new TipException("文章标题过长");
        }
        int contentLength = contents.getContent().length();
        if (contentLength > WebConst.MAX_TEXT_COUNT) {
            throw new TipException("文章内容过长");
        }
        if (null == contents.getAuthorId()) {
            throw new TipException("请登录后发布文章");
        }
        if (StringUtils.isNotBlank(contents.getSlug())) {
            if (contents.getSlug().length() < 5) {
                throw new TipException("路径太短了");
            }
            if (!TaleUtils.isPath(contents.getSlug())) throw new TipException("您输入的路径不合法");
            ContentVoExample contentVoExample = new ContentVoExample();
            contentVoExample.createCriteria().andTypeEqualTo(contents.getType()).andStatusEqualTo(contents.getSlug());
            long count = contentDao.countByExample(contentVoExample);
            if (count > 0) throw new TipException("该路径已经存在，请重新输入");
        } else {
            contents.setSlug(null);
        }

        contents.setContent(EmojiParser.parseToAliases(contents.getContent()));

        int time = DateKit.getCurrentUnixTime();
        contents.setCreated(time);
        contents.setModified(time);
        contents.setHits(0);
        contents.setCommentsNum(0);

        String tags = contents.getTags();
        String categories = contents.getCategories();
        contentDao.insert(contents);
        Integer cid = contents.getCid();

        metasService.saveMetas(cid, tags, Types.TAG.getType());
        metasService.saveMetas(cid, categories, Types.CATEGORY.getType());
    }

    @Override
    public PageInfo<ContentVo> getContents(Integer p, Integer limit) throws IOException {
        LOGGER.debug("Enter getContents method");
        ContentVoExample example = new ContentVoExample();
        example.setOrderByClause("created desc");
        example.createCriteria().andTypeEqualTo(Types.ARTICLE.getType()).andStatusEqualTo(Types.PUBLISH.getType());
        PageHelper.startPage(p, limit);
        List<ContentVo> data = contentDao.selectByExampleWithBLOBs(example);
        for (ContentVo vo : data) {

            //1:添加图片显示的处理  jiutian
            String name = vo.getName() + ".jpg";
            AttachVo attachVo = attachService.selectByFname(name);
            if (attachVo != null) {
                String picturePath = attachVo.getFkey();
                vo.setBgImage(picturePath);
            }

//TODO  001 在后台管理里边输入coinmarketcap对应的id
//
//            //2：添加价格的显示处理 TODO 在后台管理里边输入coinmarketcap对应的id 然后在获取价格
//            TickerVo tickerVo = tickerDao.selectByName(vo.getName());
////            System.out.println("name : " + tickerVo.getName());
//            if (tickerVo != null) {
//                Ticker ticker = tickerService.GetTicker(tickerVo.getId().toString());
//                if (ticker != null) {
////                System.out.println("24小时价格变动 ： " + ticker.getQuotes().getUSD().getPercent_change_24h());
//                    vo.setPrice(ticker.getQuotes().getUSD().getPrice().substring(0,7) + "USD");
//                    vo.setPercent_change_24h(ticker.getQuotes().getUSD().getPercent_change_24h() + "%");
//                }
//            } else {
//                vo.setPrice("");
//                vo.setPercent_change_24h("");
//            }
//TODO 002 https://www.bqi.com/api/
//            BqiTicker bqiTicker = tickerService.GetBqiTicker(vo.getName());
            BqiTickerTable bqiTicker = bqiTickerRepository.findOneByName(vo.getName());
            if (bqiTicker != null) {
                vo.setPrice(bqiTicker.getPrice_usd() + "USD");
                vo.setPercent_change_24h(bqiTicker.getPercent_change_24h() + "%");
            } else {
                vo.setPrice("");
                vo.setPercent_change_24h("");
            }
        }
        PageInfo<ContentVo> pageInfo = new PageInfo<>(data);
        LOGGER.debug("Exit getContents method");
        return pageInfo;
    }


    //TODO add by jiutian
    @Override
    public PageInfo<ContentVo> getContentsByCategory(Integer p, String category, Integer limit) {
        LOGGER.debug("Enter getContentsByCategory method");
        ContentVoExample example = new ContentVoExample();
        example.setOrderByClause("created desc");
        example.createCriteria().andTypeEqualTo(Types.ARTICLE.getType()).andStatusEqualTo(Types.PUBLISH.getType())
                .andCategoriesLike(category);
        PageHelper.startPage(p, limit);
        List<ContentVo> data = contentDao.selectByExampleWithBLOBs(example);
        for (ContentVo vo : data) {
            //1:添加图片显示的处理  jiutian
            String name = vo.getName() + ".jpg";
            AttachVo attachVo = attachService.selectByFname(name);
            if (attachVo != null) {
                String picturePath = attachVo.getFkey();
                vo.setBgImage(picturePath);
            }
//TODO 002 https://www.bqi.com/api/
//            BqiTicker bqiTicker = tickerService.GetBqiTicker(vo.getName());
            BqiTickerTable bqiTicker = bqiTickerRepository.findOneByName(vo.getName());
            if (bqiTicker != null) {
                vo.setPrice(bqiTicker.getPrice_usd() + "USD");
                vo.setPercent_change_24h(bqiTicker.getPercent_change_24h() + "%");
            } else {
                vo.setPrice("");
                vo.setPercent_change_24h("");
            }
        }
        PageInfo<ContentVo> pageInfo = new PageInfo<>(data);
        LOGGER.debug("Exit getContentsByCategory method");
        return pageInfo;
    }

    @Override
    public ContentVo getContents(String id) {
        if (StringUtils.isNotBlank(id)) {
            if (Tools.isNumber(id)) {
                ContentVo contentVo = contentDao.selectByPrimaryKey(Integer.valueOf(id));
                if (contentVo != null) {
                    contentVo.setHits(contentVo.getHits() + 1);
                    contentDao.updateByPrimaryKey(contentVo);
                }
                return contentVo;
            } else {
                ContentVoExample contentVoExample = new ContentVoExample();
                contentVoExample.createCriteria().andSlugEqualTo(id);
                List<ContentVo> contentVos = contentDao.selectByExampleWithBLOBs(contentVoExample);
                if (contentVos.size() != 1) {
                    throw new TipException("query content by id and return is not one");
                }
                return contentVos.get(0);
            }
        }
        return null;
    }

    @Override
    public void updateContentByCid(ContentVo contentVo) {
        if (null != contentVo && null != contentVo.getCid()) {
            contentDao.updateByPrimaryKeySelective(contentVo);
        }
    }

    @Override
    public PageInfo<ContentVo> getArticles(Integer mid, int page, int limit) {
        int total = metaDao.countWithSql(mid);
        PageHelper.startPage(page, limit);
        List<ContentVo> list = contentDao.findByCatalog(mid);
        PageInfo<ContentVo> paginator = new PageInfo<>(list);
        paginator.setTotal(total);
        return paginator;
    }

    @Override
    public PageInfo<ContentVo> getArticles(String keyword, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        ContentVoExample contentVoExample = new ContentVoExample();
        ContentVoExample.Criteria criteria = contentVoExample.createCriteria();
        criteria.andTypeEqualTo(Types.ARTICLE.getType());
        criteria.andStatusEqualTo(Types.PUBLISH.getType());
        criteria.andTitleLike("%" + keyword + "%");
        contentVoExample.setOrderByClause("created desc");
        List<ContentVo> contentVos = contentDao.selectByExampleWithBLOBs(contentVoExample);
        return new PageInfo<>(contentVos);
    }

    @Override
    public PageInfo<ContentVo> getArticlesWithpage(ContentVoExample commentVoExample, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<ContentVo> contentVos = contentDao.selectByExampleWithBLOBs(commentVoExample);
        return new PageInfo<>(contentVos);
    }

    @Override
    public void deleteByCid(Integer cid) {
        ContentVo contents = this.getContents(cid + "");
        if (null != contents) {
            contentDao.deleteByPrimaryKey(cid);
            relationshipService.deleteById(cid, null);
        }
    }

    @Override
    public void updateCategory(String ordinal, String newCatefory) {
        ContentVo contentVo = new ContentVo();
        contentVo.setCategories(newCatefory);
        ContentVoExample example = new ContentVoExample();
        example.createCriteria().andCategoriesEqualTo(ordinal);
        contentDao.updateByExampleSelective(contentVo, example);
    }

    @Override
    public void updateArticle(ContentVo contents) {
        if (null == contents || null == contents.getCid()) {
            throw new TipException("文章对象不能为空");
        }
        if (StringUtils.isBlank(contents.getTitle())) {
            throw new TipException("文章标题不能为空");
        }
        if (StringUtils.isBlank(contents.getContent())) {
            throw new TipException("文章内容不能为空");
        }
        if (contents.getTitle().length() > 200) {
            throw new TipException("文章标题过长");
        }
        if (contents.getContent().length() > 65000) {
            throw new TipException("文章内容过长");
        }
        if (null == contents.getAuthorId()) {
            throw new TipException("请登录后发布文章");
        }
        if (StringUtils.isBlank(contents.getSlug())) {
            contents.setSlug(null);
        }
        int time = DateKit.getCurrentUnixTime();
        contents.setModified(time);
        Integer cid = contents.getCid();
        contents.setContent(EmojiParser.parseToAliases(contents.getContent()));

        contentDao.updateByPrimaryKeySelective(contents);
        relationshipService.deleteById(cid, null);
        metasService.saveMetas(cid, contents.getTags(), Types.TAG.getType());
        metasService.saveMetas(cid, contents.getCategories(), Types.CATEGORY.getType());
    }
}
