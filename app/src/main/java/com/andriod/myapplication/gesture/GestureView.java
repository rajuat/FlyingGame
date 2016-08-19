package com.andriod.myapplication.gesture;

import android.content.Context;
import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import com.andriod.myapplication.DisplayMessageActivity;
import com.andriod.myapplication.R;

/**
 * Created by raju.athokpam on 10-08-2016.
 */
public class GestureView extends GestureOverlayView {
    private final Bitmap kokBitmap;

    public GestureView(Context context) {
        super(context);
        kokBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.kok);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        this.setBackground(new BitmapDrawable(getResources(), kokBitmap));
    }

}
