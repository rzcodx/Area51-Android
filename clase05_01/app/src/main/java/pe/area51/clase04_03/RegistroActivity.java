package pe.area51.clase04_03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class RegistroActivity extends AppCompatActivity {
    private TextInputEditText etNombre, etApellido, etEdad, etDireccion;
    private Button btnAgregar;
    private FrameLayout progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etEdad = findViewById(R.id.etEdad);
        etDireccion = findViewById(R.id.etDireccion);
        btnAgregar = findViewById(R.id.btnAgregar);
        progress = findViewById(R.id.progress);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Habilitar progress
        progress.setVisibility(View.VISIBLE);

        if (getIntent().hasExtra("persona")) {
            Persona item = getIntent().getParcelableExtra("persona");
            etNombre.setText(item.getNombre());
            etApellido.setText(item.getApellido());
            etEdad.setText(item.getEdad());
            etDireccion.setText(item.getDireccion());
            btnAgregar.setTag(item.getId());
            btnAgregar.setText("Modificar");
        } else {
            btnAgregar.setTag(-1);
        }

        ProgressBar progressBar = new ProgressBar(this);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = etNombre.getText().toString();
                String apellido = etApellido.getText().toString();
                String edad = etEdad.getText().toString();
                String direccion = etDireccion.getText().toString();

                Persona persona = new Persona();
                persona.setNombre(nombre);
                persona.setApellido(apellido);
                persona.setEdad(edad);
                persona.setDireccion(direccion);

                SentenciaSQL sentenciaSQL = new SentenciaSQL(RegistroActivity.this);

                int id = (int) btnAgregar.getTag();
                if (id == -1) {
                    sentenciaSQL.registrar(persona);
                    Toast.makeText(RegistroActivity.this,
                            "Reegistro correcto", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    persona.setId(id);
                    sentenciaSQL.actualizar(persona);
                    Toast.makeText(RegistroActivity.this,
                            "Actualización correcta", Toast.LENGTH_SHORT).show();
                    finish();
                }


            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
