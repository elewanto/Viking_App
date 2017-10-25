package com.mygdx.game;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.facebook.AccessToken;

/**
 * Created by Kevin on 10/30/16.
 */

public class AccountManageFragment extends Fragment {

    private EditText mFirstNameEdit;
    private EditText mCharacterNameEdit;
    private String mFirstName;
    private String mCharacterName;
    private UserData data;

    private static final String CHARACTER_NAME = "characterName";

    @Override
    public void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_manage, container, false);

        data = UserData.get();

        mFirstName = data.getFirstName();
        mCharacterName = SharedSettings.getCharacterName(getActivity());

        mFirstNameEdit = (EditText) view.findViewById(R.id.first_name_edit);
        mFirstNameEdit.setText(mFirstName);
        if (AccessToken.getCurrentAccessToken() != null) {
            mFirstNameEdit.setEnabled(false);
        }
        mFirstNameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mFirstName = s.toString();
                data.setFirstName(mFirstName);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mCharacterNameEdit = (EditText) view.findViewById(R.id.character_name_edit);
        mCharacterNameEdit.setText(mCharacterName);
        mCharacterNameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCharacterName = s.toString();
                data.setCharacterName(mCharacterName);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

        SharedPreferences settings = SharedSettings.getPreferences(getActivity());
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(CHARACTER_NAME, mCharacterName);
        editor.apply();
    }
}
