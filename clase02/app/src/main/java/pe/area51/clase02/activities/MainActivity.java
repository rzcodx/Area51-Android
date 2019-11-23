package pe.area51.clase02.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import pe.area51.clase02.models.Producto;
import pe.area51.clase02.R;

public class MainActivity extends AppCompatActivity {
    private Spinner categoria;
    private TextView nombre, descripcion;
    private RadioButton publico, privado;
    private CheckBox acepto;
    private Button registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Habilitamos el boton del home
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Registro producto");

        categoria = findViewById(R.id.spCategoria);
        nombre = findViewById(R.id.etNombre);
        descripcion = findViewById(R.id.etDescripcion);
        publico = findViewById(R.id.rbPublico);
        privado = findViewById(R.id.rbPrivado);
        acepto = findViewById(R.id.cbAcepto);
        registrar = findViewById(R.id.btnRegistrar);

        final String[] categorias = getResources().getStringArray(R.array.categorias);
        ArrayAdapter adapter = new ArrayAdapter(
                this, android.R.layout.simple_spinner_dropdown_item, categorias
        );
        categoria.setAdapter(adapter);

        int posicion = getIntent().getIntExtra("posicion", -1);
        if (posicion != -1) {
            Producto obj = ListadoActivity.listaProductos.get(posicion);

            for (int i = 0; i < categorias.length; i++) {
                if (obj.getCategoria().equals(categorias[i])) {
                    categoria.setSelection(i);
                    break;
                }
            }

            nombre.setText(obj.getNombre());
            descripcion.setText(obj.getDescripcion());
            publico.setChecked(obj.isPublico());
            privado.setChecked(!obj.isPublico());
            acepto.setChecked(obj.isAccept());

            // setTag: Sirver para almacenar algún valor que luego se puede modificar
            registrar.setTag(posicion);
            registrar.setText("Modificar");
        } else {
            registrar.setTag(-1);
        }

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String categoriaValor = categoria.getSelectedItem().toString();
                String nombreValor = nombre.getText().toString();
                String descripcionValor = descripcion.getText().toString();
                boolean publicoValor = publico.isChecked();
                boolean privadoValor = privado.isChecked();
                boolean aceptoValor = acepto.isChecked();

                if (nombreValor.length() == 0) {
                    nombre.setError("El campo es requerido");
                    return;
                } else {
                    nombre.setError(null);
                }

                if (descripcionValor.length() == 0) {
                    descripcion.setError("El campo es requerido");
                    return;
                } else {
                    descripcion.setError(null);
                }

                int posicion = (int) registrar.getTag();
                String toastText;

                if (posicion == -1) {
                    Producto obj = new Producto();
                    obj.setCategoria(categoriaValor);
                    obj.setNombre(nombreValor);
                    obj.setDescripcion(descripcionValor);
                    obj.setPublico(publicoValor);
                    obj.setAccept(aceptoValor);

                    ListadoActivity.listaProductos.add(obj);

                    toastText = "Se registró el producto";
                } else {
                    ListadoActivity.listaProductos.get(posicion).setCategoria(categoriaValor);
                    ListadoActivity.listaProductos.get(posicion).setNombre(nombreValor);
                    ListadoActivity.listaProductos.get(posicion).setDescripcion(descripcionValor);
                    ListadoActivity.listaProductos.get(posicion).setAccept(aceptoValor);
                    ListadoActivity.listaProductos.get(posicion).setPublico(publicoValor);

                    toastText = "Se actualizó el producto";
                }

                Toast toast = Toast.makeText(MainActivity.this,
                        toastText, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                finish();
            }
        });
    }

    // Funcionalidad al botón del home
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // Asignamos animación en la transición de patallas
        overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
    }
}
