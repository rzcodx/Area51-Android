package pe.area51.clase04_03;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class SentenciaSQL {
    private ManageSQL manageSQL;

    public SentenciaSQL(Context context) {
        manageSQL = new ManageSQL(context);
    }

    public void registrar(String nombre, String apellido, String edad, String direccion) {
        SQLiteDatabase db = manageSQL.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("apellido", apellido);
        values.put("edad", edad);
        values.put("direccion", direccion);
        db.insert("persona", null, values);
    }

    public ArrayList<Persona> obtenerDatos() {
        SQLiteDatabase db = manageSQL.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM persona ", null);
        ArrayList<Persona> lista = null;
        if (cursor.moveToFirst()) {
            lista = new ArrayList<>();
            do {
                Persona persona = new Persona();
                persona.setId(cursor.getInt(cursor.getColumnIndex("id")));
                persona.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                persona.setApellido(cursor.getString(cursor.getColumnIndex("apellido")));
                persona.setEdad(cursor.getString(cursor.getColumnIndex("edad")));
                persona.setDireccion(cursor.getString(cursor.getColumnIndex("direccion")));
                lista.add(persona);
            } while (cursor.moveToNext());
        }
        return lista;
    }

    public void actualizar(int id, String nombre, String apellido, String edad, String direccion) {
        SQLiteDatabase db = manageSQL.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("apellido", apellido);
        values.put("edad", edad);
        values.put("direccion", direccion);
        db.update("persona", values, "id=?", new String[]{String.valueOf(id)});
    }

    public void eliminar(int id) {
        SQLiteDatabase db = manageSQL.getWritableDatabase();
        db.delete("persona", "id=?", new String[]{String.valueOf(id)});
    }
}
