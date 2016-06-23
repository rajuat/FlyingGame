package com.andriod.myapplication;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andriod.myapplication.dummy.DummyContent;

public class FragmentTestActivity extends FragmentActivity implements ItemFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test);

        // Create a new Fragment to be placed in the activity layout
        ItemFragment firstFragment = new ItemFragment();

        // In case this activity was started with special instructions from an
        // Intent, pass the Intent's extras to the fragment as arguments
        firstFragment.setArguments(getIntent().getExtras());

        // Add the fragment to the 'fragment_container' FrameLayout
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_test, firstFragment).commit();

        Intent intent = getIntent();
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.fragment_test);

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        System.out.println("Listening to clicks on fragments.");
    }
}
