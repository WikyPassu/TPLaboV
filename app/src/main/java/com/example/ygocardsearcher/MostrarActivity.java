package com.example.ygocardsearcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class MostrarActivity extends AppCompatActivity implements Handler.Callback {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Detalles de la carta");
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle extras = super.getIntent().getExtras();
        Integer id = extras.getInt("id");
        String name = extras.getString("name");
        String type = extras.getString("type");
        String desc = extras.getString("desc");
        String atk = extras.getString("atk");
        String def = extras.getString("def");
        String level = extras.getString("level");
        String race = extras.getString("race");
        String attribute = extras.getString("attribute");
        String archetype = extras.getString("archetype");
        String img_url = extras.getString("img_url");

        TextView tvName = this.findViewById(R.id.tvName);
        tvName.setText(name);
        TextView tvTypeId = this.findViewById(R.id.tvTypeId);
        tvTypeId.setText(type + " / ID: " + id);
        TextView tvLevel = this.findViewById(R.id.tvLevel);
        tvLevel.setText(level);
        TextView tvAttribute = this.findViewById(R.id.tvAttribute);
        tvAttribute.setText(attribute);
        TextView tvRace = this.findViewById(R.id.tvRace);
        tvRace.setText(race);
        TextView tvAtkDef = this.findViewById(R.id.tvAtkDef);
        tvAtkDef.setText("ATK/ " + atk + " DEF/ " + def);
        TextView tvDesc = this.findViewById(R.id.tvDesc);
        tvDesc.setText(desc);
        TextView tvArchetype = this.findViewById(R.id.tvArchetype);
        tvArchetype.setText("Archetype: " + archetype);

        this.handler = new Handler(Looper.myLooper(), this);
        HiloConexion hiloImg = new HiloConexion(this.handler, true, img_url);
        hiloImg.start();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == android.R.id.home){
            super.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean handleMessage(@NonNull Message message) {
        if(message.arg1 == HiloConexion.IMAGEN){
            ImageView iv = super.findViewById(R.id.ivImg);
            byte[] img = (byte[]) message.obj;
            iv.setImageBitmap(BitmapFactory.decodeByteArray(img, 0, img.length));
            Log.d("Callback", "Llego imagen");
        }

        return false;
    }
}