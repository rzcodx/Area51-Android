package pe.area51.clase04_03;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ManageSQL extends SQLiteOpenHelper {
    public ManageSQL(@Nullable Context context) {
        super(context, "clase04.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Crear las tablas que manejaremos internamente
        sqLiteDatabase.execSQL(
                "CREATE TABLE persona (" +
                        "id integer primary key autoincrement," +
                        "nombre text," +
                        "apellido text," +
                        "edad text," +
                        "direccion text" +
                        ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Se va a ejecutar cada vez que cambiemos la versión de la base de datos
    }
}
