package com.mygdx.game;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

/**
 * Created by Kevin on 10/30/16.
 */

public class SettingsFragment extends Fragment {

    Switch mGameMusicSwitch;
    Switch mSkipDialogSwitch;
    Spinner mDifficultySpinner;
    boolean mTurnOffMusic;
    boolean mSkipDialog;
    int mDifficulty;

    private static final String DIFFICULTY = "difficulty";
    private static final String MUSIC_ON = "music";
    private static final String SKIP_DIALOG = "skipDialog";

    @Override
    public void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        mTurnOffMusic = SharedSettings.getMusicOn(getContext());
        mSkipDialog = SharedSettings.getSkipDialog(getContext());
        mDifficulty = SharedSettings.getDifficulty(getContext());

        mGameMusicSwitch = (Switch) view.findViewById(R.id.game_music_switch);
        mGameMusicSwitch.setChecked(mTurnOffMusic);
        mGameMusicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mTurnOffMusic = isChecked;
            }
        });

        mSkipDialogSwitch = (Switch) view.findViewById(R.id.skip_dialog_switch);
        mSkipDialogSwitch.setChecked(mSkipDialog);
        mSkipDialogSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mSkipDialog = isChecked;
            }
        });

        mDifficultySpinner = (Spinner) view.findViewById(R.id.difficulty_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.difficulty_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mDifficultySpinner.setAdapter(adapter);
        mDifficultySpinner.setSelection(mDifficulty);
        mDifficultySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mDifficulty = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences preferences = SharedSettings.getPreferences(getActivity());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(MUSIC_ON, mTurnOffMusic);
        editor.putBoolean(SKIP_DIALOG, mSkipDialog);
        editor.putInt(DIFFICULTY, mDifficulty);
        editor.apply();
    }

    @Override
    public void onResume() {
        super.onResume();
        mTurnOffMusic = SharedSettings.getMusicOn(getContext());
        mSkipDialog = SharedSettings.getSkipDialog(getContext());
        mDifficulty = SharedSettings.getDifficulty(getContext());
        mGameMusicSwitch.setChecked(mTurnOffMusic);
        mSkipDialogSwitch.setChecked(mSkipDialog);
        mDifficultySpinner.setSelection(mDifficulty);
    }
}
