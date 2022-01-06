package com.example.projetmobile.fragment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.projetmobile.AvisRecyclerViewAdaptater;
import com.example.projetmobile.R;
import com.example.projetmobile.avisDB.AvisSQLite;
import com.example.projetmobile.avisDB.Avisweb;

import com.example.projetmobile.avisDB.DbAvis;

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



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);

        LinkedList<Avisweb> a1 = new LinkedList<>();
        Cursor c = DbAvis.getBdd().query(AvisSQLite.TABLE_AVIS, new String[]{AvisSQLite.COLUMN_AVIS_URL,AvisSQLite.COLUMN_AVIS_SCORE},
                null,null,null,null,AvisSQLite.COLUMN_AVIS_SCORE);
        while (c.moveToNext()){
            Avisweb a = new Avisweb(c.getString(c.getColumnIndexOrThrow(AvisSQLite.COLUMN_AVIS_URL)),
                    c.getInt(c.getColumnIndexOrThrow(AvisSQLite.COLUMN_AVIS_SCORE)));
            System.out.println(a);
            a1.add(a);
        }

        AvisRecyclerViewAdaptater adaptater = new AvisRecyclerViewAdaptater(getContext(), a1);

        recyclerView.setAdapter(adaptater);
        return view;

    }
}