package com.example.giiffinal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.giiffinal.entidades.MostrarDatos_SI;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 12;
    private static final String DATABASE_NOMBRE = "GIIF.db";
    public static final String TABLE_USUARIOS = "t_usuarios";
    public static final String TABLE_GASTOS_EU = "t_gastos_eu";
    public static final String TABLE_GASTOS = "t_gastos";
    public static final String TABLE_FICHAR = "t_fichar";
    public static final String TABLE_PROYECTOS = "t_proyectos";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE " + TABLE_USUARIOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "dni varchar(9) UNIQUE," +
                "username varchar(50)," +
                "contraseña varchar(255)," +
                "nombre varchar(50)," +
                "apellido varchar(50))");

        MyDB.execSQL("CREATE TABLE " + TABLE_PROYECTOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "username varchar(50) ,\n" +
                "Nombre_proyecto varchar(50),\n" +
                "estado boolean,\n" +
                "constraint FK3_id foreign key (id) references t_usuarios(id))"
        );

        MyDB.execSQL("CREATE TABLE " + TABLE_FICHAR + "(" +
                "intIdFichar INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "id INTEGER(10),\n" +
                "intIdUser INTEGER(10),\n" +
                "intIdProyecto int(10),\n" +
                "Nombre varchar(50),\n" +
                "estado varchar(10), \n" +
                "fechainicio datetime,\n" +
                "fechafinal datetime,\n" +
                "horainicio datetime ,\n" +
                "horafinal datetime,\n" +
                "horasDia time,\n" +
                "constraint FK2_id foreign key (id) references t_usuarios(id))"
        );

        MyDB.execSQL("CREATE TABLE " + TABLE_GASTOS_EU + "(" +
                "id_gastos4 INTEGER PRIMARY KEY AUTOINCREMENT," +
                "dni varchar(9)," +
                "fecha_ini date," +
                "fecha_fin date," +
                "pais varchar(50)," +
                "ciudad varchar(50)," +
                "gasto_total decimal(6.2)," +
                "FOREIGN KEY (dni) REFERENCES " + TABLE_USUARIOS + "(dni))");

        MyDB.execSQL("CREATE TABLE " + TABLE_GASTOS + "( " +
                "id_gastos INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "dni varchar(9)," +
                "fecha_ini date, " +
                "fecha_fin date," +
                "transporte varchar(50)," +
                "distancia decimal(7,2), " +
                "peaje decimal(5,2)," +
                "parking decimal(5,2)," +
                "FOREIGN KEY (dni) " +
                "REFERENCES " + TABLE_USUARIOS + " (dni))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        if(oldVersion < 1000) {
            MyDB.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
            onCreate(MyDB);
            MyDB.execSQL("DROP TABLE IF EXISTS " + TABLE_GASTOS_EU);
            onCreate(MyDB);
            MyDB.execSQL("DROP TABLE IF EXISTS " + TABLE_GASTOS);
            onCreate(MyDB);
            MyDB.execSQL("DROP TABLE IF EXISTS " + TABLE_FICHAR);
            onCreate(MyDB);
            MyDB.execSQL("DROP TABLE IF EXISTS " + TABLE_PROYECTOS);
            onCreate(MyDB);
        }
    }

    public  boolean insertData4 ( String finicio, String hinicio, String nombre_fichar){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fechainicio", finicio);
        contentValues.put("horainicio", hinicio);
        contentValues.put("nombre", nombre_fichar);

        long result = MyDB.insert("t_fichar", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }




    public  boolean insertData5 ( String ffinal, String hfinal, String nombre_fichar){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fechafinal", ffinal);
        contentValues.put("horafinal", hfinal);
        contentValues.put("nombre", nombre_fichar);
        long result = MyDB.insert("t_fichar", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }
    public  boolean insertData3(String DNI, String fecha_ini, String fecha_fin, String pais,
                                String ciudad, String gasto_total){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DNI", DNI);
        contentValues.put("fecha_ini", fecha_ini);
        contentValues.put("fecha_fin", fecha_fin);
        contentValues.put("pais", pais);
        contentValues.put("ciudad", ciudad);
        contentValues.put("gasto_total", gasto_total);
        long result = MyDB.insert("t_gastos_eu", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    public  boolean insertData6 ( String Nombre_proyecto, String estado, String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Nombre_proyecto", Nombre_proyecto);
        contentValues.put("estado", estado);
        contentValues.put("username", username);
        long result = MyDB.insert("t_proyectos", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }
    public boolean cerrarpro (
            String estado, String Nombre_proyecto, String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("estado", estado);
        contentValues.put("Nombre_proyecto", Nombre_proyecto);
        contentValues.put("username", username);
        long result = MyDB.update("t_proyectos", contentValues, "Nombre_proyecto = ? and estado = 'activo'", new String[]{Nombre_proyecto});
        if (result == -1) return false;
        else
            return true;
    }
    public  boolean insertData7 ( String Nombre_proyecto, String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("Nombre_proyecto", Nombre_proyecto);
        long result = MyDB.insert("t_proyectos", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean insertData2(String inicio, String fin, String transporte, String diatancia, String peaje, String parking, String dni) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DNI", dni);
        contentValues.put("fecha_ini", inicio);
        contentValues.put("fecha_fin", fin);
        contentValues.put("transporte", transporte);
        contentValues.put("distancia", diatancia);
        contentValues.put("peaje", peaje);
        contentValues.put("parking", parking);
        long result = MyDB.insert("t_gastos", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    public List<MostrarDatos_SI> DatosSI(){
        SQLiteDatabase bd=getReadableDatabase();
        Cursor cursor=bd.rawQuery("select * FROM t_gastos",null);
        List<MostrarDatos_SI> datos=new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                datos.add(new MostrarDatos_SI(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6)));
            } while (cursor.moveToNext());
        }
        return datos;
    }

}
