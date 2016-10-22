package ru.stairenx.viergo.youleadomsk.fragment;

import android.content.Context;
import android.view.View;

/**
 * Created by viergo on 24.09.16.
 */
public class AbstractTabFragment extends android.support.v4.app.Fragment {
    private String title;
    protected Context context;
    protected View view;

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }
}
