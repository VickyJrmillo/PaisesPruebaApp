package com.example.sala305_pc_03.pruebacountryinfoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sala305_pc_03.pruebacountryinfoapp.ClasesWebService.Asynchtask;
import com.example.sala305_pc_03.pruebacountryinfoapp.ClasesWebService.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Activity_detalle extends AppCompatActivity implements Asynchtask{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

         Bundle b = this.getIntent().getExtras();
         String alpha = b.getString("ALPHA");

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("http://www.geognos.com/api/en/countries/info/" + alpha +".json", datos,
                Activity_detalle.this, Activity_detalle.this);
        ws.execute("");


    }

    @Override
    public void processFinish(String result) throws JSONException {



        JSONObject objdatos = new JSONObject(result).getJSONObject("Results");


        TextView nombre = (TextView)findViewById(R.id.txtNombre1);
        nombre.setText(objdatos.getString("Name"));


        //TextView codigo = (TextView)findViewById(R.id.txtCodigo);
        //codigo.setText(objdatos.getString("StatusCode"));

        TextView informacion = (TextView)findViewById(R.id.txtCountryInfo);
        informacion.setText(objdatos.getString("CountryInfo"));

        //Datos Capital
        JSONObject ObjCapital = objdatos.getJSONObject("Capital");
        TextView capital = (TextView)findViewById(R.id.txtCapital1);
        capital.setText(ObjCapital.getString("Name"));



    }
}
