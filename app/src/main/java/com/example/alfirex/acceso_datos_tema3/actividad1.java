package com.example.alfirex.acceso_datos_tema3;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class actividad1 extends AppCompatActivity {

    public static final String PREFS = "My preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad1);

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Creamos o recuperamos el objeto de preferencias compartidas
                SharedPreferences mySharedPreferences = getSharedPreferences(PREFS, Activity.MODE_PRIVATE);

                // Obtenemos un editor para modificar las preferencias
                SharedPreferences.Editor editor = mySharedPreferences.edit();
                final EditText edNombre = (EditText) findViewById(R.id.etNombre);
                String sNombre = edNombre.getText().toString();

                final EditText edNombreUsuario = (EditText) findViewById(R.id.nombreUsuario);
                String sNombreUsuario = edNombreUsuario.getText().toString();

                final EditText edFecha = (EditText) findViewById(R.id.fecha);
                String sFecha = edFecha.getText().toString();

                final RadioButton rdHombre = (RadioButton) findViewById(R.id.hombre);
                final RadioButton rdmujer = (RadioButton) findViewById(R.id.mujer);
                String sGenero = null;

                if(rdHombre.isChecked()){
                    sGenero = "Hombre";
                }else if(rdmujer.isChecked()){
                    sGenero = "Mujer";
                }

                // Guardamos nuevos valores
                editor.putBoolean("isTrue",true);
                editor.putString("nombre", sNombre);
                editor.putString("nombreUsuario", sNombreUsuario);
                editor.putString("fecha", sFecha);
                editor.putString("sexo", sGenero);

                // Guardamos los cambios
                editor.commit();

            }
        });

        final Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Recuperamos el objeto de preferencias compartidas
                SharedPreferences mySharedPreferences = getSharedPreferences(PREFS, Activity.MODE_PRIVATE);

                // Recuperamos los valores guardados
                boolean isTrue = mySharedPreferences.getBoolean("isTrue", false);
                String sNombre = mySharedPreferences.getString("nombre","");
                String sNombreUsuario = mySharedPreferences.getString("nombreUsuario","");
                String sFecha = mySharedPreferences.getString("fecha","");
                String sSexo = mySharedPreferences.getString("sexo","");

                Toast.makeText(actividad1.this, "He recuperado: Nombre --> " + sNombre + ", Nombre Usuario --> " + sNombreUsuario
                        + ", Fecha--> " + sFecha + ", Sexo--> " +sSexo , Toast.LENGTH_SHORT).show();

            }
        });
    }
}
