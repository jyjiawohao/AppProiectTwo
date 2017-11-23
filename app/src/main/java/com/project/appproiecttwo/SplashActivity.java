package com.project.appproiecttwo;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lzy.okgo.OkGo;
import com.project.appproiecttwo.bean.SplashBean;
import com.project.appproiecttwo.global.HttpCallback;
import com.project.appproiecttwo.utils.JsonUtil;
import com.project.appproiecttwo.utils.MyLogger;
import com.tbruyelle.rxpermissions2.RxPermissions;

/**
 * Created by lenovo on 2017/10/17.
 */

public class SplashActivity extends AppCompatActivity {

    private RxPermissions mRxPermissions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       initNewNetWork();
    }

    private void initNewNetWork() {
        OkGo.<String>post("http://142.4.109.25:8001/serverapian.php")
                .params("app_id", 201711212106L)
                .execute(new HttpCallback(this, false) {
                    @Override
                    public void onSuccess(String responseBean) {
                        SplashBean bean = JsonUtil.parseJsonToBean(responseBean, SplashBean.class);
                        MyLogger.i("bs", responseBean);
                        if (bean.getData().getVersion().equals("1.0")) {
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else if (bean.getData().getVersion().equals("2.0")) {

                            WebActivity.newInstance(SplashActivity.this, bean.getData());
                        }
                        finish();
                    }
                });
    }

}
