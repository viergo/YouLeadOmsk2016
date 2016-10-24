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

import com.squareup.picasso.Picasso;

import ru.stairenx.viergo.youleadomsk.Constants;
import ru.stairenx.viergo.youleadomsk.CreateWidget;
import ru.stairenx.viergo.youleadomsk.R;

/**
 * Created by viergo on 22.09.16.
 */
public class PartnersActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partners);
        initToolbar();
        initDrawerLayout();
        initImagePartners();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.partners);
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
                    if(item.getItemId()!=R.id.partners) {
                        startActivity(new Intent(PartnersActivity.this, CreateWidget.includeNavAction(item)));
                        PartnersActivity.this.finish();
                    }
                    return true;
                }
            });
        }catch (Exception e){
            e.getMessage();
        }
    }

    private void initImagePartners(){
        ImageView metro,lerua,univer,skuratov,stairenx,rahat,fotoshkola,eva,dacha,apriori,om1,mayak,vesomsk,molodoy;
        metro = (ImageView) findViewById(R.id.metro);
        setImageServer(metro,"metro");
        lerua = (ImageView) findViewById(R.id.lerua);
        setImageServer(lerua,"lerua");
        univer = (ImageView) findViewById(R.id.univer);
        setImageServer(univer,"univer");
        skuratov = (ImageView) findViewById(R.id.skuratov);
        setImageServer(skuratov,"skuratov");
        rahat = (ImageView) findViewById(R.id.rahat);
        setImageServer(rahat,"rahat");
        fotoshkola = (ImageView) findViewById(R.id.fotoshkola);
        setImageServer(fotoshkola,"fotoshkola");
        eva = (ImageView) findViewById(R.id.eva);
        setImageServer(eva, "eva");
        stairenx = (ImageView) findViewById(R.id.stairenx);
        setImageServer(stairenx,"stairenx");
        dacha = (ImageView) findViewById(R.id.dacha);
        setImageServer(dacha, "dacha");
        apriori = (ImageView) findViewById(R.id.apriori);
        setImageServer(apriori, "apriori");
        mayak = (ImageView) findViewById(R.id.mayak);
        setImageServer(mayak, "mayak");
        molodoy = (ImageView) findViewById(R.id.molodoy);
        setImageServer(molodoy, "molodoy");
        vesomsk = (ImageView) findViewById(R.id.vesomsk);
        setImageServer(vesomsk, "vesomsk");
        om1 = (ImageView) findViewById(R.id.om1);
        setImageServer(om1, "om1");
    }

    private void setImageServer(ImageView img, String partner){
        Picasso.with(getApplicationContext())
                .load(Constants.LINK_IMG+partner+Constants.PNG)
                .error(R.drawable.error)
                .placeholder(R.drawable.download)
                .into(img);
    }
}
