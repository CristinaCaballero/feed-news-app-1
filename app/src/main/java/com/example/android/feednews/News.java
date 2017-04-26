package com.example.android.feednews;

public class News {

    private String mTitle;
    private String mDescription;
    private String mImageResource;
    private String mHoursPast;
    private String mAuthor;
    private String mUrl;
    private String mCategory;

    public News(String title, String description, String imageResource, String hoursPast, String author, String category, String url) {
        mTitle = title;
        mHoursPast = hoursPast;
        mDescription = description;
        mImageResource = imageResource;
        mAuthor = author;
        mUrl = url;
        mCategory = category;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getImageResource() {
        return mImageResource;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getHoursPast() {
        return mHoursPast;
    }

    public String getCategory() {
        return mCategory;
    }

    public String getUrl() {
        return mUrl;
    }

}
