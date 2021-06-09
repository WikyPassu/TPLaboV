package com.example.ygocardsearcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MostrarActivity extends AppCompatActivity implements Handler.Callback {

    Handler handler;
    TextView tvName;
    TextView tvTypeId;
    TextView tvLevel;
    TextView tvAttribute;
    TextView tvRace;
    TextView tvAtkDef;
    TextView tvDesc;
    TextView tvArchetype;
    ImageView ivTypeId;
    ImageView ivLevel;
    ImageView ivAttribute;
    ImageView ivRace;
    CardView cvAtkDef;
    CardView cvArchetype;

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
        String img_url = extras.getString("image_url");

        this.tvName = super.findViewById(R.id.tvName);
        this.tvTypeId = super.findViewById(R.id.tvTypeId);
        this.tvLevel = super.findViewById(R.id.tvLevel);
        this.tvAttribute = super.findViewById(R.id.tvAttribute);
        this.tvRace = super.findViewById(R.id.tvRace);
        this.tvAtkDef = super.findViewById(R.id.tvAtkDef);
        this.tvDesc = super.findViewById(R.id.tvDesc);
        this.tvArchetype = super.findViewById(R.id.tvArchetype);

        this.ivTypeId = super.findViewById(R.id.ivTypeId);
        this.ivLevel = super.findViewById(R.id.ivLevel);
        this.ivAttribute = super.findViewById(R.id.ivAttribute);
        this.ivRace = super.findViewById(R.id.ivRace);

        this.cvAtkDef = super.findViewById(R.id.cvAtkDef);
        this.cvArchetype = super.findViewById(R.id.cvArchetype);

        this.tvName.setText(name);
        this.tvTypeId.setText(type + " / ID: " + id);
        this.tvLevel.setText(level);
        this.tvAttribute.setText(attribute);
        this.tvRace.setText(race);
        if("Link Monster".equals(type)){
            this.tvAtkDef.setText("ATK/ " + atk + " LINK-" + def);
        }
        else{
            this.tvAtkDef.setText("ATK/ " + atk + " DEF/ " + def);
        }
        this.tvDesc.setText(desc);
        this.tvArchetype.setText("Archetype: " + archetype);

        switch(type){
            case "Normal Monster":
            case "Normal Tuner Monster":
                this.ivTypeId.setImageResource(R.drawable.normal_monster);
                break;
            case "Ritual Monster":
            case "Ritual Effect Monster":
                this.ivTypeId.setImageResource(R.drawable.ritual_monster);
                break;
            case "Fusion Monster":
                this.ivTypeId.setImageResource(R.drawable.fusion);
                break;
            case "Synchro Monster":
            case "Synchro Tuner Monster":
                this.ivTypeId.setImageResource(R.drawable.synchro);
                break;
            case "XYZ Monster":
                this.ivTypeId.setImageResource(R.drawable.xyz);
                this.ivLevel.setImageResource(R.drawable.rango);
                break;
            case "Pendulum Effect Monster":
            case "Pendulum Flip Effect Monster":
            case "Pendulum Tuner Effect Monster":
                this.ivTypeId.setImageResource(R.drawable.pendulum_effect);
                break;
            case "Pendulum Normal Monster":
                this.ivTypeId.setImageResource(R.drawable.pendulum_normal);
                break;
            case "Pendulum Effect Fusion Monster":
                this.ivTypeId.setImageResource(R.drawable.pendulum_fusion);
                break;
            case "Synchro Pendulum Effect Monster":
                this.ivTypeId.setImageResource(R.drawable.pendulum_synchro);
                break;
            case "XYZ Pendulum Effect Monster":
                this.ivTypeId.setImageResource(R.drawable.pendulum_xyz);
                this.ivLevel.setImageResource(R.drawable.rango);
                break;
            case "Link Monster":
                this.ivTypeId.setImageResource(R.drawable.link);
                this.ivLevel.setVisibility(View.GONE);
                this.tvLevel.setVisibility(View.GONE);
                break;
            case "Spell Card":
                this.ivTypeId.setImageResource(R.drawable.spell_card);
                this.ivAttribute.setImageResource(R.drawable.spell);
                this.cvAtkDef.setVisibility(View.GONE);
                this.ivLevel.setVisibility(View.GONE);
                this.tvLevel.setVisibility(View.GONE);
                break;
            case "Trap Card":
                this.ivTypeId.setImageResource(R.drawable.trap_card);
                this.ivAttribute.setImageResource(R.drawable.trap);
                this.cvAtkDef.setVisibility(View.GONE);
                this.ivLevel.setVisibility(View.GONE);
                this.tvLevel.setVisibility(View.GONE);
                break;
            case "Token":
                this.ivTypeId.setImageResource(R.drawable.token);
                break;
            default:
                this.ivTypeId.setImageResource(R.drawable.effect);
                break;
        }

        if(archetype == null){
            this.cvArchetype.setVisibility(View.GONE);
        }

        if(!"Spell Card".equals(type) && !"Trap Card".equals(type)){
            switch(attribute){
                case "DARK":
                    this.ivAttribute.setImageResource(R.drawable.dark);
                    break;
                case "DIVINE":
                    this.ivAttribute.setImageResource(R.drawable.divine);
                    break;
                case "EARTH":
                    this.ivAttribute.setImageResource(R.drawable.earth);
                    break;
                case "FIRE":
                    this.ivAttribute.setImageResource(R.drawable.fire);
                    break;
                case "LIGHT":
                    this.ivAttribute.setImageResource(R.drawable.light);
                    break;
                case "WATER":
                    this.ivAttribute.setImageResource(R.drawable.water);
                    break;
                case "WIND":
                    this.ivAttribute.setImageResource(R.drawable.wind);
                    break;
            }
        }

        switch(race){
            case "Aqua":
                this.ivRace.setImageResource(R.drawable.aqua);
                break;
            case "Beast":
                this.ivRace.setImageResource(R.drawable.beast);
                break;
            case "Beast-Warrior":
                this.ivRace.setImageResource(R.drawable.beast_warrior);
                break;
            case "Creator-God":
            case "Fairy":
                this.ivRace.setImageResource(R.drawable.fairy);
                break;
            case "Cyberse":
            case "Machine":
                this.ivRace.setImageResource(R.drawable.machine);
                break;
            case "Dinosaur":
                this.ivRace.setImageResource(R.drawable.dinosaur);
                break;
            case "Divine-Beast":
                this.ivRace.setImageResource(R.drawable.divine_beast);
                break;
            case "Dragon":
                this.ivRace.setImageResource(R.drawable.dragon);
                break;
            case "Fiend":
                this.ivRace.setImageResource(R.drawable.fiend);
                break;
            case "Fish":
                this.ivRace.setImageResource(R.drawable.fish);
                break;
            case "Insect":
                this.ivRace.setImageResource(R.drawable.insect);
                break;
            case "Plant":
                this.ivRace.setImageResource(R.drawable.plant);
                break;
            case "Psychic":
                this.ivRace.setImageResource(R.drawable.psychic);
                break;
            case "Pyro":
                this.ivRace.setImageResource(R.drawable.pyro);
                break;
            case "Reptile":
                this.ivRace.setImageResource(R.drawable.reptile);
                break;
            case "Rock":
                this.ivRace.setImageResource(R.drawable.rock);
                break;
            case "Sea Serpent":
                this.ivRace.setImageResource(R.drawable.sea_serpent);
                break;
            case "Spellcaster":
                this.ivRace.setImageResource(R.drawable.spellcaster);
                break;
            case "Thunder":
                this.ivRace.setImageResource(R.drawable.thunder);
                break;
            case "Warrior":
                this.ivRace.setImageResource(R.drawable.warrior);
                break;
            case "Winged Beast":
                this.ivRace.setImageResource(R.drawable.winged_beast);
                break;
            case "Normal":
                this.ivRace.setImageResource(R.drawable.normal);
                break;
            case "Field":
                this.ivRace.setImageResource(R.drawable.field);
                break;
            case "Equip":
                this.ivRace.setImageResource(R.drawable.equip);
                break;
            case "Continuous":
                this.ivRace.setImageResource(R.drawable.continuous);
                break;
            case "Quick-Play":
                this.ivRace.setImageResource(R.drawable.quick_play);
                break;
            case "Ritual":
                this.ivRace.setImageResource(R.drawable.ritual);
                break;
            case "Counter":
                this.ivRace.setImageResource(R.drawable.counter);
                break;
        }

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