package com.mygdx.game;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.badlogic.gdx.Gdx;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;


/**
 * Created by Kevin on 9/25/16.
 * Edited by Eric 9/27/16 (add temporary play button)
 */

public class PlayGameFragment extends Fragment {
    private Button mPlayButton;
    private ShareButton mFacebookShareButton;
    private Button mBackLoginButton;
    private Button mDatabaseManagerButton;
    private Button mAccelerometerButton;
    private TextView mNameField;
    private String mFirstName = "";

    private static final String NAME = "username";

    @Override
    public void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());           // initialize Facebook SDK; do before view inflater
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // retrieve username from shared preferences to get database record
        View view = inflater.inflate(R.layout.fragment_play_game, container, false);
        UserData data = UserData.get();                   // get user record from database
        mFirstName = data.getFirstName();

        // display player welcome message
        mNameField = (TextView) view.findViewById(R.id.name_text_view);
        mNameField.setText("Welcome " + mFirstName);

        mPlayButton = (Button) view.findViewById(R.id.play_button);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LevelDialogActivity.class));
            }
        });

        // display custom Facebook Share button
        mFacebookShareButton = (ShareButton) view.findViewById(R.id.fb_share_button);
        mFacebookShareButton.setShareContent(getPhoto());

        // takes logged in user from main menu back to login menu
        mBackLoginButton = (Button) view.findViewById(R.id.back_login_button);
        mBackLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginPage = new Intent(getActivity(), LoginActivity.class);
                loginPage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(loginPage);
            }
        });

        // access Android Database Manager tool to view contents of database
        mDatabaseManagerButton = (Button) view.findViewById(R.id.database_manager_button);
        mDatabaseManagerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dbmanager = new Intent(getActivity(), AndroidDatabaseManager.class);
                startActivity(dbmanager);
            }
        });

        // accelerometer testing
        mAccelerometerButton = (Button) view.findViewById(R.id.accelerometer_button);
        mAccelerometerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AccelerometerActivity.class));
            }
        });

        return view;
    }

    // Facebook share
    private SharePhotoContent getPhoto() {
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_viking);
        SharePhoto photo = new SharePhoto.Builder().setBitmap(image).build();
        SharePhotoContent content = new SharePhotoContent.Builder().addPhoto(photo).build();

        return content;
    }

    @Override
    public void onResume() {
        super.onResume();
        UserData data = UserData.get();
        mFirstName = data.getFirstName();
        if (!mNameField.getText().toString().contains(mFirstName)) {
            mNameField.setText("Welcome " + mFirstName);
        }
    }

}
