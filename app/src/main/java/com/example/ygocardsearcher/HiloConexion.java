package com.example.ygocardsearcher;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HiloConexion extends Thread {

    public static final int IMAGEN = 1;
    public static final int CARTAS = 2;
    Handler handler;
    Boolean img;
    String urlImg;

    public HiloConexion(Handler handler, boolean img){
        this.handler = handler;
        this.img = img;
    }

    public HiloConexion(Handler handler, boolean img, String urlImg){
        this.handler = handler;
        this.img = img;
        this.urlImg = urlImg;
    }

    @Override
    public void run(){
        try{
            ConexionHTTP conexionHTTP = new ConexionHTTP();
            if(!img){
                byte[] cartasJson = conexionHTTP.obtenerRespuesta("https://db.ygoprodeck.com/api/v7/cardinfo.php?name=Pot%20of%20Greed");
                String s = new String(cartasJson);
                Message msg = new Message();
                msg.arg1 = CARTAS;
                msg.obj = this.parserJson(s);
                this.handler.sendMessage(msg);
            }
            else{
                byte[] img = conexionHTTP.obtenerRespuesta(this.urlImg);
                Message msg = new Message();
                msg.arg1 = IMAGEN;
                msg.obj = img;
                this.handler.sendMessage(msg);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public List<CartaModel> parserJson(String s){
        List<CartaModel> cartas = new ArrayList<CartaModel>();

        try {
            JSONObject jsonRespuesta = new JSONObject(s);
            JSONArray jsonArray = jsonRespuesta.getJSONArray("data");
            CartaModel carta = new CartaModel();

            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                carta.setId(jsonObject.getInt("id"));
                carta.setName(jsonObject.getString("name"));
                carta.setType(jsonObject.getString("type"));
                carta.setDesc(jsonObject.getString("desc"));
                carta.setRace(jsonObject.getString("race"));

                if(!"Spell Card".equals(carta.getType()) && !"Trap Card".equals(carta.getType())){
                    carta.setAtk(String.valueOf(jsonObject.getInt("atk")));
                    if("Link Monster".equals(carta.getType())){
                        carta.setDef(String.valueOf(jsonObject.getInt("linkval")));
                        carta.setLevel(null);
                    }
                    else{
                        carta.setDef(String.valueOf(jsonObject.getInt("def")));
                        carta.setLevel(String.valueOf(jsonObject.getInt("level")));
                    }

                    carta.setAttribute(jsonObject.getString("attribute"));

                    if(jsonObject.has("archetype")){
                        carta.setArchetype(jsonObject.getString("archetype"));
                    }
                    else{
                        carta.setArchetype(null);
                    }
                }
                else{
                    carta.setAtk(null);
                    carta.setDef(null);
                    carta.setLevel(null);
                    carta.setAttribute(null);
                }

                JSONArray jsonArrayImg = jsonObject.getJSONArray("card_images");
                JSONObject jsonImgUrl = jsonArrayImg.getJSONObject(i);
                carta.setImage_url(jsonImgUrl.getString("image_url"));
                Log.d("Img_url", carta.getImage_url());

                cartas.add(carta);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cartas;
    }
}
