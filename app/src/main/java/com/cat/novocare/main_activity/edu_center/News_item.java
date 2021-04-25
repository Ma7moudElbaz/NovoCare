package com.cat.novocare.main_activity.edu_center;

import java.io.Serializable;

public class News_item implements Serializable {
    private final String title, caption, imageUrl,videoUrl, content, date;

    public News_item(String title, String caption, String imageUrl, String videoUrl, String content, String date) {
        this.title = title;
        this.caption = caption;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
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

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }
}
