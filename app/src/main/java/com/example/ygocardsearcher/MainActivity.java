package com.example.ygocardsearcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IOnItemClick, Handler.Callback, SearchView.OnQueryTextListener{

    List<CartaModel> cartas = new ArrayList<CartaModel>();
    CartaAdapter adapter;
    RecyclerView rv;
    Handler handler;
    TextView tvResultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tvResultados = super.findViewById(R.id.tvResultados);
        this.rv = super.findViewById(R.id.rvCartas);
        this.rv.setVisibility(View.GONE);
        this.handler = new Handler(Looper.myLooper(), this);
        HiloConexion hiloApi = new HiloConexion(this.handler, false, "https://db.ygoprodeck.com/api/v7/cardinfo.php");
        hiloApi.start();
    }

    @Override
    protected void onRestart() {
        String query = FiltrarActivity.query;
        FiltrarActivity.query = null;
        if(query != null){
            this.handler = new Handler(Looper.myLooper(), this);
            HiloConexion hiloApi = new HiloConexion(this.handler, false, query);
            hiloApi.start();
        }
        super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        MenuItem menuItem = menu.findItem(R.id.buscar);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.filtrar){
            Intent intent = new Intent(this, FiltrarActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
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
            String resultados = "Resultados: " + this.cartas.size();
            this.tvResultados.setText(resultados);
            Log.d("Callback", "Llegaron cartas!!!");
        }

        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.d("Submit", query);
        if(!"".equals(query)){
            String s = query.replace(" ", "%20");
            this.handler = new Handler(Looper.myLooper(), this);
            HiloConexion hiloApi = new HiloConexion(this.handler, false, "https://db.ygoprodeck.com/api/v7/cardinfo.php?fname=" + s + "&desc=" + s);
            hiloApi.start();
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}