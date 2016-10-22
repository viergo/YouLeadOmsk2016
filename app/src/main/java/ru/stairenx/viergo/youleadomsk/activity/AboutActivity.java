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
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ru.stairenx.viergo.youleadomsk.Constants;
import ru.stairenx.viergo.youleadomsk.CreateWidget;
import ru.stairenx.viergo.youleadomsk.R;
import ru.stairenx.viergo.youleadomsk.database.DataBaseAction;
import ru.stairenx.viergo.youleadomsk.server.ServerAction;

/**
 * Created by viergo on 22.09.16.
 */
public class AboutActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initToolbar();
        initDrawerLayout();
        initAboutInfo();
        DataBaseAction.initContext(getApplicationContext());
        ServerAction.getProgram();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.about);
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
                    if(item.getItemId()!=R.id.about) {
                        startActivity(new Intent(AboutActivity.this, CreateWidget.includeNavAction(item)));
                        AboutActivity.this.finish();
                    }
                    return true;
                }
            });
        }catch (Exception e){
            e.getMessage();
        }
    }

    private void initAboutInfo(){
        ImageView img_youlead = (ImageView) findViewById(R.id.logo_youlead);
        ImageView img_aiesec = (ImageView) findViewById(R.id.logo_aiesec);
        TextView about = (TextView) findViewById(R.id.about_youlead);
        TextView mesto = (TextView) findViewById(R.id.about_mesto);
        TextView date = (TextView) findViewById(R.id.about_date);
        Picasso.with(getApplicationContext()).load(R.drawable.youlead).into(img_youlead);
        Picasso.with(getApplicationContext()).load(R.drawable.aiesec_logo_blue).into(img_aiesec);
        about.setText(Constants.ABOUT_YOULEAD);
        mesto.setText(Constants.MESTO);
        mesto.setTextIsSelectable(true);
        date.setText(Constants.DATE);
    }
}
