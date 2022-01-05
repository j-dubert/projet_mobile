package com.example.projetmobile;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab_layout);
        pager2 = findViewById(R.id.view_pager2);


        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(fm, getLifecycle());
        pager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Navigation"));
        tabLayout.addTab(tabLayout.newTab().setText("Vos Recherches"));

        avisdb = new DbAvis(this);



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
    public void onButtonClicked(View view) {

        TextView url = (TextView)findViewById(R.id.recherche);
        String test = "http://"+url.getText().toString();

        //Toast.makeText(MainActivity.this, test, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(test));
        startActivity(intent);
        insertDb(test);






    }

    public void insertDb(String str){

        Avisweb avis = new Avisweb(str,5);
        boolean boo = avisdb.findurl(str);
        System.out.println(boo);
        if(boo==false){
            avisdb.insertAvis(avis);
        }else{
            Toast.makeText(MainActivity.this, "déjà dedans tarba", Toast.LENGTH_SHORT).show();
        }

    }

}