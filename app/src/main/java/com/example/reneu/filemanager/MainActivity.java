package com.example.reneu.filemanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{

    private Button imgButton;
    private Button spaceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgButton = (Button) findViewById(R.id.btnImg);

        imgButton.setOnClickListener(this);

        spaceButton = (Button) findViewById(R.id.btnSpace);
        spaceButton.setOnClickListener(this);

    }



    /** Called when the user taps the Send button */
    public void sendMessage(View view) {

        Button b = (Button) view;
        if (b == imgButton)
        {
            Intent intent = new Intent(this, MyImgActivity.class);
            startActivity(intent);
        }
        else
            if (b == spaceButton)
        {
            Intent intent = new Intent(this, SpaceActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        sendMessage(v);
    }
}
