package com.example.sala305_pc_03.pruebacountryinfoapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class pais {

    private String nombre;
    private String alpha2;
    private String alpha3;
    private String bandera;


    public String getNombre() {
        return nombre;
    }

    public String getAlpha2() {
        return alpha2;
    }

    public String getAlpha3() {
        return alpha3;
    }
    public String getBandera() {
        return bandera;
    }

    //Constructor

    public pais(JSONObject a) throws JSONException {
        nombre =  a.getString("name").toString();
        alpha2 =  a.getString("alpha2_code").toString();
        alpha3 = a.getString("alpha3_code").toString();
        bandera ="http://www.geognos.com/api/en/countries/flag/"+a.getString("alpha2_code").toString()+".png";
    }



    public static ArrayList<pais> JsonObjectsBuild(JSONArray datos) throws JSONException {

        ArrayList<pais> paises = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            paises.add(new pais(datos.getJSONObject(i)));
        }
        return paises;
    }
}
