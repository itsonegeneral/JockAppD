package com.rstudio.jockapp.models;


import java.io.Serializable;

/**
 * Developed by Rithik S (tubeviral88@gmail.com)
 * GitHub /itsonegeneral
 */
public class Jock implements Serializable {
    String JockName;
    String JockBody;
    String type;
    long timestamp;

    public Jock() {
    }

    public Jock(String JockName, String JockBody, String type, long timestamp) {
        this.JockName = JockName;
        this.JockBody = JockBody;
        this.type = type;
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getJockName() {
        return JockName;
    }

    public void setJockName(String jockName) {
        JockName = jockName;
    }

    public String getJockBody() {
        return JockBody;
    }

    public void setJockBody(String jockBody) {
        JockBody = jockBody;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
