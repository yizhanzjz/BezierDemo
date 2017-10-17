package com.example.yizhan.bezierdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 二阶贝塞尔曲线的演示示例
 * Created by yizhan on 2017/10/17.
 */

public class Bezier2View extends View {


    private Paint mPaint;
    private PointF startPoint;
    private PointF endPoint;
    private PointF controlPoint;

    public Bezier2View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {

        //画笔
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);


        startPoint = new PointF(0, 0);
        endPoint = new PointF(0, 0);
        controlPoint = new PointF(0, 0);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int centerX = w / 2;
        int centerY = h / 2;

        startPoint.set(centerX - 200, centerY);
        endPoint.set(centerX + 200, centerY);
        controlPoint.set(centerX, centerY - 100);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画出3个点
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(startPoint.x, startPoint.y, mPaint);
        canvas.drawPoint(endPoint.x, endPoint.y, mPaint);
        canvas.drawPoint(controlPoint.x, controlPoint.y, mPaint);

        //画出数据点与控制点的连线
        mPaint.setStrokeWidth(4);
        canvas.drawLine(startPoint.x, startPoint.y, controlPoint.x, controlPoint.y, mPaint);
        canvas.drawLine(endPoint.x, endPoint.y, controlPoint.x, controlPoint.y, mPaint);

        //画二阶贝塞尔曲线
        mPaint.setStrokeWidth(8);
        mPaint.setColor(Color.RED);
        Path path = new Path();
        path.moveTo(startPoint.x, startPoint.y);
        path.quadTo(controlPoint.x, controlPoint.y, endPoint.x, endPoint.y);
        canvas.drawPath(path, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //将接触点控制点
        float x = event.getX();
        float y = event.getY();
        controlPoint.set(x, y);

        //刷新
        invalidate();

        return true;
    }
}
