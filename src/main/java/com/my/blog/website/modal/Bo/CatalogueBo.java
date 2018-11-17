package com.my.blog.website.modal.Bo;

import com.my.blog.website.modal.Vo.ContentVo;

import java.util.List;

public class CatalogueBo {

    private String categories;
    //    private String count;
    private List<ContentVo> articles;

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public List<ContentVo> getArticles() {
        return articles;
    }

    public void setArticles(List<ContentVo> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "CatalogueBo{" +
                "categories='" + categories + '\'' +
                ", articles=" + articles +
                '}';
    }
}
