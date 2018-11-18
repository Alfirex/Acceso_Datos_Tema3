package com.example.alfirex.acceso_datos_tema3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class HorasAsignatura extends AppCompatActivity {
    ListView lv;
    ArrayList<String> lista;
    ArrayAdapter adaptador;
    private MyDBAdapter dbAdapter;
    private EditText buscadorAsignatura;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horas_asignatura);

        lv = (ListView)findViewById(R.id.listaHoras);

        buscadorAsignatura = (EditText)findViewById(R.id.etBuscador);

        final Button btnBuscarHorasAsignatura = (Button) findViewById(R.id.btnBuscar);
        btnBuscarHorasAsignatura.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String sNombre = buscadorAsignatura.getText().toString();
                Log.d("Prubea","entra");
                Log.d("Prubea","Asignatura:"+ sNombre);
                dbAdapter = new MyDBAdapter(v.getContext());
                dbAdapter.open();
                lista = dbAdapter.devolverHorasAsignatura(sNombre);
                adaptador = new ArrayAdapter(v.getContext(), android.R.layout.simple_list_item_1, lista);
                lv.setAdapter(adaptador);

            }
        });

    }
}
