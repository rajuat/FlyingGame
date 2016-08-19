package com.andriod.myapplication.alphabet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.andriod.myapplication.R;

public class AlphabetActivity extends AppCompatActivity {

    private AlphabetView alphabetView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet);

        RelativeLayout mtile = (RelativeLayout) findViewById(R.id.mtile);
        mtile.setBackgroundResource(R.drawable.kok);
        alphabetView = new AlphabetView(this);
        mtile.addView(alphabetView);

        RelativeLayout rvtrans = new RelativeLayout(this);
        rvtrans.setBackgroundResource(R.drawable.kok_t);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        mtile.addView(rvtrans, params);
    }
}

