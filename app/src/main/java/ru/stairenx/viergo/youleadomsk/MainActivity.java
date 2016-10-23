package ru.stairenx.viergo.youleadomsk;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import ru.stairenx.viergo.youleadomsk.ItemPack.NewsItem;
import ru.stairenx.viergo.youleadomsk.activity.CreateNewsActivity;
import ru.stairenx.viergo.youleadomsk.adapter.NewsAdapter;
import ru.stairenx.viergo.youleadomsk.database.DataBaseAction;
import ru.stairenx.viergo.youleadomsk.server.ServerAction;

public class MainActivity extends AppCompatActivity {


    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initDrawerLayout();
        initFab();
        DataBaseAction.initContext(getApplicationContext());
        ServerAction.getProgram();
        initNewsFeed();
    }

    private void initFab(){
        fab = (FloatingActionButton) findViewById(R.id.add_item_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* *** Метод для перехода на активность создания новости *** */
                startActivity(new Intent(MainActivity.this, CreateNewsActivity.class));
                MainActivity.this.finish();
            }
        });
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.news);
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
                    if(item.getItemId()!=R.id.news) {
                        startActivity(new Intent(MainActivity.this, CreateWidget.includeNavAction(item)));
                        MainActivity.this.finish();
                    }
                    return true;
                }
            });
        }catch (Exception e){
            e.getMessage();
        }
    }

    private void initNewsFeed(){
        listView = (ListView) findViewById(R.id.listViewNews);
        List<NewsItem> news;
        DataBaseAction.initContext(getApplicationContext());
        Log.d("последняя запись feed",String.valueOf(DataBaseAction.getLastNewsId()));
        ServerAction.getNews();
        news = DataBaseAction.getNews();
        NewsAdapter adapter = new NewsAdapter(this,news);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initNewsFeed();
    }
}
