package com.example.reneu.filemanager;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by rene on 20-8-17.
 */

public class ImageviewExample extends Activity implements View.OnClickListener {

    private ImageView imgView;

    private String fileName;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        imgView = (ImageView) findViewById(R.id.imageView);
        Bundle bundle = getIntent().getExtras();

        if (bundle.get("filename") != null) {
            fileName = bundle.get("filename").toString();
        }


        Button b = (Button) findViewById(R.id.btnLoadImage);
        b.setOnClickListener(this);
        File imgfile = new File(fileName);


        Bitmap myBitmap = BitmapFactory.decodeFile(imgfile.getAbsolutePath());

        imgView.setImageBitmap(myBitmap);

    }

    @Override
    public void onClick(View view) {

    }
}