package com.example.asm_mob201.Adapter;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_mob201.R;
import com.example.asm_mob201.service.SvMusic1;

import java.util.ArrayList;
import java.util.List;

public class AdapterMusic extends RecyclerView.Adapter<AdapterMusic.ViewHolder> {
    Context context;
    ArrayList<String> listMuic = new ArrayList<>();
    //
    ComponentName sv = null;

    public AdapterMusic(Context context, ArrayList<String> listMuic) {
        this.context = context;
        this.listMuic = listMuic;
    }

    @Override
    public AdapterMusic.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_music1 , parent ,false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMusic.ViewHolder holder, int position)
    {
        int id = position;
        holder.tv1.setText("Name Music: " + listMuic.get(position));
        holder.btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        holder.btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent((Activity)context , SvMusic1.class);
                context.stopService(intent);
                sv = null;
            }
        });
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sv == null){
                    Intent intent = new Intent((Activity)context , SvMusic1.class);
                    sv = context.startService(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMuic.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv1;
        Button btnPlay , btnStop;
        LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.title);
            btnPlay = itemView.findViewById(R.id.btnPlay);
            layout = itemView.findViewById(R.id.lv1234);
            btnStop = itemView.findViewById(R.id.btnStop);
        }
    }
}
