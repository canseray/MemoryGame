package com.example.memorygame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import static com.example.memorygame.Constants.NAME;

public class MainActivity extends AppCompatActivity {

    private Context context = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText mainUserNameText = findViewById(R.id.nameEditText);
        final Button playButton = findViewById(R.id.playButton);


        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mainUserNameText.getText().length() > 0){
                    Intent intent = new Intent(MainActivity.this, GameScreen.class);
                    intent.putExtra(NAME, mainUserNameText.getText().toString());
                    startActivity(intent);
                } else {
                    Toast.makeText(context,"Lütfen kullanıcı adınızı giriniz",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
