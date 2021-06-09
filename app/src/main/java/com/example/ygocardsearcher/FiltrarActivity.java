package com.example.ygocardsearcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.EditText;

public class FiltrarActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    Spinner spType;
    Spinner spRace;
    Spinner spAttribute;
    ArrayAdapter<CharSequence> adapterType;
    ArrayAdapter<CharSequence> adapterRace;
    ArrayAdapter<CharSequence> adapterAttribute;
    SeekBar sbLevelRank;
    TextView tvLevelRank;
    String type;
    String race;
    String attribute;
    String levelRank;
    String buscar;
    String atk;
    String def;
    String link;
    String scale;
    EditText etBuscar;
    EditText etAtk;
    EditText etDef;
    EditText etLink;
    EditText etScale;
    static String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtrar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Filtrar cartas");
        actionBar.setDisplayHomeAsUpEnabled(true);

        Button btnReset = super.findViewById(R.id.btnReset);
        btnReset.setOnClickListener(this);
        Button btnFilter = super.findViewById(R.id.btnFilter);
        btnFilter.setOnClickListener(this);

        this.etBuscar = super.findViewById(R.id.etBuscar);
        this.etAtk = super.findViewById(R.id.etAtk);
        this.etDef = super.findViewById(R.id.etDef);
        this.etLink = super.findViewById(R.id.etLink);
        this.etScale = super.findViewById(R.id.etScale);

        this.spType = super.findViewById(R.id.spType);
        this.adapterType = ArrayAdapter.createFromResource(this,
                R.array.type, android.R.layout.simple_spinner_item);
        this.adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spType.setAdapter(this.adapterType);
        this.spType.setOnItemSelectedListener(this);
        this.type = "";

        this.spRace = super.findViewById(R.id.spRace);
        this.adapterRace = ArrayAdapter.createFromResource(this,
                R.array.race, android.R.layout.simple_spinner_item);
        this.adapterRace.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spRace.setAdapter(this.adapterRace);
        this.spRace.setOnItemSelectedListener(this);
        this.race = "";

        this.spAttribute = super.findViewById(R.id.spAttribute);
        this.adapterAttribute = ArrayAdapter.createFromResource(this,
                R.array.attribute, android.R.layout.simple_spinner_item);
        this.adapterAttribute.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spAttribute.setAdapter(this.adapterAttribute);
        this.spAttribute.setOnItemSelectedListener(this);
        this.attribute = "";

        this.tvLevelRank = super.findViewById(R.id.tvLevelRank);
        this.sbLevelRank = super.findViewById(R.id.sbLevelRank);
        this.levelRank = "0";
        sbLevelRank.setProgress(0);
        sbLevelRank.setMax(12);
        sbLevelRank.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvLevelRank.setText(String.valueOf(progress));
                levelRank = String.valueOf(progress);
            }
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == android.R.id.home){
            super.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch(adapterView.getId()){
            case R.id.spType:
                this.type = adapterView.getItemAtPosition(i).toString();
                break;
            case R.id.spRace:
                this.race = adapterView.getItemAtPosition(i).toString();
                break;
            case R.id.spAttribute:
                this.attribute = adapterView.getItemAtPosition(i).toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        query = "https://db.ygoprodeck.com/api/v7/cardinfo.php?";
        switch(view.getId()){
            case R.id.btnReset:
                super.finish();
                break;
            case R.id.btnFilter:
                this.buscar = this.etBuscar.getText().toString();
                this.atk = this.etAtk.getText().toString();
                this.def = this.etDef.getText().toString();
                this.link = this.etLink.getText().toString();
                this.scale = this.etScale.getText().toString();
                if("Seleccionar tipo".equals(this.type)){
                    this.type = "";
                }
                if("Seleccionar raza".equals(this.race)){
                    this.race = "";
                }
                if("Seleccionar atributo".equals(this.attribute)){
                    this.attribute = "";
                }
                if(!"".equals(this.buscar)){
                    query = query.concat("&fname="+this.buscar)
                            .concat("&desc="+this.buscar);
                }
                if(!"".equals(this.type)){
                    query = query.concat("&type="+this.type);
                }
                if(!"".equals(this.race)){
                    query = query.concat("&race="+this.race);
                }
                if(!"".equals(this.attribute)){
                    query = query.concat("&attribute="+this.attribute);
                }
                if(!"0".equals(this.levelRank)){
                    query = query.concat("&level="+this.levelRank);
                }
                if(!"".equals(this.atk)){
                    query = query.concat("&atk="+this.atk);
                }
                if(!"".equals(this.def)){
                    query = query.concat("&def="+this.def);
                }
                if(!"".equals(this.link)){
                    query = query.concat("&link="+this.link);
                }
                if(!"".equals(this.scale)){
                    query = query.concat("&scale="+this.scale);
                }
                break;
        }
        Log.d("Query", query);
        super.finish();
    }
}