package com.example.alfirex.acceso_datos_tema3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class actividad3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad3);

        final Button btnAllStudents = (Button) findViewById(R.id.btnAllStudents);
        btnAllStudents.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intento = new Intent(v.getContext(),todosLosEstudiantes.class);
                startActivity(intento);

            }
        });

        final Button btnAllStudentsCicle = (Button) findViewById(R.id.btnStudentsCicle);
        btnAllStudentsCicle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intento = new Intent(v.getContext(),todosLosEstudiantesCiclos.class);
                startActivity(intento);

            }
        });

        final Button btnAllStudentsCourse = (Button) findViewById(R.id.btnStudentsCourse);
        btnAllStudentsCourse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intento = new Intent(v.getContext(),todosLosEstudiantesCursos.class);
                startActivity(intento);

            }
        });

        final Button btnAllTeacher = (Button) findViewById(R.id.btnAllTeachers);
        btnAllTeacher.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intento = new Intent(v.getContext(),todosLosProfesores.class);
                startActivity(intento);

            }
        });
        final Button btnAllStudentsTeachers = (Button) findViewById(R.id.btnAllTeachersStudents);
        btnAllStudentsTeachers.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intento = new Intent(v.getContext(),alumno_edad.class);
                startActivity(intento);

            }
        });

    }

}
