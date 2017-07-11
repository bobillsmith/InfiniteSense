package com.infinitesense.bobill.infinitesense;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.infinitesense.bobill.infinitesense.Adapters.ViewpagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class VPagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    private List<View> views;
    private ViewpagerAdapter vpAdapter;
    private ViewPager vp;
    private ImageView[] ivs;
    private int[] dots = {R.id.iv1,R.id.iv2,R.id.iv3};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vpager);
        initViews();
        initNavigateDots();
        initStartBtn();
    }

    private void initViews(){
        LayoutInflater inflater = LayoutInflater.from(this);

        views = new ArrayList<>();
        views.add(inflater.inflate(R.layout.one,null));
        views.add(inflater.inflate(R.layout.two,null));
        views.add(inflater.inflate(R.layout.three,null));

        vpAdapter = new ViewpagerAdapter(views,this);

        vp = (ViewPager)findViewById(R.id.vp_guide);
        vp.setAdapter(vpAdapter);
        vp.setOnPageChangeListener(this);
    }

    private void initNavigateDots(){
        ivs = new ImageView[views.size()];
        for(int i=0; i<views.size();i++){
            ivs[i] = (ImageView)findViewById(dots[i]);
        }

    }

    private void initStartBtn(){
        views.get(views.size()-1).findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VPagerActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for(int i=0; i<views.size();i++){
            if(position==i){
                ivs[i].setImageResource(R.drawable.login_point_selected);
            }else {
                ivs[i].setImageResource(R.drawable.login_point);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
