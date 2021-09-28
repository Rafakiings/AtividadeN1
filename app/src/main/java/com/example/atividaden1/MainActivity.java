package com.example.atividaden1;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button personagem;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        personagem = (Button) findViewById(R.id.btnChar);
        personagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCharActivity();
            }
        });
    }

    public void openCharActivity(){
        Intent intent = new Intent(this, CreateChar.class);
        startActivity(intent);
    }

}


