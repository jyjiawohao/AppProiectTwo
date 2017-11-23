package com.project.appproiecttwo.global;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.project.appproiecttwo.R;
import com.project.appproiecttwo.utils.DialogUtil;
import com.project.appproiecttwo.utils.MyLogger;
import com.project.appproiecttwo.utils.NetWorkHelper;


/**
 * Created by lenovo on 2017/10/12.
 */

public abstract class HttpCallback extends StringCallback {

    private ProgressDialog dialog;
    private Context context;
    private boolean showDialog;

    /**
     * 是否有网络的监听 网络的监听
     */
    public interface NetworkCallback {
        void isNetwork();
    }

    public NetworkCallback mNetworkCallback;

    public void setNetworkCallback(NetworkCallback callback) {
        mNetworkCallback = callback;
    }

    /**
     * onError
     */
    public  interface ErrorNetworkCallback{
        void onErrorCallback();
    }
    private ErrorNetworkCallback mErrorNetworkCallback;

    public void setErrorCallback(ErrorNetworkCallback callback){
        mErrorNetworkCallback=callback;
    }



    public HttpCallback(boolean showDialog) {
        this.showDialog = showDialog;
        initDialog();
    }

    public HttpCallback(Context context, boolean showDialog) {
        this.context = context;
        this.showDialog = showDialog;
        initDialog();
    }

    private void initDialog() {
        dialog = DialogUtil.obtainProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage("请求网络中...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                onCancelRequest();
            }
        });
    }

    @Override
    public void onStart(Request<String, ? extends Request> request) {
        super.onStart(request);
        if (showDialog) {
            showDialog();
        }
    }

    @Override
    public void onFinish() {
        super.onFinish();
        onEnd();
    }


    private void onEnd() {
        dismissDialog();
    }


    /**
     * 取消请求
     */
    protected void onCancelRequest() {
        dismissDialog();
    }

    private void showDialog() {
        dialog.show();
    }

    private void dismissDialog() {
        dialog.dismiss();
    }


    @Override
    public void onSuccess(Response<String> response) {
        onEnd();
        if (response == null)
            return;
        MyLogger.e("yh","服务器数据==="+response.body());
       /* try {
            ResponseBean responseBean = jsonResponse(response);
            String status = responseBean.getStatus();

            if (status.equals(Constants.STATUS_OK)) {
                onSuccess(responseBean);
            } else if (status.equals(Constants.STATUS_TWO)) {
                onSuccess(responseBean);
            } else {
                onError(responseBean);
                if (mErrorNetworkCallback!=null)
                    mErrorNetworkCallback.onErrorCallback();
            }
        } catch (Exception e) {
            //ToastUtils.showToast("后台数据出错,请联系后台");
            e.printStackTrace();
        }*/
        onSuccess(response.body());
    }

    public abstract void onSuccess(String responseBean);

    @Override
    public void onError(Response<String> response) {
        super.onError(response);
        onEnd();
        MyLogger.e("yh","服务器连接出错=="+response.body());
        MyLogger.e("yh","服务器连接出错=="+response.toString());
        MyLogger.e("yh","服务器连接出错=="+response.getException());

        if (mNetworkCallback != null) {
            mNetworkCallback.isNetwork();
            return;
        }
        boolean networkAvailable = NetWorkHelper.isNetworkAvailable(App.sContext);
        String showContent="";
        if (networkAvailable){
            showContent="服务器连接出错,请稍后再试";
        }else{
            showContent="网络连接失败,请检查网络";
        }
            //TODO  responseBean.getMsg() 返回失败后台原因  "服务器连接出错,请稍后再试"
            DialogUtil.showDialog(context, context.getString(R.string.dialog_title),showContent,
                    context.getString(R.string.dialog_commit), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
    }
}

