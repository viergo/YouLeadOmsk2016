package ru.stairenx.viergo.youleadomsk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import ru.stairenx.viergo.youleadomsk.CreateWidget;
import ru.stairenx.viergo.youleadomsk.R;
import ru.stairenx.viergo.youleadomsk.adapter.AllSpeakerAdapter;
import ru.stairenx.viergo.youleadomsk.database.DataBaseAction;
import ru.stairenx.viergo.youleadomsk.server.ServerAction;

/**
 * Created by viergo on 22.09.16.
 */
public class SpeakersActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speakers);
        initToolbar();
        initDrawerLayout();
        initAllSpeakers();
        DataBaseAction.initContext(getApplicationContext());
        ServerAction.getNews();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.speakers);
    }

    private void initDrawerLayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.view_navigation_open, R.string.view_navigation_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        try {
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {
                    drawerLayout.closeDrawers();
                    if (item.getItemId() != R.id.speakers) {
                        startActivity(new Intent(SpeakersActivity.this, CreateWidget.includeNavAction(item)));
                        SpeakersActivity.this.finish();
                    }
                    return true;
                }
            });
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void initAllSpeakers() {
        listView = (ListView) findViewById(R.id.listViewAllSpeaker);
        DataBaseAction.initContext(getApplicationContext());
            DataBaseAction.addAllSpeakers();
        AllSpeakerAdapter adp = new AllSpeakerAdapter(this,DataBaseAction.getSpeaker());
        listView.setAdapter(adp);
    }
}