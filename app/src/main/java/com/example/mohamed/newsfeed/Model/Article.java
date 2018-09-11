package com.example.mohamed.newsfeed.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Article implements Serializable {
    private String author;
    private String title;
    private String description;
    private String url;
    @SerializedName("urlToImage")
    private String imgUrl;
    @SerializedName("publishedAt")
    private String publishDate;

    public Article(){}

    public Article(String author,
            String title,
            String description,
            String url,
            String imgUrl,
            String publishDate){
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.imgUrl = imgUrl;
        this.publishDate = publishDate;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getAuthor(){
        return author;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getUrl(){
        return url;
    }

    public void setImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }

    public String getImgUrl(){
        return imgUrl;
    }

    public void setPublishDate(String publishDate){
        this.publishDate = publishDate;
    }

    public String getPublishDate() {
        return publishDate;
    }
}
