package com.rstudio.jockapp.models;
import java.io.Serializable;

/**
 * Created by munchado on 2/1/18.
 */

public class JockImage implements Serializable {
    private String imageName;
    private String type;
    private String downloadUrl;
    long timestamp;

    public JockImage() {
    }

    public JockImage(String imageName, String downloadUrl, String type, long timestamp) {
        this.imageName = imageName;
        this.downloadUrl = downloadUrl;
        this.type = type;
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }



    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

}
