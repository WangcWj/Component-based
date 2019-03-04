package com.chanven.lib.cptr.header.smileheader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 *
 */

public class SmlieAnimView extends View {

    private RectF mOval;
    private Paint mPointPaint;


    private float defaultWidth = 0;
    private float defaultHeight = 0;

    private float lineWidth = 0;//画笑脸的线的宽度
    private float slimeistanceBorder = 0;//笑脸距离边框的距离
    private Paint mPaint;
    //默认最小的宽度

    private int startAngle = 90;//笑脸的初始位置
    private float sweepAngle;
    private int angleForUpdate = 8;//每次转的角度

    private boolean animRunning = false;


    public SmlieAnimView(Context context) {
        super(context);
        init();
    }

    public SmlieAnimView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SmlieAnimView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        defaultWidth = dp2px(60);
        defaultHeight = dp2px(60);

        lineWidth = dp2px(3);

        slimeistanceBorder = dp2px(18);

        mPaint = new Paint();
        mPaint.setStrokeWidth(lineWidth);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#1786fa"));

        mPointPaint = new Paint();
        mPointPaint.setStrokeWidth(lineWidth + dp2px(1));
        mPointPaint.setStrokeCap(Paint.Cap.ROUND);
        mPointPaint.setStyle(Paint.Style.STROKE);
        mPointPaint.setColor(Color.parseColor("#1786fa"));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width;
        int height;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            width = size;
        } else {
            width = (int) defaultWidth;
        }
        defaultWidth = width;
        mode = MeasureSpec.getMode(heightMeasureSpec);
        size = MeasureSpec.getSize(heightMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            height = size;
        } else {
            height = (int) defaultHeight;
        }
        defaultHeight = height;
        setMeasuredDimension(width, height);



        mOval = new RectF(0 + lineWidth / 2 + slimeistanceBorder, 0 + lineWidth / 2 + slimeistanceBorder, defaultWidth - lineWidth / 2 - slimeistanceBorder
                , defaultHeight - lineWidth / 2 - slimeistanceBorder);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (startAngle == 90){
            sweepAngle = (lineWidth / (getWidth() / 2));
        }
        if (startAngle > 225 && startAngle + sweepAngle < 360 + 225) {
            canvas.drawArc(mOval, 225, (lineWidth / (getWidth() / 2)), false, mPointPaint);
        }
        if (startAngle > 315 && startAngle + sweepAngle < 360 + 315) {
            canvas.drawArc(mOval, 315, (lineWidth / (getWidth() / 2)), false, mPointPaint);
        }

        if (startAngle > 360 + 45 && startAngle < 90 + 360 + 270) {
            if (sweepAngle < 60) {
                startAngle = 360 + 45;
                sweepAngle += angleForUpdate;
            }
        }

        if (startAngle + sweepAngle > 90 + 360 + 360) {
            sweepAngle -= angleForUpdate;
            if (sweepAngle < lineWidth / (getWidth() / 2)) {
                startAngle = 90;
                sweepAngle = lineWidth / (getWidth() / 2);
            }
        }

        canvas.drawArc(mOval, startAngle, sweepAngle, false, mPaint);

        if (animRunning) {
            invalidate();
            startAngle += angleForUpdate;
        }

    }

    public int dp2px(float dp) {
        return (int) (dp * getContext().getResources().getDisplayMetrics().density + 0.5f);
    }

    public void startAnim() {
        animRunning = true;
        invalidate();
    }

    public void stopAnim() {
        animRunning = false;
    }

    public boolean getAnimStatus() {
        return animRunning;
    }

    public void setSlimeColor(int colorInt){
        mPaint.setColor(getResources().getColor(colorInt));
        mPointPaint.setColor(getResources().getColor(colorInt));
    }
}
