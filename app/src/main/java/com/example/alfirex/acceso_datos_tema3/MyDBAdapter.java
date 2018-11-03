package com.example.alfirex.acceso_datos_tema3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDBAdapter {

    // Definiciones y constantes
    private static final String DATABASE_NAME = "dbescuela.db";
    private static final String DATABASE_TABLE_PROFESORES = "profesores";
    private static final String DATABASE_TABLE_ALUMNOS = "alumnos";
    private static final int DATABASE_VERSION = 1;
    

    private static final String NOMBRE = "nombre";
    private static final String EDAD = "edad";
    private static final String CICLO = "ciclo";
    private static final String TUTOR = "tutor";
    private static final String DESPACHO = "despacho";
    private static final String CURSO = "curso";
    private static final String NOTA = "nota";

    private static final String DATABASE_CREATE_PROFESORES = "CREATE TABLE "+DATABASE_TABLE_PROFESORES+" (_id integer primary key autoincrement, nombre text, edad text, ciclo text, tutor text, despacho text);";
    private static final String DATABASE_DROP_PROFESORES = "DROP TABLE IF EXISTS " +DATABASE_CREATE_PROFESORES + ";";

    private static final String DATABASE_CREATE_ALUMNOS = "CREATE TABLE "+DATABASE_TABLE_ALUMNOS+" (_id integer primary key autoincrement, nombre text, edad text, ciclo text, curso text, nota text);";
    private static final String DATABASE_DROP_ALUMNOS = "DROP TABLE IF EXISTS " + DATABASE_CREATE_ALUMNOS+ ";";



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


    public void insertarProfesor(String tNombre, String tEdad, String tCiclo, String tTutot, String tDespacho){
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put(NOMBRE,tNombre);
        newValues.put(EDAD,tEdad);
        newValues.put(CICLO,tCiclo);
        newValues.put(TUTOR,tTutot);
        newValues.put(DESPACHO,tDespacho);
        db.insert(DATABASE_TABLE_PROFESORES,null,newValues);
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

    private static class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context,name,factory,version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE_PROFESORES);
            db.execSQL(DATABASE_CREATE_ALUMNOS);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DATABASE_DROP_PROFESORES);
            db.execSQL(DATABASE_DROP_ALUMNOS);
            onCreate(db);
        }

    }
}