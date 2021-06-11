package com.example.ygocardsearcher;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConexionHTTP {

    Activity a;
    static RecyclerView rv;
    static ProgressBar pb;

    public ConexionHTTP(Activity a){
        this.a = a;
    }

    public static void setRecyclerView(RecyclerView recyclerView){
        rv = recyclerView;
    }

    public static void setProgressBar(ProgressBar progressBar){
        pb = progressBar;
    }

    public byte[] obtenerRespuesta(String urlString){
        try {
            URL url = new URL(urlString);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            Log.d("Conexion", "Estableciendo conexion...");
            urlConnection.connect();
            int respuesta = urlConnection.getResponseCode();
            Log.d("Conexion", "Respuesta del servidor: " + respuesta);

            if(respuesta == 200){
                InputStream is = urlConnection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int cantidad = 0;
                while((cantidad = is.read(buffer)) != -1){
                    baos.write(buffer, 0, cantidad);
                }
                is.close();
                return baos.toByteArray();
            }
            else if(respuesta == 400){
                this.cambiarVisibilidad(this.a);
                this.mostrarMensaje(this.a, "No se encontraron cartas con ese filtro.");
                throw new RuntimeException("No se encontraron cartas con ese filtro.");
            }
            else{
                this.cambiarVisibilidad(this.a);
                this.mostrarMensaje(this.a, "Error en la conexion con el servidor.");
                throw new RuntimeException("Error en la conexion con el servidor: " + respuesta);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void mostrarMensaje(final Activity a, final String mensaje){
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(a, mensaje, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void cambiarVisibilidad(final Activity a){
        a.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pb.setVisibility(View.GONE);
                rv.setVisibility(View.VISIBLE);
            }
        });
    }
}
