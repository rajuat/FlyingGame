package com.andriod.myapplication.gesture;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.andriod.myapplication.R;

import java.util.ArrayList;
import java.util.Collections;

public class GestureActivity extends AppCompatActivity {

    private GestureLibrary gLib;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);

        gLib = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!gLib.load()) {
            finish();
        }

        //GestureOverlayView gestures = (GestureOverlayView) findViewById(R.id.gestures);
        GestureView gestures = new GestureView(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        addContentView(gestures, params);
        gestures.addOnGesturePerformedListener(handleGestureListener);
    }

//    public void nextLetter(View view) {}

    /**
     * our gesture listener
     */
    private GestureOverlayView.OnGesturePerformedListener handleGestureListener = new GestureOverlayView.OnGesturePerformedListener() {
        @Override
        public void onGesturePerformed(GestureOverlayView gestureView,
                                       Gesture gesture) {

            ArrayList<Prediction> predictions = gLib.recognize(gesture);
            Collections.sort(predictions, new PredictionSort());
            for(String ges: gLib.getGestureEntries()){
                System.out.println("gesture " + ges);
            }
            for(Prediction pre : predictions){
                System.out.println("Prediction " + pre.name + " score " + pre.score);
            }
            // one prediction needed
            if (predictions.size() > 0) {
                Prediction prediction = predictions.get(0);
                System.out.println("prediction " + prediction.score + "name " + prediction.name );
                // checking prediction
                if (prediction.score > 5.0) {
                    // and action
                    Toast.makeText(GestureActivity.this, prediction.name,
                            Toast.LENGTH_SHORT).show();
                }
            }

        }
    };

}
