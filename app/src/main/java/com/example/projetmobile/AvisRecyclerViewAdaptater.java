package com.example.projetmobile;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetmobile.avisDB.Avisweb;

import java.util.LinkedList;
import java.util.List;

public class AvisRecyclerViewAdaptater extends RecyclerView.Adapter<AvisRecyclerViewAdaptater.MyViewHolder> {

    Context mContext;
    LinkedList<Avisweb> mData;

    public AvisRecyclerViewAdaptater(Context mcontext, LinkedList<Avisweb> data) {
        this.mContext = mcontext;
        this.mData = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item, parent,false);
        MyViewHolder vHolder = new MyViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.url.setText(mData.get(position).get_web());

        int yellow = Color.argb(255, 255, 255, 0);
        int white = Color.argb(255, 255, 255, 255);
        if (mData.get(position).get_score() < 2) {
            holder.etoile2.setColorFilter(white);
        }

        if (mData.get(position).get_score() < 3) {
            holder.etoile3.setColorFilter(white);
        }

        if (mData.get(position).get_score() < 4) {
            holder.etoile4.setColorFilter(white);
        }

        if (mData.get(position).get_score() < 5) {
            holder.etoile5.setColorFilter(white);
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView url;
        private ImageView etoile2;
        private ImageView etoile3;
        private ImageView etoile4;
        private ImageView etoile5;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            url = (TextView) itemView.findViewById(R.id.Web);
            etoile2 = (ImageView) itemView.findViewById(R.id.etoile2);
            etoile3 = (ImageView) itemView.findViewById(R.id.etoile3);
            etoile4 = (ImageView) itemView.findViewById(R.id.etoile4);
            etoile5 = (ImageView) itemView.findViewById(R.id.etoile5);


        }
    }
}
