package com.example.projetmobile;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements FirstFragment.OnButtonClickedListener {

    SearchView simpleSearchView;
    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapter adapter;
    private DbAvis avisdb;
    private boolean hasbeenpause = false;
    private MainActivity activity;
    private String str_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.activity=this;
        tabLayout = findViewById(R.id.tab_layout);
        pager2 = findViewById(R.id.view_pager2);


        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(fm, getLifecycle());
        pager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Navigation"));
        tabLayout.addTab(tabLayout.newTab().setText("Vos Recherches"));

        avisdb = new DbAvis(this);

        avisdb.open();



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
    protected void onResume(){
        super.onResume();
        if(hasbeenpause){
            hasbeenpause=false;
            checkavis(str_url);
            Toast.makeText(MainActivity.this,"Merci pour votre visite", Toast.LENGTH_SHORT).show();
        }

    }

    public void checkavis(String str){
        Avisweb a = avisdb.findurl(str);
        if(a==null){
            popupeval();
        }
    }

    @Override
    public void onButtonClicked(View view) {

        TextView url = (TextView)findViewById(R.id.recherche);
        str_url = "http://"+url.getText().toString();
        openIntentWeb(str_url);
    }

    public void openIntentWeb(String str){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(str));
        startActivity(intent);
        hasbeenpause = true;

    }

    public void popupeval(){

        CustomPopup customPopup = new CustomPopup(activity);
        customPopup.build();
        customPopup.getButton1().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customPopup.dismiss();
                insertDb(str_url,1);
            }
        });
        customPopup.getButton2().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customPopup.dismiss();
                insertDb(str_url,2);
            }
        });
        customPopup.getButton3().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customPopup.dismiss();
                insertDb(str_url,3);
            }
        });
        customPopup.getButton4().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customPopup.dismiss();
                insertDb(str_url,4);
            }
        });
        customPopup.getButton5().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customPopup.dismiss();
                insertDb(str_url,5);
            }
        });
    }

    public void insertDb(String str, int i) {

        Avisweb a = new Avisweb(str,i);
        avisdb.insertAvis(a);
    }

    @Override
    public void onButtonClicked2(View view){
       popupeval();
    }



}