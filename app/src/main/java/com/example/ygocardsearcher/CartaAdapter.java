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

        switch(carta.getType()){
            case "Normal Monster":
            case "Normal Tuner Monster":
                holder.ivCartita.setImageResource(R.drawable.normal_monster);
                break;
            case "Ritual Monster":
            case "Ritual Effect Monster":
                holder.ivCartita.setImageResource(R.drawable.ritual_monster);
                break;
            case "Fusion Monster":
                holder.ivCartita.setImageResource(R.drawable.fusion);
                break;
            case "Synchro Monster":
            case "Synchro Tuner Monster":
                holder.ivCartita.setImageResource(R.drawable.synchro);
                break;
            case "XYZ Monster":
                holder.ivCartita.setImageResource(R.drawable.xyz);
                break;
            case "Pendulum Effect Monster":
            case "Pendulum Flip Effect Monster":
            case "Pendulum Tuner Effect Monster":
                holder.ivCartita.setImageResource(R.drawable.pendulum_effect);
                break;
            case "Pendulum Normal Monster":
                holder.ivCartita.setImageResource(R.drawable.pendulum_normal);
                break;
            case "Pendulum Effect Fusion Monster":
                holder.ivCartita.setImageResource(R.drawable.pendulum_fusion);
                break;
            case "Synchro Pendulum Effect Monster":
                holder.ivCartita.setImageResource(R.drawable.pendulum_synchro);
                break;
            case "XYZ Pendulum Effect Monster":
                holder.ivCartita.setImageResource(R.drawable.pendulum_xyz);
                break;
            case "Link Monster":
                holder.ivCartita.setImageResource(R.drawable.link);
                break;
            case "Spell Card":
                holder.ivCartita.setImageResource(R.drawable.spell_card);
                break;
            case "Trap Card":
                holder.ivCartita.setImageResource(R.drawable.trap_card);
                break;
            default:
                holder.ivCartita.setImageResource(R.drawable.effect);
                break;
        }

        holder.setIndex(position);
    }

    @Override
    public int getItemCount() {
        return this.cartas.size();
    }
}
