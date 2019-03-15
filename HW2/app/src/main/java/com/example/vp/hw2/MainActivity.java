package com.example.vp.hw2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {

    Counter counter = new Counter();
    ImageButton b_next, b_prev;
    TextView text_right, text_left;
    ImageSwitcher imageSwitcher;

    Integer[] images = {R.drawable.art_1, R.drawable.art_2, R.drawable.art_3, R.drawable.art_4, R.drawable.art_5};
    int i = 0;

    Animation in, out, in2, out2;


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Save Count Left", counter.getLeftCounter());
        outState.putInt("Save Count Right", counter.getRightCounter());
        outState.putString("Left String" ,String.valueOf(counter.getLeftCounter()));
        outState.putString("Right String" ,String.valueOf(counter.getRightCounter()));
        outState.putInt("SaveImage",images[i] );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageSwitcher = (ImageSwitcher) findViewById(R.id.switcher);

        text_right = (TextView) findViewById(R.id.text_right);
        text_left = (TextView) findViewById(R.id.text_left);


        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

                return imageView;
            }
        });

        in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.in);
        out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.out);

        in2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.in2);
        out2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.out2);

        b_next = (ImageButton) findViewById(R.id.next);
        b_prev = (ImageButton) findViewById(R.id.prev);


        imageSwitcher.setImageResource(R.drawable.art_1);


        b_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageSwitcher.setInAnimation(in2);
                imageSwitcher.setOutAnimation(out2);
                counter.CountLeft();
                text_right.setText(String.valueOf(counter.getLeftCounter()));
                if (i < images.length - 1) {
                    i++;
                    imageSwitcher.setImageResource(images[i]);
                } else if (i >= 4) {
                    i = 0;
                    imageSwitcher.setImageResource(images[i]);
                }


            }
        });


        b_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageSwitcher.setInAnimation(in);
                imageSwitcher.setOutAnimation(out);
                counter.CountRight();
                text_left.setText(String.valueOf(counter.getRightCounter()));

                if (i > 0) {
                    i--;

                    imageSwitcher.setImageResource(images[i]);
                } else if (i == 0) {
                    i = 4;
                    imageSwitcher.setImageResource(images[i]);
                }


            }
        });

        if (savedInstanceState != null) {
            counter.setRightCounter(savedInstanceState.getInt("Save Count Right"));
            counter.setLeftCounter(savedInstanceState.getInt("Save Count Left"));
            text_left.setText(savedInstanceState.getString("Right String"));
            text_right.setText(savedInstanceState.getString("Left String"));
            imageSwitcher.setImageResource(savedInstanceState.getInt("SaveImage"));

        }
    }
}
