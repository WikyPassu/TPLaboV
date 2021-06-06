package com.example.ygocardsearcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IOnItemClick, Handler.Callback, View.OnClickListener{

    List<CartaModel> cartas = new ArrayList<CartaModel>();
    CartaAdapter adapter;
    RecyclerView rv;
    Handler handler;
    EditText etBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.rv = super.findViewById(R.id.rvCartas);
        this.rv.setVisibility(View.GONE);

        Button btnBuscar = super.findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(this);

        this.etBuscar = super.findViewById(R.id.etBuscar);
    }

    @Override
    public void onItemClick(int index) {
        CartaModel carta = this.cartas.get(index);
        Log.d("Click", carta.getName());
        Intent intent = new Intent(this, MostrarActivity.class);
        intent.putExtra("id", carta.getId());
        intent.putExtra("name", carta.getName());
        intent.putExtra("type", carta.getType());
        intent.putExtra("desc", carta.getDesc());
        intent.putExtra("atk", carta.getAtk());
        intent.putExtra("def", carta.getDef());
        intent.putExtra("level", carta.getLevel());
        intent.putExtra("race", carta.getRace());
        intent.putExtra("attribute", carta.getAttribute());
        intent.putExtra("archetype", carta.getArchetype());
        intent.putExtra("image_url", carta.getImage_url());
        startActivity(intent);
    }

    @Override
    public boolean handleMessage(@NonNull Message message) {
        if(message.arg1 == HiloConexion.CARTAS){
            this.cartas = (List<CartaModel>) message.obj;
            this.adapter = new CartaAdapter(this.cartas, this, this);
            this.rv.setAdapter(this.adapter);
            this.rv.setLayoutManager(new LinearLayoutManager(this));
            this.rv.setVisibility(View.VISIBLE);
            Log.d("Callback", "Llego cartas");
        }

        return false;
    }

    @Override
    public void onClick(View view) {
        String busqueda = String.valueOf(this.etBuscar.getText());
        if(!"".equals(busqueda)){
            String s = busqueda.replace(" ", "%20");
            this.handler = new Handler(Looper.myLooper(), this);
            HiloConexion hiloApi = new HiloConexion(this.handler, false, "https://db.ygoprodeck.com/api/v7/cardinfo.php?fname=" + s + "&desc=" + s);
            hiloApi.start();
        }
    }
}