package com.example.alfirex.acceso_datos_tema3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addProfesor extends AppCompatActivity {

    private static final String TAG = "tag";
    private MyDBAdapter dbAdapter;
    private EditText etNombre, etEdad, etCiclo, etTutot, etDespacho;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profesor);

        etNombre = (EditText)findViewById(R.id.nombreProfesor);
        etEdad = (EditText)findViewById(R.id.edadProfesor);
        etCiclo = (EditText) findViewById(R.id.cicloProfesor);
        etTutot = (EditText)findViewById(R.id.cursoProfesor);
        etDespacho = (EditText)findViewById(R.id.despachoProfesor);

        final Button button2 = (Button) findViewById(R.id.insertarAlumno);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String sNombre = etNombre.getText().toString();
                String sEdad = etEdad.getText().toString();
                String sCiclo = etCiclo.getText().toString();
                String sTutot = etTutot.getText().toString();
                String sDespacho = etDespacho.getText().toString();


                dbAdapter = new MyDBAdapter(v.getContext());
                dbAdapter.open();

                dbAdapter.insertarProfesor(sNombre,sEdad,sCiclo,sTutot,sDespacho);


            }
        });
    }
}
