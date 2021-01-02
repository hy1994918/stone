package com.kdmins.login.pojo;

import java.io.Serializable;

public class App implements Serializable {
    private String id;
    private String appName;
    private String appRemark;
    private static final long serialVersionUID = 1L;

    public App() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppRemark() {
        return this.appRemark;
    }

    public void setAppRemark(String appRemark) {
        this.appRemark = appRemark;
    }
}
