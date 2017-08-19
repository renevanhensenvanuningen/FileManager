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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgButton = (Button) findViewById(R.id.btnImg);

        imgButton.setOnClickListener(this);


    }



    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, MyImgActivity.class);
        //EditText editText = (EditText) findViewById(R.id.editText);
      //  String message = editText.getText().toString();
     //   intent.putExtra(EXTRA_MESSAGE, message);

        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        sendMessage(v);
    }
}
