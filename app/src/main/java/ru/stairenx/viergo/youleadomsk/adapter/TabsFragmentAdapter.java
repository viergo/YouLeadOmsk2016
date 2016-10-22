package ru.stairenx.viergo.youleadomsk.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.HashMap;
import java.util.Map;

import ru.stairenx.viergo.youleadomsk.fragment.AbstractTabFragment;
import ru.stairenx.viergo.youleadomsk.fragment.FirstDayFragment;
import ru.stairenx.viergo.youleadomsk.fragment.SecondDayCareerFragment;
import ru.stairenx.viergo.youleadomsk.fragment.SecondDayYouthSpeakFragment;

/**
 * Created by viergo on 24.09.16.
 */
public class TabsFragmentAdapter extends FragmentPagerAdapter {

    private Map<Integer, AbstractTabFragment> tabs;
    private Context context;

    public TabsFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        initTabsMap(context);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getTitle();
    }


    @Override
    public int getCount() {
        return tabs.size();
    }

    private void initTabsMap(Context context) {
        tabs = new HashMap<>();
        tabs.put(0, FirstDayFragment.getInstance(context));
        tabs.put(1, SecondDayCareerFragment.getInstance(context));
        tabs.put(2, SecondDayYouthSpeakFragment.getInstance(context));

    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position);
    }
}
