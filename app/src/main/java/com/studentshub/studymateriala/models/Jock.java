package com.studentshub.studymateriala.models;


import java.io.Serializable;

/**
 * Developed by Rithik S (tubeviral88@gmail.com)
 * GitHub /itsonegeneral
 */
public class Jock implements Serializable {
    String JockName;
    String JockBody;
    String type;
    String jockTitle;

    long timestamp;

    public Jock() {
    }

    public Jock(String JockName, String JockBody, String type, long timestamp,String jockTitle) {
        this.JockName = JockName;
        this.JockBody = JockBody;
        this.type = type;
        this.timestamp = timestamp;
        this.jockTitle = jockTitle;
    }


    public String getJockTitle() {
        return jockTitle;
    }

    public void setJockTitle(String jockTitle) {
        this.jockTitle = jockTitle;
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
