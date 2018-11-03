package com.example.alfirex.acceso_datos_tema3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class pantallaPincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_pincipal);

        final Button btnActividad1 = (Button) findViewById(R.id.activiad1);
        btnActividad1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intento = new Intent(v.getContext(),actividad1.class);
                startActivity(intento);

            }
        });
        final Button btnActividad2 = (Button) findViewById(R.id.actividad2);
        btnActividad2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intento = new Intent(v.getContext(),actividad2.class);
                startActivity(intento);

            }
        });

        final Button btnActividad3 = (Button) findViewById(R.id.actividad3);
        btnActividad3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intento = new Intent(v.getContext(),actividad3.class);
                startActivity(intento);

            }
        });
    }
}
