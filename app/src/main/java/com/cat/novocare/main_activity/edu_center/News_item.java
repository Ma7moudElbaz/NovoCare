package com.cat.novocare.main_activity.edu_center;

public class News_item {
    private final int newsId;
    private final String title, slug, caption, imageUrl, content, date;

    public News_item(int newsId, String title, String slug, String caption, String imageUrl, String content, String date) {
        this.newsId = newsId;
        this.title = title;
        this.slug = slug;
        this.caption = caption;
        this.imageUrl = imageUrl;
        this.content = content;
        this.date = date;
    }

    public int getNewsId() {
        return newsId;
    }

    public String getTitle() {
        return title;
    }

    public String getSlug() {
        return slug;
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
