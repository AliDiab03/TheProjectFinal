package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theprojectfinal.R;


public class Adapter_Show_Day_Month extends RecyclerView.Adapter<Adapter_Show_Day_Month.myHolder> {
    Context context ;
    String [] day ;
    itemClick itemClick ;

    public Adapter_Show_Day_Month(Context context, String[] day,itemClick itemClick ) {
        this.context = context;
        this.day = day;
        this.itemClick = itemClick ;
    }

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.iteam_activity_see_day_month,parent,false);
        return new myHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder holder, int position) {
         String days = day[position];
         holder.btnShowDay.setText(days);
         holder.btnShowDay.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 itemClick.isClicked(holder.getAdapterPosition());
             }
         });
    }

    @Override
    public int getItemCount() {
        return day.length;
    }

    public class myHolder extends RecyclerView.ViewHolder {
        Button btnShowDay ;
        public myHolder(@NonNull View itemView) {
            super(itemView);
            btnShowDay =  itemView.findViewById(R.id.btnShowDay);
        }
    }
    public interface itemClick{
        void isClicked (int position);
    }
}
