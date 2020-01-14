package com.mifengkong.frtools.glide.transformation;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

/**
 *
 * @author WANG
 * @date 18/3/6
 * 圆角图片
 */

public class RoundsTransformation extends BitmapTransformation {

    private float radius = 0f;
    private boolean useX = true;  //只要 left  right 的圆角
    private boolean useY = true;  //只要 top

    public RoundsTransformation() {
        this(4);
    }

    public RoundsTransformation(int dp) {
        super();
        this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
    }

    public RoundsTransformation(int dp, boolean leftAndRight, boolean top) {
        super();
        this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
        this.useX = leftAndRight;
        this.useY = top;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return roundCrop(pool, toTransform);
    }

    private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;

        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
        if (useX && useY) {
            canvas.drawRoundRect(rectF, radius, radius, paint);
        } else if (useY) {
            canvas.drawRoundRect(rectF, 0, radius, paint);
        } else if (useX) {
            canvas.drawRoundRect(rectF, radius, 0, paint);
        }
        return result;
    }


    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }
}
