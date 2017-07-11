package com.infinitesense.bobill.infinitesense.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by bobill on 2017/6/14.
 */

public class ViewpagerAdapter extends PagerAdapter {
    private List<View> views;
    private Context context;

    public ViewpagerAdapter(List<View> views,Context context){
        this.context = context;
        this.views = views;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }
}
