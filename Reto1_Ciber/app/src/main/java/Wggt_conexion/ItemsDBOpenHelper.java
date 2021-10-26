package Wggt_conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ItemsDBOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="Gestion_Gastos_Beta";
    private static final int DB_VERSION=1;
    private static final String TABLE_CREATE="create table bda_Empleados (\n" +
            "\n" +
            "\t\n" +
            "\tId_user int,\n" +
            "    \tDNI varchar(9),\n" +
            "\tNombre varchar(50) not null,\n" +
            "    \tApellido varchar(50) not null,\n" +
            "\te_mail varchar(250) not null,\n" +
            "    \tUsername varchar(50) not null,\n" +
            "    \tPassword_hash varchar(255) not null,\n" +
            "    \tFecha_reg datetime,\n" +
            "   \tDepartamento_Id int not null,\n" +
            "    \tId_rol int not null\n" +
            "\n" +
            ");";

    public ItemsDBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS bda_Empleados");
        onCreate(db);
    }
}
