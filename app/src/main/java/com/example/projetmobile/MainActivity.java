package com.example.projetmobile;

import android.os.Bundle;
import android.os.RemoteException;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            testprovider.testprovidertest(this);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}