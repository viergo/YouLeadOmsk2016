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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ru.stairenx.viergo.youleadomsk.CreateWidget;
import ru.stairenx.viergo.youleadomsk.LoginActivity;
import ru.stairenx.viergo.youleadomsk.R;
import ru.stairenx.viergo.youleadomsk.database.DataBaseAction;

/**
 * Created by viergo on 13.10.16.
 */
public class ProfileActivity extends AppCompatActivity{

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private Button logout;
    private TextView name;
    private TextView email;
    private TextView phone;
    private TextView info;
    private ImageView img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initToolbar();
        initDrawerLayout();
        initGetInfoLogin();
        initLogoutBtn();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.profile);
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
                    if(item.getItemId()!=R.id.profile) {
                        startActivity(new Intent(ProfileActivity.this, CreateWidget.includeNavAction(item)));
                        ProfileActivity.this.finish();
                    }
                    return true;
                }
            });
        }catch (Exception e){
            e.getMessage();
        }
    }

    private void initLogoutBtn(){
        logout = (Button) findViewById(R.id.logout_button);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseAction.initContext(getApplicationContext());
                DataBaseAction.deleteLogin();
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                ProfileActivity.this.finish();
            }
        });
    }

    private void initGetInfoLogin(){
        name = (TextView) findViewById(R.id.profile_name);
        email = (TextView) findViewById(R.id.profile_email);
        phone = (TextView) findViewById(R.id.profile_phone);
        info = (TextView) findViewById(R.id.profile_info);
        phone.setText(DataBaseAction.getLoginPhone());
        name.setText(DataBaseAction.getLoginName());
        email.setText(DataBaseAction.getLoginEmail());
        info.setText(DataBaseAction.getLoginInfo());

        Picasso.with(DataBaseAction.getContext())
                .load(DataBaseAction.getLoginImg())
                .error(R.drawable.plug)
                .placeholder(R.drawable.plug)
                .into((ImageView) findViewById(R.id.profile_img));
    }
}
