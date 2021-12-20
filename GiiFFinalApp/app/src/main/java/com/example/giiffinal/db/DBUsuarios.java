package com.example.giiffinal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Base64;

import androidx.annotation.Nullable;

import com.example.giiffinal.Editar;
import com.example.giiffinal.ThirdFragment;
import com.example.giiffinal.entidades.Usuarios;

import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class DBUsuarios extends DBHelper{

    Context context;

    public DBUsuarios(@Nullable  Context context) {
        super(context);
        this.context = context;
    }


    public long insertarContactos(String dni, String username, String contraseña, String nombre, String apellido){

        long id = 0;

        try {
            DBHelper dbHelper = new DBHelper(context);
            SQLiteDatabase MyDB = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("dni", dni);
            values.put("username", username);
            values.put("contraseña", contraseña);
            values.put("nombre", nombre);
            values.put("apellido", apellido);

            id = MyDB.insert(TABLE_USUARIOS, null, values);

        } catch (Exception ex) {
            ex.toString();
        }
        return id;
    }

    public ArrayList<Usuarios> mostrarUsuarios() {

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase MyDB = dbHelper.getWritableDatabase();

        ArrayList<Usuarios> listaUsuarios = new ArrayList<>();

        Usuarios usuario = null;
        Cursor cursorUsuarios = null;

        cursorUsuarios = MyDB.rawQuery(" SELECT * FROM " + TABLE_USUARIOS, null);

        if (cursorUsuarios.moveToFirst()) {
            do {
                usuario = new Usuarios();
                usuario.setId(cursorUsuarios.getInt(0));
                usuario.setDni(cursorUsuarios.getString(1));
                usuario.setUsername(cursorUsuarios.getString(2));
                usuario.setNombre(cursorUsuarios.getString(4));
                usuario.setApellido(cursorUsuarios.getString(5));
                listaUsuarios.add(usuario);
            } while (cursorUsuarios.moveToNext());
        }

        cursorUsuarios.close();

        return listaUsuarios;
    }

    public Usuarios verUsuarios(int id){

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase MyDB = dbHelper.getWritableDatabase();

        Usuarios usuario = null;
        Cursor cursorUsuarios = null;

        cursorUsuarios = MyDB.rawQuery(" SELECT * FROM " + TABLE_USUARIOS + " WHERE id = " + id + " LIMIT 1 ", null);

        if (cursorUsuarios.moveToFirst()) {
            usuario = new Usuarios();
            usuario.setId(cursorUsuarios.getInt(0));
            usuario.setDni(cursorUsuarios.getString(1));
            usuario.setUsername(cursorUsuarios.getString(2));
            usuario.setNombre(cursorUsuarios.getString(4));
            usuario.setApellido(cursorUsuarios.getString(5));
        }

        cursorUsuarios.close();

        return usuario;
    }

    public boolean editarUsuarios(int id, String username, String nombre, String apellido) {

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase MyDB = dbHelper.getWritableDatabase();

        try {
            MyDB.execSQL(" UPDATE " + TABLE_USUARIOS + " SET username = '" + username + "', nombre ='" + nombre + "', apellido = '" + apellido + "' WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            MyDB.close();
        }
        return correcto;
    }

    public boolean eliminarUsuario(int id){

        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase MyDB = dbHelper.getWritableDatabase();

        try {
            MyDB.execSQL(" DELETE FROM " + TABLE_USUARIOS + " WHERE id ='" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            MyDB.close();
        }
        return correcto;
    }

    public Usuarios perfilUsuarios(int id){

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase MyDB = dbHelper.getWritableDatabase();

        Usuarios usuario = null;
        Cursor cursorUsuarios = null;

        cursorUsuarios = MyDB.rawQuery(" SELECT * FROM " + TABLE_USUARIOS + " WHERE id = " + id, null);

        if (cursorUsuarios.moveToFirst()) {
            usuario = new Usuarios();
            usuario.setId(cursorUsuarios.getInt(0));
            usuario.setDni(cursorUsuarios.getString(1));
            usuario.setUsername(cursorUsuarios.getString(2));
            usuario.setNombre(cursorUsuarios.getString(4));
            usuario.setApellido(cursorUsuarios.getString(5));
        }

        cursorUsuarios.close();

        return usuario;
    }

    public Usuarios idUsuario (String user){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase MyDB = dbHelper.getWritableDatabase();

        Usuarios usuarioid = null;
        Cursor cursorUsuarios = null;

        cursorUsuarios = MyDB.rawQuery("  SELECT id FROM " + TABLE_USUARIOS + " WHERE username = '" + user + "'", null);
        if (cursorUsuarios.moveToFirst()) {
            usuarioid = new Usuarios();
            usuarioid.setId(cursorUsuarios.getInt(0));
        }

        cursorUsuarios.close();

        return usuarioid;
    }


    public boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM " + TABLE_USUARIOS + " WHERE username = ?", new String[] {username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean checkusernamepassword (String username, String contraseña) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM " + TABLE_USUARIOS + " WHERE username = ? and contraseña = ?", new String[] {username, contraseña});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

}