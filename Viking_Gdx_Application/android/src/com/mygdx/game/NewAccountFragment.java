package com.mygdx.game;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by Kevin on 9/25/16.
 */

public class NewAccountFragment extends Fragment {

    private String mUsername = "";
    private String mPassword = "";
    private String mConfirmPass = "";
    private EditText mUsernameField;
    private EditText mPasswordField;
    private EditText mConfirmPasswordField;
    private Button mCreateAccountButton;
    private Button mBackButton;
    private static final String NAME = "username";
    private VikingData vikingData;           // database


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_account, container, false);

        mUsernameField = (EditText) view.findViewById(R.id.account_username_text_edit);
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

        mPasswordField = (EditText) view.findViewById(R.id.account_password_text_edit);
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

        mConfirmPasswordField = (EditText) view.findViewById(R.id.account_confirm_password_text_edit);
        mConfirmPasswordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mConfirmPass = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mCreateAccountButton = (Button) view.findViewById(R.id.create_account_button);
        mCreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUsername.length() == 0 || mPassword.length() == 0 || mConfirmPass.length() == 0) {
                    Toast.makeText(getActivity(), R.string.invalid_login_toast, LENGTH_SHORT).show();
                } else if (!mPassword.equals(mConfirmPass)){
                    new AlertDialog.Builder(getActivity()).setTitle("Error").setMessage("passwords must match")
                            .setNeutralButton("Try Again", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {}
                            })
                            .show();
                } else {
                    // creates database if first user created; otherwise retrieves existing database to add a new user
                    vikingData = VikingData.get(getActivity());
                    // check if username already exists in database
                    if (vikingData.checkUsername(mUsername) == null) {
                        // insert initial user record into database with username, password, and default UserData values
                        UserData data = UserData.get();
                        data.setUsername(mUsername);
                        data.setPassword(mPassword);
                        data.setFirstName(mUsername);
                        vikingData.insertRow(data);
                        Toast.makeText(getActivity(), R.string.successful_account_create_toast, LENGTH_SHORT).show();
                        // go back to login menu after successful account creation
                        Intent i = new Intent(getActivity(), LoginActivity.class);
                        // pass username to Login activity using extras
                        i.putExtra(NAME, mUsername);
                        startActivity(i);
                    } else {    // username already exists; don't create account
                        Toast.makeText(getActivity(), R.string.invalid_account_duplicate_username_toast, LENGTH_SHORT).show();
                    }
                }
            }
        });

        mBackButton = (Button) view.findViewById(R.id.back_to_login_button);
        mBackButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
            }
        });


        return view;
    }
}
