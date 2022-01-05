package com.example.projetmobile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetmobile.placeholder.PlaceholderContent;

import com.example.projetmobile.DbAvis;

import java.util.LinkedList;

/**
 * A fragment representing a list of Items.
 */
public class ItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }



    }
    @Override
    public void onResume(){
        super.onResume();
        readbdd();



    }

    public void readbdd(){

        LinkedList<Avisweb> a1 = new LinkedList<>();
        Cursor c = DbAvis.getBdd().query(AvisSQLite.TABLE_AVIS, new String[]{AvisSQLite.COLUMN_AVIS_URL,AvisSQLite.COLUMN_AVIS_SCORE},
                null,null,null,null,AvisSQLite.COLUMN_AVIS_SCORE);
        if(c==null){

        }else{
            c.moveToFirst();
            while (c.moveToNext()){
                Avisweb a = new Avisweb(c.getString(c.getColumnIndexOrThrow(AvisSQLite.COLUMN_AVIS_URL)),
                        c.getInt(c.getColumnIndexOrThrow(AvisSQLite.COLUMN_AVIS_SCORE)));
                System.out.println(a);
                a1.add(a);


            }
            System.out.println(a1);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(PlaceholderContent.ITEMS));
        }
        return view;

    }
}