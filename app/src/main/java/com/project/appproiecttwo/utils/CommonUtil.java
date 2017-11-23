package com.project.appproiecttwo.utils;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.TextView;

import com.project.appproiecttwo.global.App;


/**
 * 在没有上下文的方法里面获取上下文
 */
public class CommonUtil {
    /**
     * 在主线程执行一段任务
     *
     * @param
     */
    public static void runOnUIThread(Runnable r) {
        App.getHandler().post(r);  //调用 Application 里面的handler

    }
    /**
     * 获取当前系统时间
     * @return
     */
    public static long getTime() {
        return  System.currentTimeMillis();// 获取时间戳
    }
    /**
     * 移除子View
     *
     * @param child
     */

    public static void removeSelfFromParent(View child) {
        if (child != null) {
            ViewParent parent = child.getParent();
            if (parent != null && parent instanceof ViewGroup) {
                ViewGroup group = (ViewGroup) parent;
                group.removeView(child);//移除子View
            }
        }
    }

    /**
     * dip转为PX
     */
    public static int dip2px(Context context, float dipValue) {
        float fontScale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * fontScale + 0.5f);
    }

    /**
     * 根据全局的上下文
     * 获取资源目录的图片
     */
    public static Drawable getDrawable(int id) {
        return App.sContext.getResources().getDrawable(id);
    }

    /**
     * 字符串
     */
    public static String getString(int id) {
        return App.sContext.getResources().getString(id);
    }

    /**
     * 数组
     *
     * @param id
     * @return
     */
    public static String[] getStringArray(int id) {
        return App.sContext.getResources().getStringArray(id);
    }

    /**
     * 颜色
     */
    public static int getColor(int id) {
        return App.sContext.getResources().getColor(id);
    }

    /**
     * 获取dp资源，并且会自动将dp值转为px值
     *
     * @param id
     * @return
     */
    public static int getDimens(int id) {
        return App.sContext.getResources().getDimensionPixelSize(id);
    }

    /**
     * 测量View的宽高
     *
     * @param view View
     */
    public static void measureWidthAndHeight(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
    }


    /**
     * android:drawableLeft="@drawable/"  设置 图片
     *
     * @param view {@link TextView}
     * @param id   图片
     * @param type 设置 状态 就是图片显示的位置  1 左 2上 3右  4下  其他默认: 右边图标
     */
    public static void TextViewDrawable(TextView view, int id, int type) {
        Drawable rightDrawable = getDrawable(id);
        rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
        switch (type) {
            case 1:  //1 左 2上 3右  4下
                view.setCompoundDrawables(rightDrawable, null, null, null);
                break;
            case 2:   //1 左 2上 3右  4下
                view.setCompoundDrawables(null, rightDrawable, null, null);
                break;
            case 3:      //1 左 2上 3右  4下
                view.setCompoundDrawables(null, null, rightDrawable, null);
                break;
            case 4:      //1 左 2上 3右  4下
                view.setCompoundDrawables(null, null, null, rightDrawable);
                break;
            default:
                view.setCompoundDrawables(null, null, rightDrawable, null);
                break;
        }
    }

    /**
     * android:drawableLeft="@drawable/"  设置 图片
     * @param view {@link TextView}
     * @param id   图片
     * @param type 设置 状态 就是图片显示的位置  1 左 2上 3右  4下  其他默认: 右边图标
     */
    public static void EditTextDrawable(EditText view, int id, int type) {
        Drawable rightDrawable = getDrawable(id);
        rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
        switch (type) {
            case 1:  //1 左 2上 3右  4下
                view.setCompoundDrawables(rightDrawable, null, null, null);
                break;
            case 2:   //1 左 2上 3右  4下
                view.setCompoundDrawables(null, rightDrawable, null, null);
                break;
            case 3:      //1 左 2上 3右  4下
                view.setCompoundDrawables(null, null, rightDrawable, null);
                break;
            case 4:      //1 左 2上 3右  4下
                view.setCompoundDrawables(null, null, null, rightDrawable);
                break;
            default:
                view.setCompoundDrawables(null, null, rightDrawable, null);
                break;
        }
    }
}
