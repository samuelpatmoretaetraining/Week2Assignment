package com.muelpatmore.week2assignment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.muelpatmore.week2assignment.ui.tracklist.TrackListView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private FragmentManager fragmentManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Log.i(TAG, "Home listener clicked");
                    return true;
                case R.id.navigation_dashboard:
                    Log.i(TAG, "Nav listener clicked");
                    return true;
                case R.id.navigation_notifications:
                    Log.i(TAG, "Notification listener clicked");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add( R.id.fragmentContainer, new TrackListView())
                .commit();

    }
}
