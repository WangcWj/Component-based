package com.example.education.tree;


/**
 * Created by jack on 2017/8/22
 */

public class FRImageLoad {

    private FRImageLoad() {

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //加载图片地址
   /* public static void loadImage(String imageUrl, ImageView imageView) {
        if (Util.isOnMainThread()) {
            GlideApp.with(imageView.getContext()).load(imageUrl).into(imageView);
        }
    }

    //加载图片地址(旋转)
    public static void loadImageRotateTransForm(String imageUrl, ImageView imageView, float rotate) {
        if (Util.isOnMainThread()) {
            GlideApp.with(imageView.getContext()).load(imageUrl).transform(new RotateTransformation(rotate)).into(imageView);
        }
    }

    //加载图片地址(圆角)
    public static void loadImageRoundedTransForm(String imageUrl, ImageView imageView, int rotate) {
        if (Util.isOnMainThread()) {
            GlideApp.with(imageView.getContext()).load(imageUrl).transform(new RoundsTransformation(rotate)).into(imageView);
        }
    }

    //加载图片地址(圆角)
    public static void loadImageRoundedTransForm(int res, ImageView imageView, int rotate) {
        if (Util.isOnMainThread()) {
            GlideApp.with(imageView.getContext()).load(res).transform(new RoundsTransformation(rotate)).into(imageView);
        }
    }

    //加载图片地址(去掉动画)
    public static void loadImageDoNotAnim(String imageUrl, ImageView imageView) {
        if (Util.isOnMainThread()) {
            GlideApp.with(imageView.getContext()).load(imageUrl).dontAnimate().into(imageView);
        }
    }

    //加载图片地址(带占位图)
    public static void loadImage(String imageUrl, ImageView imageView, int placeholder) {
        if (Util.isOnMainThread()) {
            GlideApp.with(imageView.getContext()).load(imageUrl).placeholder(placeholder).error(placeholder).into(imageView);
        }
    }

    //加载本地图片
    public static void loadImage(Integer imageRes, ImageView imageView) {
        if (Util.isOnMainThread()) {
            GlideApp.with(imageView.getContext()).load(imageRes).into(imageView);
        }
    }

    //加载图片Uri
    public static void loadImage(Uri imageUri, ImageView imageView) {
        if (Util.isOnMainThread()) {
            GlideApp.with(imageView.getContext()).load(imageUri).into(imageView);
        }
    }

    //加载图片文件
    public static void loadImage(File imageFile, ImageView imageView) {
        if (Util.isOnMainThread()) {
            GlideApp.with(imageView.getContext()).load(imageFile).into(imageView);
        }
    }

    //加载图片字节
    public static void loadImage(byte[] imageByte, ImageView imageView) {
        if (Util.isOnMainThread()) {
            GlideApp.with(imageView.getContext()).load(imageByte).into(imageView);
        }
    }

    //加载Gif图片地址
    public static void loadImageGif(String imageUrl, ImageView imageView) {
        if (Util.isOnMainThread()) {
            GlideApp.with(imageView.getContext()).asGif().load(imageUrl).into(imageView);
        }
    }

    //加载本地Gif图片地址
    public static void loadImageGif(@DrawableRes int drawableRes, ImageView imageView) {
        if (Util.isOnMainThread()) {
            GlideApp.with(imageView.getContext()).asGif().load(drawableRes).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
        }
    }

    //监听图片加载
    public static void loadImage(String imageUrl, ImageView imageView, RequestListener<Drawable> requestListener) {
        if (Util.isOnMainThread()) {
            GlideApp.with(imageView.getContext()).load(imageUrl).listener(requestListener).into(imageView);
        }
    }

    *//**
     * 加载图片不带缓存
     *
     * @param imageUrl
     * @param imageView
     *//*
    public static void loadImageNoCache(String imageUrl, ImageView imageView) {
        if (Util.isOnMainThread()) {
            GlideApp.with(imageView.getContext()).load(imageUrl).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
        }
    }

    public static void loadCircle(String url, ImageView imageView, int placeholder) {
        GlideApp.with(imageView.getContext())
                .load(url)
                .placeholder(placeholder)
                .error(placeholder)
                .transform(new RoundTransformation())
                .into(imageView);
    }

    public static void loadCircle(int src, ImageView imageView, int placeholder) {
        GlideApp.with(imageView.getContext())
                .load(src)
                .placeholder(placeholder)
                .error(placeholder)
                .transform(new RoundTransformation())
                .into(imageView);
    }

    public static void loadCircle(int src, ImageView imageView) {
        GlideApp.with(imageView.getContext())
                .load(src)
                .transform(new RoundTransformation())
                .into(imageView);
    }

    public static void loadCircle(String url, ImageView imageView) {
        GlideApp.with(imageView.getContext())
                .load(url)
                .transform(new RoundTransformation())
                .into(imageView);
    }

    *//**
     * 将图片按照自定义的转换器进行转换
     *
     * @param imageUrl       图片地址
     * @param imageView      填充控件
     * @param transformation 自定义转换器
     *//*
    public static void loadImageTransForm(String imageUrl, ImageView imageView, Transformation<Bitmap> transformation) {
        if (Util.isOnMainThread()) {
            GlideApp.with(imageView.getContext()).load(imageUrl).transform(transformation).into(imageView);
        }
    }

    *//**
     * 将图片按照自定义的转换器进行转换
     *
     * @param imageSrc       图片地址
     * @param imageView      填充控件
     * @param transformation 自定义转换器
     *//*
    public static void loadImageTransForm(int imageSrc, ImageView imageView, Transformation<Bitmap> transformation) {
        if (Util.isOnMainThread()) {
            GlideApp.with(imageView.getContext()).load(imageSrc).transform(transformation).into(imageView);
        }
    }*/
}