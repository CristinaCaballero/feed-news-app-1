package com.example.android.feednews;

public class News {
    private String mId;
    private String mTitle;
    private String mImageResource;
    private String mHoursPast;

    public News(String id,String title, String imageResource, String hoursPast) {
        mId = id;
        mTitle = title;
        mHoursPast = hoursPast;
        mImageResource = imageResource;
    }

    public String getmId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getImageResource() {
        return mImageResource;
    }

    public String getHoursPast() {
        return mHoursPast;
    }
}
