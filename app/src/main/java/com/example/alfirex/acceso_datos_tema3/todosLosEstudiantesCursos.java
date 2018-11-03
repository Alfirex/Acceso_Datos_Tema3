package com.example.alfirex.acceso_datos_tema3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;

public class todosLosEstudiantesCursos extends AppCompatActivity {
    ListView lv;
    ArrayList<String> lista;
    ArrayAdapter adaptador;
    private MyDBAdapter dbAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos_los_estudiantes_cursos);

        lv = (ListView)findViewById(R.id.lista);

        dbAdapter = new MyDBAdapter(this);
        dbAdapter.open();
        lista = dbAdapter.llenar_lv_estudiantes_curso();
        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        lv.setAdapter(adaptador);
    }
}
