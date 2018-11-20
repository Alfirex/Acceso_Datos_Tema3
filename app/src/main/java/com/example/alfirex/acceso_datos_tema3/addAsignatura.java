package com.example.alfirex.acceso_datos_tema3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addAsignatura extends AppCompatActivity {
    private MyDBAdapter dbAdapter;
    private EditText etNombre, etHoras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_asignatura);

        etNombre = (EditText)findViewById(R.id.etNombre_Asignatura);
        etHoras = (EditText)findViewById(R.id.etHora);

        final Button button2 = (Button) findViewById(R.id.btnInsertAsignatura);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String sNombre = etNombre.getText().toString();
                int nHoras = Integer.parseInt(etHoras.getText().toString());

                dbAdapter = new MyDBAdapter(v.getContext());
                dbAdapter.open();

                dbAdapter.insertarAsignatura(sNombre,nHoras);


            }
        });
    }
}
