// Copyright (c) 2020 Facebook, Inc. and its affiliates.
// All rights reserved.
//
// This source code is licensed under the BSD-style license found in the
// LICENSE file in the root directory of this source tree.

package org.pytorch.demo.objectdetection;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.util.Log;

import java.util.ArrayList;


public class ResultView extends View {

    private final static int TEXT_X = 40;
    private final static int TEXT_Y = 35;
    private final static int TEXT_WIDTH = 260;
    private final static int TEXT_HEIGHT = 50;

    private Paint mPaintRectangle;
    private Paint mPaintRectangle2;
    private Paint mPaintText;
    private ArrayList<Result> mResults;

    public ResultView(Context context) {
        super(context);
    }

    public ResultView(Context context, AttributeSet attrs){
        super(context, attrs);
        mPaintRectangle = new Paint();
        mPaintRectangle.setColor(Color.YELLOW);
        mPaintRectangle2 = new Paint();
        mPaintRectangle2.setColor(Color.GREEN);
        mPaintText = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mResults == null) return;
        for (Result result : mResults) {
            mPaintRectangle.setStrokeWidth(5);
            mPaintRectangle.setStyle(Paint.Style.STROKE);
            // canvas.drawRect(result.rect, mPaintRectangle);
            if (result.rect.left < result.rect.right && result.rect.top < result.rect.bottom) {
                canvas.drawCircle((result.rect.left + result.rect.right)/2, (result.rect.top + result.rect.bottom)/2,
                    Math.min((result.rect.right - result.rect.left)/2, (result.rect.bottom - result.rect.top)/2), mPaintRectangle);
            //Path mPath = new Path();
            //RectF mRectF = new RectF(result.rect.left, result.rect.top, result.rect.left + TEXT_WIDTH,  result.rect.top + TEXT_HEIGHT);
            //mPath.addRect(mRectF, Path.Direction.CW);
            //mPaintText.setColor(Color.MAGENTA);
            //canvas.drawPath(mPath, mPaintText);

                mPaintText.setColor(Color.WHITE);
                mPaintText.setStrokeWidth(0);
                mPaintText.setStyle(Paint.Style.FILL);
                mPaintText.setTextSize(20);
                //canvas.drawText(String.format("%s %.2f", PrePostProcessor.mClasses[result.classIndex], result.score), result.rect.left + TEXT_X, result.rect.top + TEXT_Y, mPaintText);
                canvas.drawText(String.format("%.2f", result.score), result.rect.left + TEXT_X, result.rect.top + TEXT_Y, mPaintText);
            } else {
                mPaintRectangle2.setStrokeWidth(5);
                mPaintRectangle2.setStyle(Paint.Style.STROKE);
                canvas.drawCircle((result.rect.left + result.rect.right)/2, (result.rect.top + result.rect.bottom)/2, 5, mPaintRectangle2);

                Log.e("wrong coordinate with confidence = ", String.valueOf(result.score));
            }

        }
    }

    public void setResults(ArrayList<Result> results) {
        mResults = results;
    }
}
