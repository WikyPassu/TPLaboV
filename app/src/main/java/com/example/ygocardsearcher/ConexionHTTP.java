package com.example.ygocardsearcher;

import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConexionHTTP {

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
                throw new RuntimeException("No se encontraron cartas con ese filtro.");
            }
            else{
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
}
