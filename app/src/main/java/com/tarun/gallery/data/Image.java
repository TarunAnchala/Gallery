package com.tarun.gallery.data;


import com.google.gson.annotations.SerializedName;

/**
 * Pojo Object of Image Response
 */
public class Image {

    private String author;

    private String width;

    @SerializedName("download_url")
    private String downloadUrl;

    private String id;

    private String url;

    private String height;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "ClassPojo [author = " + author + ", width = " + width + ", download_url = " + downloadUrl + ", id = " + id + ", url = " + url + ", height = " + height + "]";
    }
}
