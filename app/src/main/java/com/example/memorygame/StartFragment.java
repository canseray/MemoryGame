package com.example.memorygame;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StartFragment extends Fragment {

    private static final String LOG_TAG = StartFragment.class.getSimpleName();
    private Button playButton;
    public EditText nameTextView;

    public StartFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_start, container, false);


        final EditText nameTextView = view.findViewById(R.id.nameEditText);
        playButton = view.findViewById(R.id.playButton);


        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userNamePut = nameTextView.getText().toString();
                Log.i(LOG_TAG,"blaa"+ userNamePut);


                GameFragment myGameFragment = new GameFragment();
                Bundle bundle = new Bundle();
                bundle.putString("userName",userNamePut);
                myGameFragment.setArguments(bundle);

                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_container,myGameFragment,"GameFragment");
                transaction.commit();
            }
        });

        return view;
    }

}
