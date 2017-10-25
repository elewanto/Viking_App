package com.mygdx.game;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Kevin on 10/30/16.
 */

public class StartGameDialogFragment extends Fragment {
    private Button mNextButton;
    private TextView mDialogTextBeginning;
    private TextView mMovingTextBox;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_game_dialog, container, false);
        mNextButton = (Button) view.findViewById(R.id.start_game_next_button);
        mDialogTextBeginning = (TextView) view.findViewById(R.id.start_game_dialog_beginning);
        mMovingTextBox = (TextView) view.findViewById(R.id.moving_instruction_text_box);

        String mCharacterName = UserData.get().getCharacterName();

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EngineLauncherActivity.class));
            }
        });
        Resources res = getResources();
        String beginningDialogWithName =
                String.format(res.getString(R.string.start_game_dialog_beginning),mCharacterName);
        String movingText = String.format(res.getString(R.string.moving_instructions), mCharacterName);
        mDialogTextBeginning.setText(beginningDialogWithName);
        mMovingTextBox.setText(movingText);

        return view;
    }

}
