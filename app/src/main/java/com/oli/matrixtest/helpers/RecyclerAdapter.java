package com.oli.matrixtest.helpers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oli.matrixtest.R;
import com.oli.matrixtest.klasi.List;
import com.oli.matrixtest.klasi.Model;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    public Context mContext;
    public ArrayList<List> peopleList = new ArrayList<>();
   public Model model;

    public RecyclerAdapter(Context mContext) {
        this.mContext = mContext;
//        peopleList=model.list;
    }
    public void setItems(ArrayList<List> movielist){

        this.model.list = movielist;
        notifyDataSetChanged();
    }



    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.forecast_row,parent,false);
        RecyclerAdapter.ViewHolder viewHolder = new RecyclerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, final int position) {
        final List people = peopleList.get(position);
        holder.info.setText(String.format("%s",peopleList.get(position).getMain().getTemp()));

    }

    @Override
    public int getItemCount() {
        if (peopleList==null){
            return 0;
        }
        return peopleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtTemp)
        TextView info;
        @BindView(R.id.txtDesc)
        TextView desc;
        @BindView(R.id.txtHum)
        TextView hum;
        @BindView(R.id.slika_prognoza)
        ImageView slikaProg;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
