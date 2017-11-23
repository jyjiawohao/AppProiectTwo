package com.project.appproiecttwo;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.just.library.AgentWeb;
import com.just.library.ChromeClientCallbackManager;
import com.project.appproiecttwo.bean.SplashBean;
import com.project.appproiecttwo.utils.ToastUtil;
import com.project.appproiecttwo.utils.glide.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/10/30.
 */

public class WebActivity extends AppCompatActivity implements View.OnClickListener {


    protected AgentWeb mAgentWeb;
    private ProgressDialog mProgressDialog;
    private AlertDialog mAlertDialog;
    private   SplashBean.DataBean mBean;
    private RelativeLayout mLayoutHome;
    private RelativeLayout mLayoutTwo;
    private RelativeLayout mLayoutThree;
    private RelativeLayout mLayoutThree04;
    private RelativeLayout mLayoutThree05;
    private TextView mTvHome;
    private TextView mTvTwo;
    private TextView mTvThree;
    private TextView mTvThree04;
    private TextView mTvThree05;
    private ImageView mIvHome;
    private ImageView mIvTwo;
    private ImageView mIvThree;
    private ImageView mIvThree04;
    private ImageView mIvThree05;
    private LinearLayout mLinl;

    public static void newInstance(Context context, SplashBean.DataBean person) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("bean", person);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.red));
        }

        setContentView(R.layout.activity_web);

        initView();
        initWebView();
    }

    private void initView() {
        mLayoutHome = (RelativeLayout) findViewById(R.id.layout_home);
        mLayoutTwo = (RelativeLayout) findViewById(R.id.layout_two);
        mLayoutThree = (RelativeLayout) findViewById(R.id.layout_three);
        mLayoutThree04 = (RelativeLayout) findViewById(R.id.layout_three04);
        mLayoutThree05 = (RelativeLayout) findViewById(R.id.layout_three05);


        mLayoutHome.setOnClickListener(this);
        mLayoutTwo.setOnClickListener(this);
        mLayoutThree.setOnClickListener(this);
        mLayoutThree04.setOnClickListener(this);
        mLayoutThree05.setOnClickListener(this);
        mBean = (SplashBean.DataBean) getIntent().getSerializableExtra("bean");
        //设置button名字
        String buttonArr = mBean.getButtonArr();
        String[] split1 = buttonArr.split(",");

        mTvHome = (TextView) findViewById(R.id.tv_home);
        mTvTwo = (TextView) findViewById(R.id.tv_two);
        mTvThree = (TextView) findViewById(R.id.tv_three);
        mTvThree04 = (TextView) findViewById(R.id.tv_three04);
        mTvThree05 = (TextView) findViewById(R.id.tv_three05);

        mTvHome.setText(split1[0]);
        mTvTwo.setText(split1[1]);
        mTvThree.setText(split1[2]);
        mTvThree04.setText(split1[3]);
        mTvThree05.setText(split1[4]);

        mIvHome = (ImageView) findViewById(R.id.iv_home);
        mIvTwo = (ImageView) findViewById(R.id.iv_two);
        mIvThree = (ImageView) findViewById(R.id.iv_three);
        mIvThree04 = (ImageView) findViewById(R.id.iv_three04);
        mIvThree05 = (ImageView) findViewById(R.id.iv_three05);
        mLinl = (LinearLayout) findViewById(R.id.linl);

        //设置图片
        String buttonImage = mBean.getButtonImage();
        String[] split = buttonImage.split(",");
        ImageUtils.setImage(this, split[0], mIvHome);
        ImageUtils.setImage(this, split[1], mIvTwo);
        ImageUtils.setImage(this, split[2], mIvThree);
        ImageUtils.setImage(this, split[3], mIvThree04);
        ImageUtils.setImage(this, split[4], mIvThree05);

    }

    private void initWebView() {
        showProgressBarDialog();


        //初始化首页
        setWebView(mBean.getHome_url());
        initBottomMenu();
    }

    private void setWebView(String bean) {
        if (mAgentWeb == null) {
            mAgentWeb = AgentWeb.with(this)//传入Activity or Fragment
                    .setAgentWebParent(mLinl, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
                    .useDefaultIndicator()// 使用默认进度条
                    .defaultProgressBarColor() // 使用默认进度条颜色
                    .setReceivedTitleCallback(new ChromeClientCallbackManager.ReceivedTitleCallback() {
                        @Override
                        public void onReceivedTitle(WebView view, String title) {

                        }
                    }) //设置 Web 页面的 title 回调
                    .setWebChromeClient(mWebChromeClient)
                    //.setWebViewClient(mWebViewClient)
                    .setSecutityType(AgentWeb.SecurityType.strict)
                    .createAgentWeb()//
                    .ready()
                    .go(bean);
        } else {
            /*WebView mWebView=mAgentWeb.getWebCreator().get();
            mWebView.loadUrl(bean);*/
            mAgentWeb.getLoader().loadUrl(bean);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_home://主页
                setWebView(mBean.getHome_url());
                mTvHome.setClickable(true);
                checkTvMenuIsSelected(mTvHome);
                break;
            case R.id.layout_two: //后退
                setWebView(mBean.getHome_url());
                checkTvMenuIsSelected(mTvThree05);
                break;
            case R.id.layout_three: //客服
                setWebView(mBean.getService_url());
                checkTvMenuIsSelected(mTvThree);
                break;
            case R.id.layout_three04://快充
                setWebView(mBean.getKc_url());
                checkTvMenuIsSelected(mTvThree04);
                break;
            case R.id.layout_three05: //刷新
                setWebView(mBean.getHome_url());
                checkTvMenuIsSelected(mTvThree05);
                break;
            default:
                break;
        }

    }

    /**
     * 初始化主界面底部导航
     */
    private List<TextView> tvMenu;
    private List<ImageView> ivMenu;

    private void initBottomMenu() {
        tvMenu = new ArrayList<>();
        ivMenu = new ArrayList<>();
        // 添加底部导航文字
        tvMenu.add(mTvHome);
        tvMenu.add(mTvTwo);
        tvMenu.add(mTvThree);
        tvMenu.add(mTvThree04);
        tvMenu.add(mTvThree05);

        // tvMenu.add(mTvFive);
        // 添加底部导航图片
       /* ivMenu.add(mBinding.bottom.ivHome);
        ivMenu.add(mBinding.bottom.ivTwo);
        ivMenu.add(mBinding.bottom.ivThree);*/
        // 选中首页
        mTvHome.setSelected(true);
        //mBinding.ivHome.setSelected(true);
    }

    /**
     * 检查底部导航栏图标是否选中
     *
     * @param target
     */
    private void checkTvMenuIsSelected(TextView target) {
        for (TextView tv : tvMenu) {
            if (tv == target)
                tv.setSelected(true);
            else
                tv.setSelected(false);
        }
    }


    private void showProgressBarDialog() {
        if (mProgressDialog == null)
            mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("请稍等,网络请求中");
        mProgressDialog.setCanceledOnTouchOutside(false); // 点击加载框以外的区域
        mProgressDialog.show();
    }

    private void showDialog() {

        if (mAlertDialog == null)
            mAlertDialog = new AlertDialog.Builder(this)
                    .setMessage("您确定要关闭该页面吗?")
                    .setNegativeButton("再逛逛", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (mAlertDialog != null)
                                mAlertDialog.dismiss();
                        }
                    })//
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (mAlertDialog != null)
                                mAlertDialog.dismiss();
                            finish();
                        }
                    }).create();
        mAlertDialog.show();

    }

    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            //do you work
            Log.i("Info", "progress:" + newProgress);
            if (newProgress == 100) {
                //mImageView.setVisibility(View.GONE);
                mProgressDialog.dismiss();
            }

        }
    };
    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //do you  work
        }
    };


    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mAgentWeb.uploadFileResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mAgentWeb.destroy();
        mAgentWeb.getWebLifeCycle().onDestroy();
    }


    /**
     * 监听返回--是否退出程序
     */
    private long exitTime = 0;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtil.showToast("再按一次退出程序！");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
