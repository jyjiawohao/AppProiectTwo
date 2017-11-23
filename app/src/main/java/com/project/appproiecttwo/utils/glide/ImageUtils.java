package com.project.appproiecttwo.utils.glide;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.project.appproiecttwo.R;

import static com.bumptech.glide.request.RequestOptions.centerCropTransform;

/**
 * Created by itheima.
 * 图片处理工具类
 */
public class ImageUtils {

    /**
     * 设置 圆形头像
     *
     * @param context
     * @param uri
     * @param imageView
     */
 public static void setHeadImage(Context context, int uri, ImageView imageView) {

     Glide.with(context)
             .load(uri)
             .apply(centerCropTransform()
                     .placeholder(R.drawable.touxiang)
                     .error(R.drawable.touxiang)
                     .dontAnimate()
                     .transform(new GlideCircleTransform(context))
                     .priority(Priority.HIGH))
             .into(imageView);
    }

    /**
     * fitCenter()会缩放图片让两边都相等或小于ImageView的所需求的边框。图片会被完整显示，可能不能完全填充整个ImageView。
     * @param context
     * @param uri
     * @param imageView
     */
    public static void setImagesFitCenter(Context context, String uri, int errorImageId, ImageView imageView) {

        Glide.with(context)
                .load(uri)
                .apply(centerCropTransform()
                        .override(600, 200) // resizes the image to these dimensions (in pixel). does not respect aspect ratio
                        .error(errorImageId)
                      )
                .into(imageView);
    }

    /**
     * CenterCrop()会缩放图片让图片充满整个ImageView的边框，然后裁掉超出的部分。
     * ImageVIew会被完全填充满，但是图片可能不能完全显示出。
     * @param context
     * @param uri
     * @param imageView
     */
    public static void setImagesCenterCrop(Context context, String uri, int errorImageId, ImageView imageView) {
        Glide.with(context)
                .load(uri)
                .apply(centerCropTransform()
                        .override(600, 200) // resizes the image to these dimensions (in pixel). does not respect aspect ratio
                        .error(errorImageId)
                        .centerCrop()
                )
                .into(imageView);
    }


    /**
     * 设置图片 包含默认
     *
     * @param fragment
     * @param uri
     * @param imageView
     * @param errorImg  包含默认
     */
    public static void setImage(Fragment fragment, String uri, ImageView imageView, int errorImg) {

        Glide.with(fragment)
                .load(uri)
                .apply(centerCropTransform()
                        .placeholder(errorImg)
                        .error(errorImg)
                        .centerCrop()
                )
                .into(imageView);

    }

    public static void setImage(Fragment fragment, Integer resourceId, ImageView imageView) {

        Glide.with(fragment)
                .load(resourceId)
                .apply(centerCropTransform()
                        .dontAnimate()
                        .centerCrop()
                )
                .into(imageView);
    }

    public static void setImage(Fragment fragment, Integer resourceId, ImageView imageView, int errorImg) {

        Glide.with(fragment)
                .load(resourceId)
                .apply(centerCropTransform()
                        .dontAnimate()
                        .error(errorImg)
                        .centerCrop()
                )
                .into(imageView);
    }


    public static void setImage(Context context, String uri, ImageView imageView) {

        Glide.with(context)
                .load(uri)
                .apply(centerCropTransform()
                        .centerCrop()
                )
                .into(imageView);
    }

    /**
     * 设置图片 包含默认
     *
     * @param context
     * @param uri
     * @param imageView
     * @param errorImg  包含默认
     */
    public static void setImage(Context context, String uri, int errorImg, ImageView imageView) {

        Glide.with(context)
                .load(uri)
                .apply(centerCropTransform()
                        .placeholder(errorImg)
                        .error(errorImg)
                        .centerCrop()
                )
                .into(imageView);
    }

    public static void setImage(Context context, Integer resourceId, ImageView imageView) {
        Glide.with(context)
                .load(resourceId)
                .apply(centerCropTransform()
                        .dontAnimate()
                        .centerCrop()
                        .centerCrop()
                )
                .into(imageView);
    }

    /**
     * 电影详情页显示高斯背景图   电影详情页 状态栏颜色就是用的模糊图片
     */
    @BindingAdapter("android:showImgBg")
    public static void showImgBg(ImageView imageView, String url) {
        displayGaussian(imageView.getContext(), url, imageView);
    }

    /**
     * 显示高斯模糊效果
     */
    private static void displayGaussian(Context context, String url, ImageView imageView) {
        // "23":模糊度；"4":图片缩放4倍后再进行模糊
       /* Glide.with(context)
                .load(url)
                .error(R.drawable.stackblur_default)
                .placeholder(R.drawable.stackblur_default)
                .crossFade(500)
                .bitmapTransform(new BlurTransformation(context, 23, 4))
                .into(imageView);*/
    }
}
