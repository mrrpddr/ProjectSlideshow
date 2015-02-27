package com.example.maya.projectslideshow;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class SlideshowActivity extends Activity {

    private Button start;
    private Button exit;
    private Button prev;
    private ImageView img;
    private static final String TAG = "Chachaslide";
    int moveOver= 3;
    private Button next;
    private final Handler mHandler = new Handler();
    private Timer timer;
    private static final String KEY_INDEX = "index";
    private  int currentIndex=0;

    private int[] myImages= new int[] {R.drawable.runimg1,R.drawable.runimg2,R.drawable.runimg3,
            R.drawable.runimg4,R.drawable.runimg5};

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState() called");
        outState.putInt(KEY_INDEX, currentIndex);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);

        start = (Button)findViewById(R.id.start_btn);


        next= (Button)findViewById(R.id.next_bt);
        exit= (Button)findViewById(R.id.exit_btn);
        prev= (Button)findViewById(R.id.previous_btn);
        img= (ImageView)findViewById(R.id.img_view);
        img.setVisibility(View.INVISIBLE);
        next.setVisibility(View.INVISIBLE);
        exit.setVisibility(View.INVISIBLE);
        prev.setVisibility(View.INVISIBLE);


        Log.d(KEY_INDEX,"onCreate() called");

        if (savedInstanceState != null){
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);

        }

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                start.setVisibility(View.INVISIBLE);
                img.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                prev.setVisibility(View.VISIBLE);
                exit.setVisibility(View.VISIBLE);


                img.setImageResource(myImages[0]);
                mHandler.postDelayed(slideOver, 5000);

            }
        });



    }

    private final Runnable slideOver = new Runnable()
    {
        public void run()
        {
            i= (i+1) % myImages.length;
            changeImage();

         }

    };

    private int i=0;

    public void onClickNext(View v){
      i= (i+1) % myImages.length;
      changeImage();

    }

    public void onExitClicked(View v){
        finish();
    }

    public void onPrevClicked(View v){
        i= (i-1) % myImages.length;
        i= (i<0)? i+myImages.length: i;

        changeImage();
    }

    public void changeImage(){

        img.setImageResource(myImages[i]);
        currentIndex=i;
        mHandler.postDelayed(slideOver, 5000);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_slideshow, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

