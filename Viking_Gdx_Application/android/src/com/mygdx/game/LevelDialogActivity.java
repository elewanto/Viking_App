package com.mygdx.game;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LevelDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_dialog);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.level_dialog_fragment_container);
        if (fragment == null) {
            fragment = new StartGameDialogFragment();
            fragmentManager.beginTransaction().add(R.id.level_dialog_fragment_container, fragment).commit();
        }
    }
}
