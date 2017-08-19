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
    private List<String> fruits_list;
    private ListView lv;
    private File currentDir;

    // Create an ArrayAdapter from List
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space);

        // Get reference of widgets from XML layout
        lv = (ListView) findViewById(R.id.lvSpace);


        final Button btnUp = (Button) findViewById(R.id.btnDirUpSpace);
        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doButtonUp();
            }
        });

        fruits_list = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fruits_list);
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
        fruits_list.clear();
        File f = new File(currentDir.getParent());
        currentDir = f.getAbsoluteFile();
        if (f.canRead())
        {
            File[] files = f.listFiles();
            for (File fi: files
                    )
            {
                fruits_list.add(fi.getAbsolutePath());
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
                        fruits_list.add(f.getAbsolutePath());
                }
            }

        }

    }

    private double findSpaceRecursive(File fs)
    {
        if (fs.isDirectory())
        {
            File [] fis = fs.listFiles();
            for (File f: fis)
            {
                findSpaceRecursive(f);
            }

        }
         return (fs.getTotalSpace()/ 1024/ 1024);
    }

    public void start()
    {
        File[] fs = new File[1];
        File f = Environment.getExternalStorageDirectory();
        fs[0] = f;
        double space =  findSpaceRecursive(f);


        String s = Environment.getExternalStorageState();
        if (s.compareTo("mounted")==0)
        {
            f = Environment.getExternalStorageDirectory();
            fruits_list.add(f.getAbsolutePath());
            fruits_list.add(Double.toString(space));
            if (f.isDirectory())
            {
                if (f.canRead())
                {
                    File[] files = f.listFiles();
                    for (File fi: files
                            )
                    {
                        //     fruits_list.add(fi.getAbsolutePath());
                    }

                }
            }

        }

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
        Context context = getApplicationContext();
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_LONG;

        String s = (String) adapterView.getItemAtPosition(i);
        Toast toast = Toast.makeText(context, s, 500);
        toast.show();
        File f = new File(s);
        fruits_list.clear();
        if (f.isDirectory())
        {
            currentDir = f.getAbsoluteFile();
            if (f.canRead()) {
                File[] files = f.listFiles();
                for (File fi : files
                        ) {
                    fruits_list.add(fi.getAbsolutePath());
                }
                arrayAdapter.notifyDataSetChanged();
            }
        }


    }



    private List<String> FindFiles() {
        File mFile=Environment.getExternalStorageDirectory();
        SD_CARD_ROOT=mFile.toString();

        final List<String> tFileList = new ArrayList<String>();
        Resources resources = getResources();
        // array of valid image file extensions
        String[] imageTypes = resources.getStringArray(R.array.image);
        FilenameFilter[] filter = new FilenameFilter[imageTypes.length];

        int i = 0;
        for (final String type : imageTypes) {
            filter[i] = new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.endsWith("." + type);
                }
            };
            i++;
        }

        FileUtils fileUtils = new FileUtils();
        File[] allMatchingFiles = fileUtils.listFilesAsArray(
                new File(SD_CARD_ROOT), filter, 1);
        for (File f : allMatchingFiles) {
            tFileList.add(f.getAbsolutePath());
        }
        return tFileList;
    }



}