package pe.area51.clase03_02.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pe.area51.clase03_02.R;

public class LoginActivity extends AppCompatActivity {
    private TextView usuario, password;
    private Button entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = findViewById(R.id.etUsuario);
        password = findViewById(R.id.etPassword);
        entrar = findViewById(R.id.btnEntrar);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuarioValor = usuario.getText().toString();
                String passwordValor = password.getText().toString();

                if (usuarioValor.equals("")) {
                    usuario.setError("Ingrese su usuario");
                    return;
                } else {
                    usuario.setError(null);
                }

                if (passwordValor.equals("")) {
                    password.setError("Ingrese su password");
                    return;
                } else {
                    password.setError(null);
                }

                Intent intent = new Intent(
                        LoginActivity.this, ListadoActivity.class);
                startActivity(intent);
            }
        });
    }
}
