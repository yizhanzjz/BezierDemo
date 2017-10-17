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
 * Created by yizhan on 2017/10/17.
 */

public class Bezier3View extends View {

    private Paint mPaint;
    private PointF startPoint;
    private PointF endPoint;
    private PointF controllerOnePoint;
    private PointF controllerTwoPoint;
    private int mMode = 0;

    public Bezier3View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);

        startPoint = new PointF(0, 0);
        endPoint = new PointF(0, 0);
        controllerOnePoint = new PointF(0, 0);
        controllerTwoPoint = new PointF(0, 0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int centerX = w / 2;
        int centerY = h / 2;

        startPoint.set(centerX - 200, centerY);
        endPoint.set(centerX + 200, centerY);
        controllerOnePoint.set(centerX - 150, centerY - 100);
        controllerTwoPoint.set(centerX + 150, centerY - 100);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画数据点和控制点
        mPaint.setStrokeWidth(20);
        mPaint.setColor(Color.GRAY);
        canvas.drawPoint(startPoint.x, startPoint.y, mPaint);
        canvas.drawPoint(endPoint.x, endPoint.y, mPaint);
        canvas.drawPoint(controllerOnePoint.x, controllerOnePoint.y, mPaint);
        canvas.drawPoint(controllerTwoPoint.x, controllerTwoPoint.y, mPaint);

        //画数据点和控制点之间的线
        mPaint.setStrokeWidth(4);
        canvas.drawLine(startPoint.x, startPoint.y, controllerOnePoint.x, controllerOnePoint.y, mPaint);
        canvas.drawLine(endPoint.x, endPoint.y, controllerTwoPoint.x, controllerTwoPoint.y, mPaint);
        canvas.drawLine(controllerOnePoint.x, controllerOnePoint.y, controllerTwoPoint.x, controllerTwoPoint.y, mPaint);

        //画贝塞尔曲线
        mPaint.setStrokeWidth(8);
        mPaint.setColor(Color.RED);
        Path path = new Path();
        path.moveTo(startPoint.x, startPoint.y);
        path.cubicTo(controllerOnePoint.x, controllerOnePoint.y, controllerTwoPoint.x, controllerTwoPoint.y, endPoint.x, endPoint.y);
        canvas.drawPath(path, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        if (mMode == 0) {
            controllerOnePoint.x = x;
            controllerOnePoint.y = y;
        } else {
            controllerTwoPoint.x = x;
            controllerTwoPoint.y = y;
        }

        invalidate();

        return true;
    }

    public void setMode(int mode) {
        mMode = mode;
    }

}
