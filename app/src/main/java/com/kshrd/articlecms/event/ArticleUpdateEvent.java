package com.kshrd.articlecms.event;

import com.kshrd.articlecms.entity.ArticleResponse;

/**
 * Created by pirang on 6/16/17.
 */

public class ArticleUpdateEvent {

    private ArticleResponse.Article article;

    public ArticleUpdateEvent(ArticleResponse.Article article) {
        this.article = article;
    }

    public ArticleResponse.Article getArticle() {
        return article;
    }

    public void setArticle(ArticleResponse.Article article) {
        this.article = article;
    }
}
