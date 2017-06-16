package com.kshrd.articlecms.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pirang on 6/16/17.
 */

public class UpdateArticleResponse {

    @SerializedName("CODE")
    private String code;
    @SerializedName("MESSAGE")
    private String message;
    @SerializedName("DATA")
    private ArticleResponse.Article article;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArticleResponse.Article getArticle() {
        return article;
    }

    public void setArticle(ArticleResponse.Article article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "UpdateArticleResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", article=" + article +
                '}';
    }
}
