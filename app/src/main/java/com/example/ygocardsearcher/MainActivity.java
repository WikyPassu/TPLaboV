package com.example.ygocardsearcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IOnItemClick, Handler.Callback {

    List<CartaModel> cartas = new ArrayList<CartaModel>();
    CartaAdapter adapter;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this.handler = new Handler(Looper.myLooper(), this);
        //HiloConexion hiloCartas = new HiloConexion(this.handler, false);
        //hiloCartas.start();

        CartaModel c1 = new CartaModel(1861629, "Decode Talker", "Link Monster", "2+ Effect Monsters\nGains 500 ATK for each monster it points to. When your opponent activates a card or effect that targets a card(s) you control (Quick Effect): You can Tribute 1 monster this card points to; negate the activation, and if you do, destroy that card.", "2300", "3", null,"Cyberse", "DARK", "Code Talker", "https://storage.googleapis.com/ygoprodeck.com/pics/1861630.jpg");
        CartaModel c2 = new CartaModel(55144522, "Pot of Greed", "Spell Card", "Draw 2 cards", null, null, null,"Normal", null, "Greed", "https://storage.googleapis.com/ygoprodeck.com/pics/55144522.jpg");
        CartaModel c3 = new CartaModel(61962135, "Glorious Illusion", "Trap Card", "Activate this card by targeting 1 \\\"Lightsworn\\\" monster in your Graveyard; Special Summon that target in face-up Attack Position. During each of your End Phases: Send the top 2 cards of your Deck to the Graveyard. When this card leaves the field, destroy that monster. When that monster leaves the field, destroy this card.", null, null, null,"Continuous", null, "Lightsworn", "https://storage.googleapis.com/ygoprodeck.com/pics/61962135.jpg");
        this.cartas.add(c1);
        this.cartas.add(c2);
        this.cartas.add(c3);

        this.adapter = new CartaAdapter(this.cartas, this, this);
        RecyclerView rv = super.findViewById(R.id.rvCartas);
        rv.setAdapter(this.adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
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
            RecyclerView rv = super.findViewById(R.id.rvCartas);
            rv.setAdapter(this.adapter);
            rv.setLayoutManager(new LinearLayoutManager(this));

            Log.d("Callback", "Llego cartas");
        }

        return false;
    }
}