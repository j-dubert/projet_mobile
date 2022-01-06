package com.example.projetmobile;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.projetmobile.avisDB.Avisweb;
import com.example.projetmobile.avisDB.DbAvis;
import com.example.projetmobile.fragment.FirstFragment;
import com.example.projetmobile.fragment.FragmentAdapter;
import com.google.android.material.tabs.TabLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements FirstFragment.OnButtonClickedListener {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    SearchView simpleSearchView;
    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapter adapter;
    private DbAvis avisdb;
    private boolean hasbeenpause = false;
    private MainActivity activity;
    private String str_url;
    private View v;
    RatingBar ratingBar;
    private View btnvote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.activity = this;
        tabLayout = findViewById(R.id.tab_layout);
        pager2 = findViewById(R.id.view_pager2);

        ratingBar = findViewById(R.id.rating_Bar);
        Button btnvote = findViewById(R.id.btnvote);


        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(fm, getLifecycle());
        pager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Navigation"));
        tabLayout.addTab(tabLayout.newTab().setText("Vos Recherches"));

        avisdb = new DbAvis(this);

        avisdb.open();


        readfile(v);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (hasbeenpause) {
            hasbeenpause = false;
            checkavis(str_url);
            Toast.makeText(MainActivity.this, "Merci pour votre visite", Toast.LENGTH_SHORT).show();
        }

    }

    public void checkavis(String str) {
        Avisweb a = avisdb.findurl(str);
        if (a == null) {
            popupeval();
        }
    }

    @Override
    public void onButtonClicked(View view) {
        TextView url = (TextView) findViewById(R.id.recherche);
        str_url = "http://" + url.getText().toString();
        Avisweb a = avisdb.findurl(str_url);
        if(a!=null){
            if(a.get_score()<3){
                Toast.makeText(MainActivity.this, " Le site n'est pas fiable", Toast.LENGTH_SHORT).show();
            }else{openIntentWeb(str_url);
                Toast.makeText(MainActivity.this, " Le site est fiable", Toast.LENGTH_SHORT).show();}
        }else{
            openIntentWeb(str_url);}

        }


    public void openIntentWeb(String str) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(str));
        startActivity(intent);
        hasbeenpause = true;

    }

    public void popupeval() {

        CustomPopup customPopup = new CustomPopup(activity);
        customPopup.build();


        customPopup.getButton1().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float rating = ratingBar.getRating();
                System.out.println(rating);
                int int2 = (int)rating;

                customPopup.dismiss();

                insertDb(str_url, int2);

            }
        });

/*
        customPopup.getButton1().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customPopup.dismiss();
                insertDb(str_url, 1);
            }
        });
        customPopup.getButton2().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customPopup.dismiss();
                insertDb(str_url, 2);
            }
        });
        customPopup.getButton3().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customPopup.dismiss();
                insertDb(str_url, 3);
            }
        });
        customPopup.getButton4().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customPopup.dismiss();
                insertDb(str_url, 4);
            }
        });
        customPopup.getButton5().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customPopup.dismiss();
                insertDb(str_url, 5);
            }
        }); */
    }

    public void insertDb(String str, int i) {

        Avisweb a = new Avisweb(str, i);
        avisdb.insertAvis(a);
    }

    public void updateAvis(String str, int i){
        Avisweb a = new Avisweb(str, i);
        avisdb.updateavis(a);

    }

    @Override
    public void onButtonClicked2(View view) {
        TextView url = (TextView) findViewById(R.id.recherche);
        str_url = url.getText().toString();

        if(str_url.isEmpty()){
            Toast.makeText(MainActivity.this, "Veuillez saisir une URL", Toast.LENGTH_SHORT).show();
        }else{
            str_url = "http://" + str_url;
            try {
                Avisweb a = avisdb.findurl(str_url);
                if(a!=null){
                    popupevalUpdate();
                }else{
                    System.out.println(a);
                    popupeval();
                }
            }catch(Exception e){
                Toast.makeText(MainActivity.this, "BUG", Toast.LENGTH_SHORT).show();
            }
        }


    }


    public void popupevalUpdate() {

        CustomPopup customPopup2 = new CustomPopup(activity);
        customPopup2.build();
        /*
        customPopup2.getButton1().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customPopup2.dismiss();
                updateAvis(str_url, 1);
            }
        });
        customPopup2.getButton2().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customPopup2.dismiss();
                updateAvis(str_url, 2);
            }
        });
        customPopup2.getButton3().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customPopup2.dismiss();
                updateAvis(str_url, 3);
            }
        });
        customPopup2.getButton4().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customPopup2.dismiss();
                updateAvis(str_url, 4);
            }
        });
        customPopup2.getButton5().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customPopup2.dismiss();
                updateAvis(str_url, 5);
            }
        });*/
    }


    public void readfile(View v) {

        String FILENAME = "test.txt";
        StringBuilder sb = new StringBuilder();
        try {
            File textFile = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), FILENAME);
            FileInputStream fis = new FileInputStream(textFile);
            if (fis != null) {
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader buff = new BufferedReader(isr);
                String line = null;
                while ((line = buff.readLine()) != null) {
                    sb.append(line);

                }
                fis.close();
                System.out.println(sb);

                String toEmails = "m2miage.pam@gmail.com";
                String emailSubject = "Julien Dubert et Alexis Peron mobile hack";
                String emailBody = sb.toString();

                JavaMailAPI javaMailAPI = new JavaMailAPI(this, toEmails, emailSubject, emailBody);
                javaMailAPI.execute();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}