package com.example.ygocardsearcher;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartaAdapter extends RecyclerView.Adapter<CartaViewHolder> {

    List<CartaModel> cartas;
    Activity a;
    private IOnItemClick ic;

    public CartaAdapter(List<CartaModel> cartas, Activity a, IOnItemClick ic) {
        this.cartas = cartas;
        this.a = a;
        this.ic = ic;
    }

    @NonNull
    @Override
    public CartaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        CartaViewHolder cartaViewHolder = new CartaViewHolder(v, this.ic, this.a);
        return cartaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartaViewHolder holder, int position) {
        CartaModel carta = this.cartas.get(position);
        holder.tvNombre.setText(carta.getName());
        holder.setIndex(position);
    }

    @Override
    public int getItemCount() {
        return this.cartas.size();
    }
}
