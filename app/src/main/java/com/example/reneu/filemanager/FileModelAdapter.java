package com.example.reneu.filemanager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by rene on 19-8-17.
 */

public class FileModelAdapter extends ArrayAdapter<FileModel> {

    private ImageView imgView;

    public FileModelAdapter(Context context, ArrayList<FileModel> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        FileModel fm = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_filemodel, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvSize = (TextView) convertView.findViewById(R.id.tvSize);

        convertView.findViewById(R.id.imageView);
        // Populate the data into the template view using the data object
        tvName.setText(fm.name);
        tvSize.setText(fm.size);
        // Return the completed view to render on screen

        File imgfile = new File(fm.fullName);


        Bitmap myBitmap = BitmapFactory.decodeFile(imgfile.getAbsolutePath());

        imgView.setImageBitmap(myBitmap);

        return convertView;
    }
}

