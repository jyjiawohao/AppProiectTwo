package com.project.appproiecttwo.utils.xml;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/10/20.
 */

public class Person implements Serializable {
    private String app_id;
    private String version;
    private String kc_url;
    private String home_url;
    private String service_url;
    private String buttonArr;
    private String buttonImage;

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getKc_url() {
        return kc_url;
    }

    public void setKc_url(String kc_url) {
        this.kc_url = kc_url;
    }

    public String getHome_url() {
        return home_url;
    }

    public void setHome_url(String home_url) {
        this.home_url = home_url;
    }

    public String getService_url() {
        return service_url;
    }

    public void setService_url(String service_url) {
        this.service_url = service_url;
    }

    public String getButtonArr() {
        return buttonArr;
    }

    public void setButtonArr(String buttonArr) {
        this.buttonArr = buttonArr;
    }

    public String getButtonImage() {
        return buttonImage;
    }

    public void setButtonImage(String buttonImage) {
        this.buttonImage = buttonImage;
    }




}
