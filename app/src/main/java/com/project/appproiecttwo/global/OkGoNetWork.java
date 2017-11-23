package com.project.appproiecttwo.global;

import com.lzy.okgo.OkGo;

import java.io.File;

/**
 * Created by lenovo on 2017/10/12.
 */
public class OkGoNetWork {
    public static void News(Object tag, String news, HttpCallback callback) {
        OkGo.<String>post(news)
                .tag(tag)
                .execute(callback);
    }

    public static void aetails(Object tag, String news, int id, HttpCallback callback) {
        OkGo.<String>post(news)
                .tag(tag)
                .params("id",id)
                .execute(callback);
    }

    /**
     * 验证用户登录接口
     * @param tag
     * @param checklogin
     * @param dopost
     * @param user
     * @param password
     */
    public static void checklogin(Object tag, String checklogin, String dopost, String user, String password, HttpCallback callback) {
        OkGo.<String>post(checklogin)
                .tag(tag)
                .params("dopost",dopost)
                .params("username",user)
                .params("password",password)
                .execute(callback);
    }

    /**
     * 注册用户
     * @param tag
     * @param register
     * @param dopost
     * @param user
     * @param password
     * @param callback
     */
    public static void register(Object tag, String register, String dopost, String user, String password, HttpCallback callback) {
        OkGo.<String>post(register)
                .tag(tag)
                .params("dopost",dopost)
                .params("username",user)
                .params("password",password)
                .execute(callback);
    }


    /**
     * 上传头像
     * @param tag
     * @param userface
     * @param uploadimg
     * @param uid
     * @param file
     * @param callback
     */
    public static void userface(Object tag, String userface, String uploadimg, String uid, File file, HttpCallback callback) {
        OkGo.<String>post(userface)
                .tag(tag)
                .params("dopost",uploadimg)
                .params("uid",uid)
                .params("file",file)
                .execute(callback);
    }

    /**
     * 意见反馈
     * @param tag
     * @param checklogin
     * @param addinfo
     * @param id
     * @param etfd
     * @param callback
     */
    public static void freeback(Object tag, String checklogin, String addinfo, String id, String etfd, HttpCallback callback) {
        OkGo.<String>post(checklogin)
                .tag(tag)
                .params("dopost",addinfo)
                .params("uid",id)
                .params("content",etfd)
                .execute(callback);
    }

    /**
     * 获取赛事数据接口
     * @param tag
     * @param url
     * @param dopost
     * @param callback
     */
    public static void getscore(Object tag, String url, String dopost, HttpCallback callback) {
        OkGo.<String>post(url)
                .tag(tag)
                .params("dopost",dopost)
                .execute(callback);
    }

    /**
     * 积分
     * @param tag
     * @param url
     * @param dopost
     * @param callback
     */
    public static void getranks(Object tag, String url, String dopost, HttpCallback callback) {
        OkGo.<String>post(url)
                .tag(tag)
                .params("dopost",dopost)
                .execute(callback);
    }

    public static void sendmsg(Object tag, String url, String dopost, HttpCallback callback) {
        OkGo.<String>post(url)
                .tag(tag)
                .params("dopost",dopost)
                .execute(callback);
    }

    /**
     * 发送评论
     * @param tag
     * @param sendmsg
     * @param send
     * @param username
     * @param userId
     * @param mEtContent
     * @param id
     * @param callback
     */
    public static void sendmsg(Object tag
            , String sendmsg
            , String send
            , String username
            , String userId
            , String mEtContent
            , String id
            ,HttpCallback callback) {

        OkGo.<String>post(sendmsg)
                .tag(tag)
                .params("dopost",send)
                .params("username",username)
                .params("content",mEtContent)
                .params("nid",id)
                .params("uid",userId)
                .execute(callback);
    }


    public static void videolist(Object tag, String news, String dopost, HttpCallback callback) {
        OkGo.<String>post(news)
                .tag(tag)
                .params("dopost",dopost)
                .execute(callback);
    }
}
