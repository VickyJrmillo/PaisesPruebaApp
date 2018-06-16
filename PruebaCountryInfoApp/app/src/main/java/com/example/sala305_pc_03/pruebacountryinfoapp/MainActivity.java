package com.example.sala305_pc_03.pruebacountryinfoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.sala305_pc_03.pruebacountryinfoapp.ClasesWebService.Asynchtask;
import com.example.sala305_pc_03.pruebacountryinfoapp.ClasesWebService.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //----------------------------------------CONSUMIR WEB SERVICE------------------------------------------------------------------


        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("http://services.groupkt.com/country/get/all", datos, MainActivity.this, MainActivity.this);
        ws.execute("");

      //----------------------------------------FIN CONSUMIR WEB SERVICE---------------------------------------------------------------



       //EVento click en el listView----------------------------------------------------------------------------------------------//


        ListView lstPais = (ListView)findViewById(R.id.lstLista); //enlaza el list view

        //lISTENER PARA EL EVENTO CLICK

        lstPais.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> a, View v, int position, long id) {


                //recibe dos parametros la clase actual y a donde se quiere mostrar la actividad
                Intent intent = new Intent(MainActivity.this, Activity_detalle.class);

                //saca la informacion del titulo que dio click
                Bundle b = new Bundle();
                b.putString("NOMBRE",((pais)a.getItemAtPosition(position)).getNombre());

                b.putString("ALPHA",((pais)a.getItemAtPosition(position)).getAlpha2());

                intent.putExtras(b);
//

                startActivity(intent);

            }
        });


        // FIN EVento click en el listView----------------------------------------------------------------------------------------------//



    }
    //---------------------------------------Obtener datos del Web Service con el ProcessFinish-----------------------------------------------------------

    @Override
    public void processFinish(String result) throws JSONException {

        //JSONObject objdatos = new JSONObject(result);
        //JSONArray objarray = objdatos.getJSONArray("RestResponse");


        JSONObject objdatos1 = new JSONObject(result).getJSONObject("RestResponse");
        JSONArray objarray = objdatos1.getJSONArray("result");


        ArrayList<pais> paises = pais.JsonObjectsBuild(objarray);
        adaptador_pais adaptadorPais = new adaptador_pais(this,paises);

        ListView lstOpciones = (ListView)findViewById(R.id.lstLista);
        lstOpciones.setAdapter(adaptadorPais);
    }
}
