package pe.area51.clase03_02.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pe.area51.clase03_02.R;

public class RegistroActivity extends AppCompatActivity {
    private TextView usuario, nombre, password, confirmaPassword;
    private Button guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        usuario = findViewById(R.id.etUsuario);
        nombre = findViewById(R.id.etNombre);
        password = findViewById(R.id.etPassword);
        confirmaPassword = findViewById(R.id.etConfirmaPassword);
        guardar = findViewById(R.id.btnGuardar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuarioValor = usuario.getText().toString();
                String nombreValor = nombre.getText().toString();
                String passwordValor = password.getText().toString();
                String confirmaPasswordValor = confirmaPassword.getText().toString();

                if (usuarioValor.equals("")) {
                    usuario.setError("Ingrese su usuario");
                    return;
                } else {
                    usuario.setError(null);
                }

                if (nombreValor.equals("")) {
                    nombre.setError("Ingrese su nombre");
                    return;
                } else {
                    nombre.setError(null);
                }

                if (passwordValor.equals("")) {
                    password.setError("Ingrese su password");
                    return;
                } else {
                    password.setError(null);
                }

                if (confirmaPasswordValor.equals("")) {
                    confirmaPassword.setError("Confirme su password");
                    return;
                } else {
                    confirmaPassword.setError(null);
                }

                if (!confirmaPasswordValor.equals(passwordValor)) {
                    confirmaPassword.setError("El password debe coincidir");
                    return;
                } else {
                    confirmaPassword.setError(null);
                }

                Intent intent = new Intent(
                        RegistroActivity.this, ListadoActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
