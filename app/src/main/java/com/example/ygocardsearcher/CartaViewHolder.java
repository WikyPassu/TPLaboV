package com.example.ygocardsearcher;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CartaViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

    TextView tvNombre;
    ImageView ivCartita;
    private IOnItemClick ic;
    private Integer index;
    Activity a;

    public CartaViewHolder(View v, IOnItemClick ic, Activity a){
        super(v);
        this.tvNombre = v.findViewById(R.id.tvNombre);
        this.ivCartita = v.findViewById(R.id.ivCartita);
        this.ic = ic;
        this.a = a;
        v.setOnClickListener(this);
    }

    public void setIndex(Integer index){
        this.index = index;
    }

    @Override
    public void onClick(View view) {
        this.ic.onItemClick(this.index);
    }
}
