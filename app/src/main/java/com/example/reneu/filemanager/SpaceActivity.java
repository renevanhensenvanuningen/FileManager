package com.example.reneu.filemanager;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class SpaceActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private String SD_CARD_ROOT;


    // Create a List from String Array elements
    private ArrayList<FileModel> file_list;
    private ListView lv;
    private File currentDir;

    // Create an ArrayAdapter from List
    private FileModelAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space);

        // Get reference of widgets from XML layout
        lv = (ListView) findViewById(R.id.lvSpace);


        //    final Button btnUp = (Button) findViewById(R.id.btnDirUpSpace);
        //   btnUp.setOnClickListener(new View.OnClickListener() {
        //       @Override
        //       public void onClick(View view) {
        //           doButtonUp();
        //        }
        //   });

        file_list = new ArrayList<FileModel>();
        arrayAdapter = new FileModelAdapter(this, file_list);
        lv.setOnItemClickListener(this);

        // Initializing a new String Array
        String[] fruits = new String[] {
        };


        // DataBind ListView with items from ArrayAdapter
        lv.setAdapter(arrayAdapter);
        start();


    }

    private void doButtonUp()
    {
        file_list.clear();
        File f = new File(currentDir.getParent());
        currentDir = f.getAbsoluteFile();
        if (f.canRead())
        {
            File[] files = f.listFiles();
            for (File fi: files
                    )
            {
                file_list.add(new FileModel(fi.getName(), fi.getAbsolutePath(), f.length()));
            }
        }
        //   File f = Environment.
        //   findFiles(f);
        arrayAdapter.notifyDataSetChanged();
    }

    private void findFiles(File f)
    {
        if (f.isDirectory())
        {
            //   if (f.canRead())
            {
                File[] fi = f.listFiles();
                findRecursive(fi);
            }

        }
    }

    private void findRecursive(File [] fi)
    {
        for (File f: fi
                )
        {
            if (f.isDirectory() )
            {
                File[] fis = f.listFiles();
                if (fis != null)
                    findRecursive(fis);
            }
            else

            {   String filename = f.getName();
                if (filename.contains("."))
                {
                    String ext =  filename.substring(filename.lastIndexOf(".") + 1, filename.length());
                    if (ext.compareToIgnoreCase("jpg") == 0)
                        file_list.add(new FileModel(f.getName(), f.getAbsolutePath(), f.length()));
                }
            }

        }

    }

    private long getDirSize(File dirs) {
        long size = 0;
        for (File file : dirs.listFiles()) {
            if (file.isFile())
                size += file.length();
            else
                size += getDirSize(file);
        }
        //   System.out.println("DIR - " + dirs+" "+ friendlyFileSize(size));
        return size;

    }

    private long findSpaceRecursive(File fs)
    {

        long ret = 0;
        if (fs.isFile())
            ret = fs.length();
        else {

        if (fs.isDirectory())
        {
            File [] fis = fs.listFiles();
            for (File f: fis)
            {
                ret += findSpaceRecursive(f);
            }
        }
        }
        return ret;
    }


    public void start()
    {
        File[] fs = new File[1];
        File f = Environment.getExternalStorageDirectory();
        fs[0] = f;
        // long space =  getDirSize(f);
        long space = findSpaceRecursive(f);
        file_list.add(new FileModel(f.getName(), f.getAbsolutePath(), space));

        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        // fruits_list = FindFiles();

        // File f = Environment.getDataDirectory();
        // fruits_list.add(f.getAbsolutePath());

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        FileModel fm = (FileModel) adapterView.getItemAtPosition(i);

        File f = new File(fm.fullName);
        file_list.clear();
        if (f.isDirectory())
        {
            currentDir = f.getAbsoluteFile();
            if (f.canRead()) {
                File[] files = f.listFiles();
                for (File fi : files
                        ) {
                    file_list.add(new FileModel(fi.getName(), fi.getAbsolutePath(), findSpaceRecursive(fi)));
                }
                arrayAdapter.notifyDataSetChanged();
            }
        }


    }






}