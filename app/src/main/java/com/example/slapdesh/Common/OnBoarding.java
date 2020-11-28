package com.example.slapdesh.Common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slapdesh.HelperClasses.SlideAdapter;
import com.example.slapdesh.R;
import com.example.slapdesh.User.UserDashBoard;

public class OnBoarding extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dotslayout;
    TextView[] dots;
    Button letsgetstatedbutton;

    SlideAdapter slideAdapter;
    Animation animation;
    int currentposition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        viewPager=findViewById(R.id.slider);
        dotslayout=findViewById(R.id.linearlayout);
        letsgetstatedbutton=findViewById(R.id.get_start_button);

        slideAdapter=new SlideAdapter(this);
        try {
            viewPager.setAdapter(slideAdapter);
        }catch (Exception e)
        {
            Toast.makeText(OnBoarding.this," "+e,Toast.LENGTH_LONG).show();
        }
        adddoits(0);
        viewPager.addOnPageChangeListener(changeListener);
    }

    public void next(View view)
    {
        viewPager.setCurrentItem(currentposition+1);
    }

    public void skip(View view)
    {
        startActivity(new Intent(getApplicationContext(), UserDashBoard.class));
        finish();
    }

    private void adddoits(int position)
    {
        dots = new TextView[4];
        dotslayout.removeAllViews();

        for (int i=0;i<dots.length;i++)
        {
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);

            dotslayout.addView(dots[i]);
        }
        if(dots.length>0)
        {
            dots[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }


    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            adddoits(position);

            currentposition=position;

            if(position==0)
            {
                letsgetstatedbutton.setVisibility(View.INVISIBLE);
            }
            else if(position==1)
            {
                letsgetstatedbutton.setVisibility(View.INVISIBLE);
            }
            else if(position==2)
            {
                letsgetstatedbutton.setVisibility(View.INVISIBLE);
            }
            else {
                animation= AnimationUtils.loadAnimation(OnBoarding.this,R.anim.side_animation);
                letsgetstatedbutton.setAnimation(animation);
                letsgetstatedbutton.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
