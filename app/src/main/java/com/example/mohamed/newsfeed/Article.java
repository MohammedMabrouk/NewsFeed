package com.example.mohamed.newsfeed;

public class Article {
    private String author;
    private String title;
    private String description;
    private String url;
    private String imageUrl;
    private String publishDate;

    public Article(){

    }

    public Article(String author,
            String title,
            String description,
            String url,
            String imageUrl,
            String publishDate){
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.imageUrl = imageUrl;
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

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public String getImageUrl(){
        return imageUrl;
    }

    public void setPublishDate(String publishDate){
        this.publishDate = publishDate;
    }

    public String getPublishDate() {
        return publishDate;
    }
}
