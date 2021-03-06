package com.project.appproiecttwo.utils.glide;


import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.project.appproiecttwo.utils.TCUtils;

import java.security.MessageDigest;

/**
 * Created by lenovo on 2017/7/28.
 */

public class TCGlideCircleTransform extends BitmapTransformation {
    public TCGlideCircleTransform(Context context) {
        super(context);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return TCUtils.createCircleImage(toTransform, 0);
    }



    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {

    }
}
