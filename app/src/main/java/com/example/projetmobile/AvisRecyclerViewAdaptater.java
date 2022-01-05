package com.example.projetmobile;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        holder.score.setText(mData.get(position).get_score());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView url;
        private TextView score;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            url = (TextView) itemView.findViewById(R.id.Web);
            score = (TextView) itemView.findViewById(R.id.score);


        }
    }
}
