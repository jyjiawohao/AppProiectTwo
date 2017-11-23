package com.project.appproiecttwo.utils.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by li on 2016/11/13.
 */

/*
* 在AndroidMainfest.xml文件中写入
     <meta-data
            android:name="com.dongqiu.dqk.glide.SimpleGlideModule"
            android:value="GlideModule"/>
* */
public class SimpleGlideModule implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // 更改Bitmap图片压缩质量为8888,默认为565
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);

        // 指定位置在packageName/cache/glide_cache,大小为MAX_CACHE_DISK_SIZE的磁盘缓存
        //builder.setDiskCache(new InternalCacheDiskCacheFactory(context, "glide_cache", ConfigConstants.MAX_CACHE_DISK_SIZE));
        //指定内存缓存大小
       // builder.setMemoryCache(new LruResourceCache(ConfigConstants.MAX_CACHE_MEMORY_SIZE));
        //全部的内存缓存用来作为图片缓存
       // builder.setBitmapPool(new LruBitmapPool(ConfigConstants.MAX_CACHE_MEMORY_SIZE));
    }



    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {

    }
}
