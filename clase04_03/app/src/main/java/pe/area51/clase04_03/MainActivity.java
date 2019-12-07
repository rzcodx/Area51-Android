package pe.area51.clase04_03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SentenciaSQL sentenciaSQL = new SentenciaSQL(this);
        //sentenciaSQL.registrar("Raul 5", "Falcon 5", "20", "Calle 3");
        //sentenciaSQL.actualizar(4, "Raul 4", "Falcon 4", "20", "Calle 3");
        sentenciaSQL.eliminar(5);

        for (Persona persona: sentenciaSQL.obtenerDatos()) {
            Log.d("clase04",
                    "Nombre: " + persona.getNombre() + "\tApellido: " + persona.getApellido());
        }
    }
}
