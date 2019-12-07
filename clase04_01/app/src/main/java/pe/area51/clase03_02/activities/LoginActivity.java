package pe.area51.clase03_02.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import pe.area51.clase03_02.AndroidApplication;
import pe.area51.clase03_02.R;
import pe.area51.clase03_02.models.Usuario;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsuario, etPassword;
    private Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuario = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        btnEntrar = findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuarioValor = etUsuario.getText().toString();
                String passwordValor = etPassword.getText().toString();

                if (usuarioValor.equals("")) {
                    etUsuario.setError("Ingrese su usuario");
                    return;
                } else {
                    etUsuario.setError(null);
                }

                if (passwordValor.equals("")) {
                    etPassword.setError("Ingrese su password");
                    return;
                } else {
                    etPassword.setError(null);
                }

                ArrayList<Usuario> lista = AndroidApplication.lista;
                Usuario obj = null;
                if (lista.size() > 0) {
                    for (Usuario usuario : lista) {
                        if (usuarioValor.equals(usuario.getUsuario()) &&
                                passwordValor.equals(usuario.getPassword())) {
                            obj = usuario;
                            break;
                        }
                    }
                    if (obj == null) {
                        Toast.makeText(LoginActivity.this,
                                "Usuario no existe", Toast.LENGTH_SHORT).show();
                    } else {
                        SharedPreferences.Editor editor = getSharedPreferences(
                                "clase04", MODE_PRIVATE).edit();
                        editor.putString("usuario", obj.getUsuario());
                        editor.putString("nombre", obj.getNombre());
                        editor.apply();

                        Intent intent = new Intent(LoginActivity.this, ListadoActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }

                } else {
                    Toast.makeText(LoginActivity.this,
                            "Usuario no existe", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
