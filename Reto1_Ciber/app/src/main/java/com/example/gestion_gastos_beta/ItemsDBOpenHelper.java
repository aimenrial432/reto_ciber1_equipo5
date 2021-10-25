package com.example.gestion_gastos_beta;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Constructor;

public class ItemsDBOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="Gestion_Gastos_Beta";
    private static final int DB_VERSION=1;
    private static final String TABLE_CREATE="";

    public ItemsDBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION); }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
