package com.project.appproiecttwo.utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;


/**
 * @author fighter
 * @version 1.0.1
 * @创建时间: 2017/3/14
 * @修改时间:
 */

public class DialogUtil {
    private DialogUtil() {
        throw new RuntimeException();
    }

    public static final ProgressDialog obtainProgressDialog(Context context) {
        ProgressDialog dialog = new ProgressDialog(context);
        return dialog;
    }

    /**
     *  @param activity  上下文
     * @param dialog
     * @param height   高度设置为屏幕的0.3
     */
    public static void setDialogWindoManager(Activity activity, AlertDialog dialog, double height) {
        WindowManager m = activity.getWindowManager();
        Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
        WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
        p.height = (int) (d.getHeight() * height);   //高度设置为屏幕的0.3
        //p.width = (int) (d.getWidth() *width);    //宽度设置为屏幕的0.5
        p.gravity = Gravity.CENTER_HORIZONTAL;
        dialog.getWindow().setAttributes(p);     //设置生效
    }
    /**
     *
     * @param context  上下文
     * @param title  标题
     * @param msg   内容
     * @param positive   确定
     * @param posiListener    确定的监听
     * @param cancel   取消
     * @param cancelListener  取消的监听
     * @param cancelable  是否可以外包取消
     * @return
     */
    public static final Dialog showDialog(Context context, String title, CharSequence msg, String positive,
                                          DialogInterface.OnClickListener posiListener,
                                          String cancel, DialogInterface.OnClickListener cancelListener, boolean cancelable) {
        try {
            if (!TextUtils.isEmpty(msg)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle(title)
                        .setMessage(msg)
                        .setCancelable(cancelable)
                        .setPositiveButton(
                                positive, posiListener);
                if (!TextUtils.isEmpty(cancel)) {
                    builder.setNegativeButton(cancel, cancelListener);
                }
                return showColor(builder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @param context  上下文
     * @param title  标题
     * @param msg   内容
     * @param positive   确定
     * @param posiListener    确定的监听
     * @param cancelable  是否可以外包取消
     */
    public static final Dialog showDialog(Context context, String title, CharSequence msg, String positive,
                                          DialogInterface.OnClickListener posiListener, boolean cancelable) {
        try {
            if (!TextUtils.isEmpty(msg)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle(title)
                        .setMessage(msg)
                        .setCancelable(cancelable)
                        .setPositiveButton(
                                positive, posiListener);
                return showColor(builder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    public static final Dialog showDialog(Context context, String title, CharSequence msg, String positive,
                                          DialogInterface.OnClickListener posiListener) {
        try {
            if (!TextUtils.isEmpty(msg)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle(title)
                        .setMessage(msg)
                        .setCancelable(true)
                        .setPositiveButton(
                                positive, posiListener);
                return showColor(builder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static AlertDialog show(AlertDialog.Builder builder) {
        AlertDialog dialog = builder.show();
        Button btnPositive = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        int btnSize = ScreenUtil.getScalePxValue(42);
        int textSize = ScreenUtil.getScalePxValue(46);
        try {
            TextView title = (TextView) dialog.findViewById(android.support.v7.appcompat.R.id.alertTitle);
            title.setTextSize(TypedValue.COMPLEX_UNIT_PX, ScreenUtil.getScalePxValue(60));
        } catch (Exception e) {
        }
        if (btnPositive != null) {
//            btnPositive.setTextColor(btnPositive.getResources().getColor(R.color.color_0b85b6));
            btnPositive.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnSize);
        }

        Button btnNegative = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        if (btnNegative != null) {
//            btnNegative.setTextColor(Color.parseColor("#ff4283"));
            btnNegative.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnSize);
        }

        Button btnNeu = dialog.getButton(DialogInterface.BUTTON_NEUTRAL);
        if (btnNeu != null) {
            btnNeu.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnSize);
        }

        TextView textView = (TextView) dialog
                .findViewById(android.R.id.message);
        if (textView != null) {
            textView.setTextColor(Color.parseColor("#747474"));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }

        return dialog;
    }


    public static Dialog showDialogList(Context context, String title, ListAdapter adapter,
                                        DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(title)
                .setAdapter(adapter, listener);
        return builder.show();
    }


    public static AlertDialog showColor(AlertDialog.Builder builder) {
        AlertDialog dialog = builder.show();
        Button btnPositive = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        int btnSize = ScreenUtil.getScalePxValue(42);
        int textSize = ScreenUtil.getScalePxValue(46);
        try {
            TextView title = (TextView) dialog.findViewById(android.support.v7.appcompat.R.id.alertTitle);
            title.setTextColor(Color.parseColor("#14191d"));
            title.setTextSize(TypedValue.COMPLEX_UNIT_PX, ScreenUtil.getScalePxValue(60));
        } catch (Exception e) {
        }
        if (btnPositive != null) {
            btnPositive.setTextColor(Color.parseColor("#53a9ff"));
            btnPositive.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnSize);
        }

        Button btnNegative = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        if (btnNegative != null) {
            btnNegative.setTextColor(Color.parseColor("#ff4283"));
            btnNegative.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnSize);
        }

        TextView textView = (TextView) dialog
                .findViewById(android.R.id.message);
        if (textView != null) {
            textView.setTextColor(Color.parseColor("#747474"));
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }
        return dialog;
    }


}

