package com.project.appproiecttwo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.project.appproiecttwo.databinding.ActivityMainBinding;
import com.project.appproiecttwo.ui.FragmentFactory;
import com.project.appproiecttwo.utils.MyLogger;

import static com.project.appproiecttwo.utils.ToastUtil.showToast;


public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    private ActivityMainBinding mBinding;
    private BottomNavigationBar mBottomNavigationBar;
    private int[] titleIds = {R.string.One, R.string.Two, R.string.Three};
    private String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initButton();
        initBottomNavigation();
        initFirstFragment();
    }

    private void initButton() {
        mBottomNavigationBar = mBinding.bottomNavigationBar;
    }





    private void initBottomNavigation() {
        /**
         * MODE_DEFAULT
         如果Item的个数<=3就会使用MODE_FIXED模式，否则使用MODE_SHIFTING模式
         MODE_FIXED
         填充模式，未选中的Item会显示文字，没有换挡动画。
         MODE_SHIFTING
         换挡模式，未选中的Item不会显示文字，选中的会显示文字。在切换的时候会有一个像换挡的动画
         */
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);//设置背景风格
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);//设置样式

        BottomNavigationItem conversationItem = new BottomNavigationItem(R.drawable.zx01, titleIds[0]);
       /* mBadgeItem = new BadgeItem();
        mBadgeItem.setGravity(Gravity.RIGHT);
        mBadgeItem.setTextColor(getResources().getColor(R.color.white)); //设置徽章的颜色 背景
        mBadgeItem.setBackgroundColor(getResources().getColor(R.color.red));
        mBadgeItem.setText("");
        mBadgeItem.show();
        conversationItem.setBadgeItem(mBadgeItem);//绑定徽章*/
        mBottomNavigationBar.addItem(conversationItem);
        BottomNavigationItem contactItem = new BottomNavigationItem(R.drawable.sp01, titleIds[1]);
        mBottomNavigationBar.addItem(contactItem);

        BottomNavigationItem pluginItem = new BottomNavigationItem(R.drawable.my01, titleIds[2]);
        mBottomNavigationBar.addItem(pluginItem);

      /*  BottomNavigationItem home_four = new BottomNavigationItem(R.mipmap.home_title_ios, titleIds[3]);
        mBottomNavigationBar.addItem(home_four);

        BottomNavigationItem home_five = new BottomNavigationItem(R.mipmap.ic_nav_login, titleIds[4]);
        mBottomNavigationBar.addItem(home_five);*/

       mBottomNavigationBar.setActiveColor(R.color.green);//选中的颜色  (只需要在这里设置一次,就不用每次都设置了)
        // mBottomNavigationBar.setInActiveColor(R.color.inActive);//没选中的颜色
        mBottomNavigationBar.initialise(); //初始化
        mBottomNavigationBar.setTabSelectedListener(this); //设置监听
        // mBottomNavigationBar.setFirstSelectedPosition(1); //设置默认被选中的位置 默认是0
    }


    /**
     * 创建 fragment  解决重影问题
     */
    private void initFirstFragment() {
        /**重影  的Fragment
         * 如果这个Activity中已经有（就是Activity保存的历史的状态，又恢复了）老的Fragment，先全部移除
         * 重新加载过了 fragment  在onCreate(Bundle savedInstanceState) 的savedInstanceState里面保存了数据 activity又没有销毁
         * 这个我们重新启动这个activity 就会重新走onCreate, 就会重新重影
         */
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        for (int i = 0; i < titleIds.length; i++) {
            Fragment fragment = supportFragmentManager.findFragmentByTag(i + ""); //用tag 找到记录每个fragment
            if (fragment != null)    //判断下每个fragment是否已经创建过了,如果已经创建过了就移除掉
                fragmentTransaction.remove(fragment);
        }
        fragmentTransaction.commit();//提交事务
        getSupportFragmentManager().beginTransaction().add(R.id.fl_content, FragmentFactory.getFragment(0), "0").commit();
        //mTv_title.setText(titleIds[0]); //设置标题
    }

    /**
     * BottomNavigationBar 状态 选中的
     *
     * @param position
     */
    @Override
    public void onTabSelected(int position) {
        /**
         * 先判断当前Fragment是否被添加到了MainActivity中
         * 如果添加了则直接显示即可
         * 如果没有添加则添加，然后显示
         */
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = FragmentFactory.getFragment(position);//根据bottom点击的位置获取对应的fragment布局
        MyLogger.e(TAG, "==fragment.isAdded()==" + fragment.isAdded());
        if (!fragment.isAdded())
            fragmentTransaction.add(R.id.fl_content, fragment, "" + position);//判断当前视图是否已经添加如果没有添加就 添加  同时添加tag 做标记
        fragmentTransaction.show(fragment).commit();
        //mTv_title.setText(titleIds[position]); //设置标题
    }

    /**
     * 未选中的隐藏掉视图
     *
     * @param position
     */
    @Override
    public void onTabUnselected(int position) {
        getSupportFragmentManager().beginTransaction().hide(FragmentFactory.getFragment(position)).commit();
    }

    /**
     * 又选择了 不做操作
     *
     * @param position
     */
    @Override
    public void onTabReselected(int position) {

    }

    /**
     * 监听返回--是否退出程序
     */
    private long exitTime = 0;
    public boolean onKeyDown(int keyCode, KeyEvent event) {

       /* if (JZVideoPlayer.backPress()) {
            return true;
        }*/

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                showToast("再按一次退出程序！");
                exitTime = System.currentTimeMillis();
            } else {
                this.finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



   /* @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }*/


}
