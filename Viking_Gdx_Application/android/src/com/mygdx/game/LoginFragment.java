package com.mygdx.game;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by Kevin on 9/25/16.
 */

public class LoginFragment extends Fragment {

    private String mUsername = "";
    private String mPassword = "";
    private String mFirstName = "";
    private EditText mUsernameField;
    private EditText mPasswordField;
    private Button mLoginButton;
    private Button mNewAccountButton;
    private LoginButton mFacebookLoginButton;
    private Button mMainMenuButton;
    private VikingData vikingData;          // database
    //Facebook callback manager
    private CallbackManager mCallbackManager;
    private AccessTokenTracker mTokenTracker;
    private ProfileTracker mProfileTracker;

    private static final String NAME = "username";
    private static final String TAG = "LoginFragment";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());           // initialize Facebook SDK; do before view inflater
        mCallbackManager = CallbackManager.Factory.create();                         // create callback manager to handle login responses
        // check if Facebook access token has been updated
        mTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken newAccessToken) {
                checkMenuButton();
            }
        };
        // check if Facebook user profile has changed
        mProfileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                checkMenuButton();
            }
        };
        mTokenTracker.startTracking();
        mProfileTracker.startTracking();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mUsernameField = (EditText) view.findViewById(R.id.username_text_edit);
        // check if username passed from create account activity fragment, and automatically populate username field
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            if (extras.getString(NAME) != null) {
                mUsernameField.setText(extras.getString(NAME));
                mUsername = extras.getString(NAME);
            }
        }
        mUsernameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mUsername = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mPasswordField = (EditText) view.findViewById(R.id.password_text_edit);
        mPasswordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPassword = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Can only login user with internal application login OR Facebook login...cannot currently associate a user account with both logins
        // application login
        mLoginButton = (Button) view.findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUsername.length() == 0 || mPassword.length() == 0) {
                    Toast.makeText(getActivity(), R.string.invalid_login_toast, LENGTH_SHORT).show();
                } else {
                    // open or create database -- getActivity() correct argument?
                    vikingData = VikingData.get(getActivity());
                    //TODO: add logic for database login checks
                    UserData data = vikingData.getRow(mUsername, mPassword);
                    if (data != null) {
                        Log.d(TAG, "successful login");
                        Toast.makeText(getActivity(), R.string.successful_login_toast, LENGTH_SHORT).show();
                        // save username in shared preferences so other activities can access database record with the username
                        data.setCharacterName(SharedSettings.getCharacterName(getActivity()));
                        SharedPreferences settings = SharedSettings.getPreferences(getActivity());
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString(NAME, mUsername);
                        editor.apply();
                        // go to MainMenu
                        Intent i = new Intent(getActivity(), MainMenuActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(getActivity(), R.string.invalid_login_toast, LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Facebook login
        mFacebookLoginButton = (LoginButton) view.findViewById(R.id.facebook_login_button);
        mFacebookLoginButton.setReadPermissions("email");
        mFacebookLoginButton.setFragment(this);     // need this line if using in a fragment
        // callback registration
        mFacebookLoginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Profile profile = Profile.getCurrentProfile();
                if(profile != null) {
                    Log.d("facebook profile", profile.getId());
                    if (profile.getId() != null) {
                        mUsername = profile.getId();            // returns String Facebook profile ID
                    } else {
                        mUsername = "Facebook User";
                    }
                    mFirstName = profile.getFirstName();    // return Facebook profile first name
                } else {
                    mUsername = "Facebook User";
                    mFirstName = mUsername;
                }
                // creates database if first user created; otherwise retrieves existing database to add a new user
                // associate user account database record to Facebook UserID
                vikingData = VikingData.get(getActivity());
                // if record doesn't exist create new user record with Facebook ID
                UserData data = vikingData.getRow(mUsername);
                if (data == null) {
                    // insert initial user record into database with username, and default UserData values; make default password 'facebook'
                    String mPassword = "facebook";
                    data = UserData.get();
                    data.setUsername(mUsername);
                    data.setPassword(mPassword);
                    if (mFirstName != null) {
                        data.setFirstName(mFirstName);              // set first name to Facebook profile first name before storing in database
                    }
                    vikingData.insertRow(data);
                    Log.d(TAG, "successful Facebook account creation");
                    Toast.makeText(getActivity(), R.string.facebook_account_create_toast, LENGTH_SHORT).show();
                } else {
                    data.setCharacterName(SharedSettings.getCharacterName(getActivity()));
                }
                // store username (FacebookID) in shared preferences
                SharedPreferences settings = SharedSettings.getPreferences(getActivity());
                SharedPreferences.Editor editor = settings.edit();
                editor.putString(NAME, mUsername);
                editor.apply();
                Log.d(TAG, "successful Facebook login");
                Toast.makeText(getActivity(), R.string.successful_facebook_login_toast, LENGTH_SHORT).show();
                // go to MainMenu
                Intent i = new Intent(getActivity(), MainMenuActivity.class);
                startActivity(i);
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException error) {
            }
        }); // end Facebook login button

        mNewAccountButton = (Button) view.findViewById(R.id.new_account_button);
        mNewAccountButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), NewAccountActivity.class);
                startActivity(i);
            }
        });

        // display a main menu button if user is logged in with Facebook to prevent having to log out and relog to proceed to main menu
        if (AccessToken.getCurrentAccessToken() != null) {
            mMainMenuButton = (Button) view.findViewById(R.id.main_menu_button);
            mMainMenuButton.setVisibility(View.VISIBLE);
            mMainMenuButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), MainMenuActivity.class);
                    startActivity(i);
                }
            });
        } // end if

        return view;
    } // end onCreateView()

    public void onPause() {
        super.onPause();
        // stop Facebook token and profile tracking
        mTokenTracker.stopTracking();
        mProfileTracker.stopTracking();
    }

    public void onStop() {
        super.onStop();
        // stop Facebook token and profile tracking
        mTokenTracker.stopTracking();
        mProfileTracker.stopTracking();
    }

    public void onResume() {
        super.onResume();
        // stop Facebook token and profile tracking
        mTokenTracker.startTracking();
        mProfileTracker.startTracking();
        checkMenuButton();
    }

    // forward Facebook login results to mCallbackManager
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        checkMenuButton();
    }

    private void checkMenuButton() {
        View v = getView();
        mMainMenuButton = (Button) v.findViewById(R.id.main_menu_button);
        if (AccessToken.getCurrentAccessToken() != null) {
            // go direct to main menu screen if user already Facebook logged in
            //Intent i = new Intent(getActivity(), MainMenuActivity.class);
            //startActivity(i);

            if (mMainMenuButton != null) {
                mMainMenuButton.setVisibility(View.VISIBLE);
            }
            /*
            mMainMenuButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), MainMenuActivity.class);
                    startActivity(i);
                }
            });
            */
        } else {
            if (mMainMenuButton != null) {
                mMainMenuButton.setVisibility(View.INVISIBLE);
            }
        }
    }

}
