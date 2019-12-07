package pe.area51.clase03_02.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import pe.area51.clase03_02.AndroidApplication;
import pe.area51.clase03_02.R;
import pe.area51.clase03_02.models.Usuario;

public class RegistroActivity extends AppCompatActivity {
    private TextView etUsuario, etNombre, etPassword, etConfirmaPassword;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etUsuario = findViewById(R.id.etUsuario);
        etNombre = findViewById(R.id.etNombre);
        etPassword = findViewById(R.id.etPassword);
        etConfirmaPassword = findViewById(R.id.etConfirmaPassword);
        btnGuardar = findViewById(R.id.btnGuardar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuarioValor = etUsuario.getText().toString();
                String nombreValor = etNombre.getText().toString();
                String passwordValor = etPassword.getText().toString();
                String confirmaPasswordValor = etConfirmaPassword.getText().toString();

                if (usuarioValor.equals("")) {
                    etUsuario.setError("Ingrese su usuario");
                    return;
                } else {
                    etUsuario.setError(null);
                }

                if (nombreValor.equals("")) {
                    etNombre.setError("Ingrese su nombre");
                    return;
                } else {
                    etNombre.setError(null);
                }

                if (passwordValor.equals("")) {
                    etPassword.setError("Ingrese su password");
                    return;
                } else {
                    etPassword.setError(null);
                }

                if (confirmaPasswordValor.equals("")) {
                    etConfirmaPassword.setError("Confirme su password");
                    return;
                } else {
                    etConfirmaPassword.setError(null);
                }

                if (!confirmaPasswordValor.equals(passwordValor)) {
                    etConfirmaPassword.setError("El password debe coincidir");
                    return;
                } else {
                    etConfirmaPassword.setError(null);
                }

                Usuario obj = new Usuario();
                obj.setNombre(nombreValor);
                obj.setUsuario(usuarioValor);
                obj.setPassword(passwordValor);

                AndroidApplication.lista.add(obj);

                Toast toast = Toast.makeText(RegistroActivity.this,
                        "Usuario registrado", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM, 0, 0);
                toast.show();
                finish();
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
