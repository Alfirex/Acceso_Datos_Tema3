package com.example.alfirex.acceso_datos_tema3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MyDBAdapter {

    // Definiciones y constantes
    private static final String DATABASE_NAME = "dbescuela.db";
    private static final String DATABASE_TABLE_PROFESORES = "profesores";
    private static final String DATABASE_TABLE_ALUMNOS = "alumnos";
    private static final String DATABASE_TABLE_ASIGNATURA = "asignatura";//EXM A
    private static final int DATABASE_VERSION = 1;

    

    private static final String NOMBRE = "nombre";
    private static final String EDAD = "edad";
    private static final String CICLO = "ciclo";
    private static final String TUTOR = "tutor";
    private static final String DESPACHO = "despacho";
    private static final String CURSO = "curso";
    private static final String NOTA = "nota";

    //EXAMEN A
    private static final String NOMBRE_ASIGNATURA = "nombre";
    private static final String HORAS = "horas";

    private static final String DATABASE_CREATE_PROFESORES = "CREATE TABLE "+DATABASE_TABLE_PROFESORES+" (_id integer primary key autoincrement, nombre text, edad text, ciclo text, tutor text, despacho text);";
    private static final String DATABASE_DROP_PROFESORES = "DROP TABLE IF EXISTS " +DATABASE_CREATE_PROFESORES + ";";

    private static final String DATABASE_CREATE_ALUMNOS = "CREATE TABLE "+DATABASE_TABLE_ALUMNOS+" (_id integer primary key autoincrement, nombre text, edad text, ciclo text, curso text, nota text);";
    private static final String DATABASE_DROP_ALUMNOS = "DROP TABLE IF EXISTS " + DATABASE_CREATE_ALUMNOS+ ";";

    private static final String DATABASE_CREATE_ASIGNATURA = "CREATE TABLE "+DATABASE_TABLE_ASIGNATURA+" (_id integer primary key autoincrement, nombre text, horas int);";
    private static final String DATABASE_DROP_ASIGNATURA = "DROP TABLE IF EXISTS " + DATABASE_CREATE_ASIGNATURA + ";";



    // Contexto de la aplicación que usa la base de datos
    private final Context context;
    // Clase SQLiteOpenHelper para crear/actualizar la base de datos
    private MyDbHelper dbHelper;
    // Instancia de la base de datos
    private SQLiteDatabase db;

    public MyDBAdapter (Context c){
        context = c;
        dbHelper = new MyDbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        //OJO open();
    }

    public void open(){

        try{
            db = dbHelper.getWritableDatabase();
        }catch(SQLiteException e){
            db = dbHelper.getReadableDatabase();
        }

    }


    public String insertarProfesor(String tNombre, String tEdad, String tCiclo, String tTutot, String tDespacho){
        Cursor cursor = db.query(DATABASE_TABLE_PROFESORES,null,null,null,null,null, null);
        String variable="";
        if (cursor != null && cursor.moveToFirst()){
            Log.d("Prueba","Entra");

            do{
                if(cursor.getString(1).compareTo(tNombre) == 0 ){
                    Log.d("Prueba","Entra Compa");
                    //View v=null;
                    //Toast.makeText(v.getContext(),"Ya existe un registro con ese Nombre",Toast.LENGTH_LONG).show();
                     variable = "Ya existe un registro con ese Nombre";
                    return variable;
                }


            }while (cursor.moveToNext());

        }
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put(NOMBRE,tNombre);
        newValues.put(EDAD,tEdad);
        newValues.put(CICLO,tCiclo);
        newValues.put(TUTOR,tTutot);
        newValues.put(DESPACHO,tDespacho);
        db.insert(DATABASE_TABLE_PROFESORES,null,newValues);
        return variable;
    }
    public void insertarAlumnos(String tNombre, String tEdad, String tCiclo, String tCurso, String tNota){
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put(NOMBRE,tNombre);
        newValues.put(EDAD,tEdad);
        newValues.put(CICLO,tCiclo);
        newValues.put(CURSO,tCurso);
        newValues.put(NOTA,tNota);
        db.insert(DATABASE_TABLE_ALUMNOS,null,newValues);
    }

    public void insertarAsignatura(String tNombre, int nHoras){
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put(NOMBRE_ASIGNATURA,tNombre);
        newValues.put(HORAS,nHoras);

        db.insert(DATABASE_TABLE_ASIGNATURA,null,newValues);
    }

    public ArrayList<String> llenar_lv_estudiantes(){
        ArrayList<String> lista = new ArrayList<>();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_TABLE_ALUMNOS,null,null,null,null,null, null);
        if (cursor != null && cursor.moveToFirst()){
            do{
                lista.add("Nombre: "+cursor.getString(1)+" Edad "+cursor.getString(2) + " Ciclo " +cursor.getString(3)+ " Curso " +cursor.getString(4));
            }while (cursor.moveToNext());
        }
        return lista;

    }

    public ArrayList<String> llenar_lv_estudiantes_ciclo(){
        ArrayList<String> lista = new ArrayList<>();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_TABLE_ALUMNOS,null,null,null,null,null, CICLO);
        if (cursor != null && cursor.moveToFirst()){
            do{
                lista.add("Nombre: "+cursor.getString(1)+" Edad "+cursor.getString(2) + " Ciclo " +cursor.getString(3)+ " Curso " +cursor.getString(4));
            }while (cursor.moveToNext());
        }
        return lista;

    }

    public ArrayList<String> llenar_lv_estudiantes_curso(){
        ArrayList<String> lista = new ArrayList<>();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_TABLE_ALUMNOS,null,null,null,null,null, CURSO);
        if (cursor != null && cursor.moveToFirst()){
            do{
                lista.add("Nombre: "+cursor.getString(1)+" Edad "+cursor.getString(2) + " Ciclo " +cursor.getString(3)+ " Curso " +cursor.getString(4));
            }while (cursor.moveToNext());
        }
        return lista;

    }
    public ArrayList<String> llenar_lv_profesores_estudiantes(){
        ArrayList<String> lista = new ArrayList<>();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor1 = db.query(DATABASE_TABLE_ALUMNOS,null,null,null,null,null, null);
        Cursor cursor2 = db.query(DATABASE_TABLE_PROFESORES,null,null,null,null,null, null);
        if (cursor1 != null && cursor1.moveToFirst()){
            do{

                lista.add("Nombre: "+cursor1.getString(1)+" Edad "+cursor1.getString(2) + " Ciclo " +cursor1.getString(3)+ " Curso " +cursor1.getString(4));
            }while (cursor1.moveToNext());
        }
        if (cursor2 != null && cursor2.moveToFirst()){
            do{

                lista.add("Nombre: "+cursor2.getString(1)+" Edad "+cursor2.getString(2) + " Ciclo " +cursor2.getString(3)+ " Tutor " +cursor2.getString(4));
            }while (cursor2.moveToNext());
        }
        return lista;

    }
    public ArrayList<String> llenar_lv_profesores(){
        ArrayList<String> lista = new ArrayList<>();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_TABLE_PROFESORES,null,null,null,null,null, null);
        if (cursor != null && cursor.moveToFirst()){
            do{

                lista.add("Nombre: "+cursor.getString(1)+" Edad "+cursor.getString(2) + " Ciclo " +cursor.getString(3)+ " Tutor " +cursor.getString(4));
            }while (cursor.moveToNext());
        }
        return lista;

    }

    // ESAMEN A ejercicio 1
    public ArrayList<String> devolverHorasAsignatura(String asignatura){
        ArrayList<String> lista = new ArrayList<>();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_TABLE_ASIGNATURA,null, "nombre=" +"'"+ asignatura+"'",null,null,null, null);
        if (cursor != null && cursor.moveToFirst()){
            do{
                lista.add( "Las Horas de la asignatura es "+cursor.getString(2) );
            }while (cursor.moveToNext());
        }
        return lista;

    }
    // EXAMEN A Ejercicio 2
    public ArrayList<String> recuperarDAMDAW(){
        ArrayList<String> lista = new ArrayList<>();
        //Recuperamos en un cursor la consulta realizada
        Cursor cursor = db.query(DATABASE_TABLE_ALUMNOS,null,"ciclo='daw' OR ciclo='dam'",null,null,null, "curso ASC");
        if (cursor != null && cursor.moveToFirst()){
            do{
                lista.add("Nombre: "+cursor.getString(1)+" Edad "+cursor.getString(2) + " Ciclo " +cursor.getString(3)+ " Curso " +cursor.getString(4));
            }while (cursor.moveToNext());
        }
        return lista;

    }


    private static class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context,name,factory,version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE_PROFESORES);
            db.execSQL(DATABASE_CREATE_ALUMNOS);
            db.execSQL(DATABASE_CREATE_ASIGNATURA);//EXAMEN A
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DATABASE_DROP_PROFESORES);
            db.execSQL(DATABASE_DROP_ALUMNOS);
            db.execSQL(DATABASE_DROP_ASIGNATURA);//Examen A
            onCreate(db);
        }

    }
}