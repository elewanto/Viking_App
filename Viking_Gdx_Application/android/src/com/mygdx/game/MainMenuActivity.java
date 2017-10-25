package com.mygdx.game;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private TextView mFirstNameDrawerText;
    private TextView mCharacterNameDrawerText;
    public static final String TAG = "MainMenuActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.activity_main_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.main_menu_fragment_container);
        if (fragment == null) {
            fragment = new PlayGameFragment();
            fragmentManager.beginTransaction().add(R.id.main_menu_fragment_container, fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) drawer.findViewById(R.id.nav_view);
        View navHeader = navView.getHeaderView(0);

        mFirstNameDrawerText = (TextView) navHeader.findViewById(R.id.firstname_drawer);
        mCharacterNameDrawerText = (TextView) navHeader.findViewById(R.id.character_name_drawer);

        UserData data = UserData.get();
        mFirstNameDrawerText.setText(data.getFirstName());
        mCharacterNameDrawerText.setText(data.getCharacterName());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView){
                super.onDrawerOpened(drawerView);
                UserData data = UserData.get();
                mFirstNameDrawerText.setText(data.getFirstName());
                mCharacterNameDrawerText.setText(data.getCharacterName());

            }
        };

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_play_game) {
            switchFragment(new PlayGameFragment());
        } else if (id == R.id.nav_account) {
            switchFragment(new AccountManageFragment());
        } else if (id == R.id.nav_settings) {
            switchFragment(new SettingsFragment());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        UserData data = UserData.get();
        String firstName = data.getFirstName();
        String characterName = data.getCharacterName();
        if (!mFirstNameDrawerText.getText().toString().equals(firstName)) {
            mFirstNameDrawerText.setText(firstName);
        }

        if (!mCharacterNameDrawerText.getText().toString().equals(characterName)) {
            mCharacterNameDrawerText.setText(characterName);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    private void switchFragment(Fragment newFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_menu_fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }


}
