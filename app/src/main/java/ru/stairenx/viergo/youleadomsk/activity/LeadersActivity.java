package ru.stairenx.viergo.youleadomsk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import ru.stairenx.viergo.youleadomsk.CreateWidget;
import ru.stairenx.viergo.youleadomsk.R;
import ru.stairenx.viergo.youleadomsk.UsersItem;
import ru.stairenx.viergo.youleadomsk.adapter.LeadersAdapter;
import ru.stairenx.viergo.youleadomsk.database.DataBaseAction;
import ru.stairenx.viergo.youleadomsk.server.ServerAction;

/**
 * Created by viergo on 22.09.16.
 */
public class LeadersActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaders);
        initToolbar();
        initDrawerLayout();
        DataBaseAction.initContext(getApplicationContext());
        ServerAction.getProgram();
        initLeaders();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.leaders);
    }

    private void initDrawerLayout(){
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
                    if(item.getItemId()!=R.id.leaders) {
                        startActivity(new Intent(LeadersActivity.this, CreateWidget.includeNavAction(item)));
                        LeadersActivity.this.finish();
                    }
                    return true;
                }
            });
        }catch (Exception e){
            e.getMessage();
        }
    }

    private void initLeaders(){
        listView = (ListView) findViewById(R.id.listViewLeaders);
        List<UsersItem> users;
        DataBaseAction.initContext(getApplicationContext());
        ServerAction.getUsers();
        users = DataBaseAction.getUsers();
        LeadersAdapter adapter = new LeadersAdapter(this, users);
        listView.setAdapter(adapter);
        final TextView v = (TextView) findViewById(R.id.cv_nameLeader);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //сделать то же как делали в слушатель при датчеке освещения и воспроизведении музыки, когда был список датчиков
				Log.d("LOG_TAG", "itemSelect: position = " + position + ", id = "+ id);
            }
        });
    }
}
