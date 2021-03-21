package com.cat.novocare.main_activity.edu_center;

import java.io.Serializable;

public class News_item implements Serializable {
    private final String title, caption, imageUrl, content, date;

    public News_item(String title, String caption, String imageUrl, String content, String date) {
        this.title = title;
        this.caption = caption;
        this.imageUrl = imageUrl;
        this.content = content;
        this.date = date;
    }


    public String getTitle() {
        return title;
    }


    public String getCaption() {
        return caption;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }
}
