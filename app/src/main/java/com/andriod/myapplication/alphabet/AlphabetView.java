package com.andriod.myapplication.alphabet;

import android.content.Context;
import android.content.res.Resources;
import android.gesture.GestureLibraries;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.andriod.myapplication.R;

/**
 * Created by raju.athokpam on 01-08-2016.
 */
public class AlphabetView extends View {

    public int width;
    public  int height;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mBitmapPaint;
    Context context;
    private Paint circlePaint;
    private Path circlePath;
    private Paint mPaint;

    public AlphabetView(Context c) {
        super(c);
        context=c;
        mPath = new Path();

        mBitmapPaint = new Paint(Paint.DITHER_FLAG);

        circlePaint = new Paint();
        circlePath = new Path();
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.BLUE);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeJoin(Paint.Join.MITER);
        circlePaint.setStrokeWidth(100f);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(100);
        //http://stackoverflow.com/questions/35425404/alphabet-drawing-app
        mPaint.setARGB(255, 0, 0,0);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setPathEffect(new DashPathEffect(new float[] {10,20}, 0));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        Log.d("Size change", "size change");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap( mBitmap, 0, 0, mBitmapPaint);
        canvas.drawPath( mPath,  mPaint);
        canvas.drawPath( circlePath,  circlePaint);
    }

    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 50;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up(x, y);
                invalidate();
                break;
        }

        return true;
    }
    private void touch_start(float x, float y) {
        //adjacent(x, y);
        if(adjacent){
            mPath.reset();
            mPath.moveTo(x, y);
            mX = x;
            mY = y;
            Log.d("start xy==>", x + "," + y);
        }
    }
    private void touch_move(float x, float y) {
        if ( adjacent && tolerance(x, y)) {
            Log.d("move xy==>", x+","+y);
            mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            mX = x;
            mY = y;
            /*circlePath.reset();
            circlePath.addCircle(mX, mY, 30, Path.Direction.CW);*/
        }
    }
    private void touch_up(float x, float y) {
        if(adjacent) {
            mPath.lineTo(mX, mY);
            Log.d("end xy", mX + "," + mY);
            circlePath.reset();
            // commit the path to our offscreen
            mCanvas.drawPath(mPath, mPaint);
            // kill this so we don't double draw
            mPath.reset();
        }
    }

    private boolean adjacent =  true;
    //code for continous drawing
    /*private void adjacent(float x, float y){
        if(Math.signum(mX) == 0 && Math.signum(mY) == 0){
            Log.d("00 xy==>", mX + "," + mY);
            adjacent = true;
            return;
        }
        int dx = (int)Math.abs(x - mX);
        int dy = (int)Math.abs(y - mY);
        Log.d("dd xy==>", dx + "," + dy);
        adjacent = (dx <= 100 && dy <= 100);
    }*/

    private boolean tolerance(float x, float y){
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        return (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE);
    }


    public Bitmap drawTextToBitmap(Context gContext,
                                   int gResId,
                                   String gText) {
        Resources resources = gContext.getResources();
        float scale = resources.getDisplayMetrics().density;
        Bitmap bitmap =
                BitmapFactory.decodeResource(resources, gResId);

        android.graphics.Bitmap.Config bitmapConfig =
                bitmap.getConfig();
        // set default bitmap config if none
        if(bitmapConfig == null) {
            bitmapConfig = android.graphics.Bitmap.Config.ARGB_8888;
        }
        // resource bitmaps are imutable,
        // so we need to convert it to mutable one
        bitmap = bitmap.copy(bitmapConfig, true);

        Canvas canvas = new Canvas(bitmap);
        // new antialised Paint
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // text color - #3D3D3D
        paint.setColor(Color.rgb(61, 61, 61));
        // text size in pixels
        paint.setTextSize((int) (14 * scale));
        // text shadow
        paint.setShadowLayer(1f, 0f, 1f, Color.WHITE);

        // draw text to the Canvas center
        Rect bounds = new Rect();
        paint.getTextBounds(gText, 0, gText.length(), bounds);
        int x = (bitmap.getWidth() - bounds.width())/2;
        int y = (bitmap.getHeight() + bounds.height())/2;

        canvas.drawText(gText, x, y, paint);

        return bitmap;

        //GestureLibraries.
    }

}
