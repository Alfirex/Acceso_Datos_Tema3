package com.example.alfirex.acceso_datos_tema3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class addAlumno extends AppCompatActivity {

    private static final String TAG = "tag";
    private MyDBAdapter dbAdapter;
    private EditText etNombre, etEdad, etCiclo, etCurso,  etNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alumno);

        etNombre = (EditText)findViewById(R.id.nombreAlumno);
        etEdad = (EditText)findViewById(R.id.edadAlumno);
        etCiclo = (EditText) findViewById(R.id.cicloAlumno);
        etCurso = (EditText)findViewById(R.id.cursoAlumno);
        etNota = (EditText)findViewById(R.id.notaAlumno);

        final Button button2 = (Button) findViewById(R.id.insertarAlumno);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String sNombre = etNombre.getText().toString();
                String sEdad = etEdad.getText().toString();
                String sCiclo = etCiclo.getText().toString();
                String sCurso = etCurso.getText().toString();
                String sNota = etNota.getText().toString();


                dbAdapter = new MyDBAdapter(v.getContext());
                dbAdapter.open();

                dbAdapter.insertarAlumnos(sNombre,sEdad,sCiclo,sCurso,sNota);


            }
        });
    }


}
