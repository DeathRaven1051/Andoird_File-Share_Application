package com.example.konnekt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
//import android.os.Environment;
//import android.util.Log;
//import android.widget.Button;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
//import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private List<App> apps = new ArrayList<>();
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.app_list);


        //Get App List
        PackageManager pm = getApplicationContext().getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo packageInfo: packages){

            String name;

            if((name = String.valueOf(pm.getApplicationLabel(packageInfo))).isEmpty()){
                name = packageInfo.sourceDir;
            }

            Drawable icon = pm.getApplicationIcon(packageInfo);
            String apkPath = packageInfo.sourceDir;
            long apkSize = new File(packageInfo.sourceDir).length();


            apps.add(new App(name, icon, apkPath , apkSize));


        }


        Collections.sort(apps, new Comparator<App>() {
                    @Override
                    public int compare(App app, App t1) {
                        return app.getName().toLowerCase().compareTo(t1.getName().toLowerCase());
                    }
                }
        );


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        AppAdapter appAdapter = new AppAdapter(this, apps);
        recyclerView.setAdapter(appAdapter);

    }
}