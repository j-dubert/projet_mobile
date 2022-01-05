package com.example.projetmobile.avisDB;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projetmobile.R;

import java.util.LinkedList;

public class Avisdaptater extends ArrayAdapter<Avisweb> {

    private final Context _context;
    private LinkedList<Avisweb> _avisweb;

    public Avisdaptater(Context context, int resource, LinkedList<Avisweb> aviswebs) {
        super(context, resource, aviswebs);
        _context = context;
        _avisweb = aviswebs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item, parent, false);
        } else {
            convertView = (LinearLayout) convertView;
        }

        TextView viewName = (TextView) convertView.findViewById(R.id.Web);
        viewName.setText(_avisweb.get(position).get_web());
        TextView viewNumber = (TextView) convertView.findViewById(R.id.score);
        viewNumber.setText(_avisweb.get(position).get_score());


        return convertView;
    }
}
