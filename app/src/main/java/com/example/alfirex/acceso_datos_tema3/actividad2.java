package com.example.alfirex.acceso_datos_tema3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class actividad2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);

        final Button btnAddProfesor = (Button) findViewById(R.id.btnAñadirProfesor);
        btnAddProfesor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intento = new Intent(v.getContext(),addProfesor.class);
                startActivity(intento);

            }
        });
        final Button btnAddAlumno = (Button) findViewById(R.id.btnAñadirAlumno);
        btnAddAlumno.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intento = new Intent(v.getContext(),addAlumno.class);
                startActivity(intento);

            }
        });
    }
}
