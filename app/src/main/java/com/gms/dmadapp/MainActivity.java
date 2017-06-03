package com.gms.dmadapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by gms on 2017/6/3.
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    static final String TAG = "MainActivity" ;

    static final String BUNDLE_KEY_NAV_ITEM_ID = "b.key.nav.item.id" ;

    DrawerLayout drawerLayout ;

    Toolbar toolbar;

    TextView txtTitle ;

    ActionBarDrawerToggle actionBarDrawerToggle  ;

    int navItemID ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if( savedInstanceState != null ) {
            navItemID = savedInstanceState.getInt(BUNDLE_KEY_NAV_ITEM_ID) ;
        }else {
            navItemID = R.id.nav_item_1 ;
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar) ;
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer) ;

        txtTitle = (TextView) findViewById(R.id.txt_title) ;

        setSupportActionBar(toolbar);

//        getSupportActionBar().hide();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this , drawerLayout , toolbar , R.string.drawer_open , R.string.drawer_close  );
        actionBarDrawerToggle.syncState();
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        NavigationView navView = (NavigationView)findViewById(R.id.nav_view) ;
        navView.setNavigationItemSelectedListener(this);


        navMenuItem(navView.getMenu().findItem(navItemID));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDLE_KEY_NAV_ITEM_ID , navItemID);
    }

    private void navMenuItem(MenuItem menuItem) {
        txtTitle.setText(menuItem.getTitle());
        navItemID = menuItem.getItemId() ;
        menuItem.setChecked(true);
    }


    // ==================================================
    //                  Interface
    // ==================================================

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        navMenuItem(item) ;
        drawerLayout.closeDrawers();
        return false;
    }
} // class close
