package com.project.appproiecttwo.ui.hometwo;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.appproiecttwo.R;
import com.project.appproiecttwo.databinding.FragmentHomeTwoBinding;
import com.project.appproiecttwo.mvp.MVPBaseFragment;
import com.project.appproiecttwo.utils.CommonUtil;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class HomeTwoFragment extends MVPBaseFragment<HomeTwoContract.View, HomeTwoPresenter> implements HomeTwoContract.View {
    private FragmentHomeTwoBinding mBinding;

    private LinearLayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_two, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
        initSwipeRefreshLayout();
        initView();
    }

    private void initView() {

    }


    private void initRecyclerView() {

        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mBinding.mRecyclerView.setLayoutManager(mLayoutManager);
        mBinding.mRecyclerView.setNestedScrollingEnabled(true);

      /*  mAdapter = new HomeTwoAdapter(getContext());
        mBinding.mRecyclerView.setAdapter(mAdapter);*/
    }
    /**
     * 下拉刷新
     */
    private void initSwipeRefreshLayout() {
        mBinding.mSwipeRefreshLayout.setColorSchemeColors(CommonUtil.getColor(R.color.red));
        mBinding.mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mBinding.mSwipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        mBinding.mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }
}
