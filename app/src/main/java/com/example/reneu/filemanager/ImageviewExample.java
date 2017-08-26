package com.example.reneu.filemanager;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by rene on 20-8-17.
 */

public class ImageviewExample extends Activity implements View.OnClickListener {

    private ImageView imgView;

    private String fileName;
    private int counter;

    private ArrayList<String> imgList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        imgView = (ImageView) findViewById(R.id.imageView);
        Bundle bundle = getIntent().getExtras();

        if (bundle.get("filenames") != null) {
            imgList = (ArrayList<String>) bundle.get("filenames");
            Integer idx = (Integer) bundle.get("index");
            String fm = imgList.get(idx);
            fileName = fm;
        }


        Button b = (Button) findViewById(R.id.btnLoadImage);
        b.setOnClickListener(this);
        File imgfile = new File(fileName);


        Bitmap myBitmap = BitmapFactory.decodeFile(imgfile.getAbsolutePath());

        imgView.setImageBitmap(myBitmap);

    }

    @Override
    public void onClick(View view) {
        counter++;
        fileName = imgList.get(counter);
        File imgfile = new File(fileName);
        Bitmap myBitmap = BitmapFactory.decodeFile(imgfile.getAbsolutePath());

        imgView.setImageBitmap(myBitmap);

    }
}