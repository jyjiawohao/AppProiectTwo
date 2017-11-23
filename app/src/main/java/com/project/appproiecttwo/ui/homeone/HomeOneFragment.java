package com.project.appproiecttwo.ui.homeone;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.appproiecttwo.R;
import com.project.appproiecttwo.databinding.FragmentHomeOneBinding;
import com.project.appproiecttwo.mvp.MVPBaseFragment;
import com.project.appproiecttwo.utils.GroupFragmentPagerAdapterUtil;

import java.util.ArrayList;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class HomeOneFragment extends MVPBaseFragment<HomeOneContract.View, HomeOnePresenter> implements HomeOneContract.View {
    private FragmentHomeOneBinding mBinding;
    private ArrayList<Fragment> mFragmentArrayList;
    private ArrayList<String> mStringList;
    private GroupFragmentPagerAdapterUtil mAdapterUtil;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_one, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {

        mFragmentArrayList = new ArrayList<>();
        mStringList = new ArrayList<>();
        mStringList.add("推荐");
        mStringList.add("热点");
        mStringList.add("体育");
        mStringList.add("赛事");
        mStringList.add("数据");

      /* //TODO  没有数据

       mFragmentArrayList.add(TuiJianFragment.newInstance("1", data));
        mFragmentArrayList.add(TuiJianFragment.newInstance("2", data));
        mFragmentArrayList.add(TuiJianFragment.newInstance("3", data));

        mFragmentArrayList.add(EventFragment.newInstance("4", data));
        mFragmentArrayList.add(DataFragment.newInstance("5", data));

        for (int i = 0; i < mStringList.size(); i++) {
            mBinding.mTabLayout.addTab(mBinding.mTabLayout.newTab().setText(mStringList.get(i)));
        }
        mAdapterUtil = new GroupFragmentPagerAdapterUtil(getChildFragmentManager(), mFragmentArrayList, mStringList);
        mBinding.mViewPager.setOffscreenPageLimit(5); //设置预加载个数
        mBinding.mViewPager.setAdapter(mAdapterUtil);
        mBinding.mViewPager.setCurrentItem(0);
        mBinding.mTabLayout.setupWithViewPager(mBinding.mViewPager);
        //设置可以滑动
        mBinding.mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);*/
    }
}
