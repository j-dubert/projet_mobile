package com.example.projetmobile.fragment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.projetmobile.R;
import com.example.projetmobile.avisDB.AvisSQLite;
import com.example.projetmobile.avisDB.Avisdaptater;
import com.example.projetmobile.avisDB.Avisweb;
import com.example.projetmobile.avisDB.DbAvis;

import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public LinkedList<Avisweb> readbdd(){

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

        return(a1);

    }

    @Override
    public void onResume(){
        super.onResume();
        readbdd();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View result =  inflater.inflate(R.layout.fragment_list, container, false);
        LinkedList<Avisweb> li = readbdd();

        final ListView listView = (ListView) result.findViewById(R.id.liste);
        Avisdaptater adaptater = new Avisdaptater(getContext(), R.layout.item, li);
        listView.setAdapter(adaptater);
    return result;}
}