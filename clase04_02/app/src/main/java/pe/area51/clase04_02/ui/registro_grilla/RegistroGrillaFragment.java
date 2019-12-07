package pe.area51.clase04_02.ui.registro_grilla;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;

import pe.area51.clase04_02.AndroidApplication;
import pe.area51.clase04_02.Imagen;
import pe.area51.clase04_02.Producto;
import pe.area51.clase04_02.R;

public class RegistroGrillaFragment extends Fragment {

    private RegistroGrillaViewModel registroGrillaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        registroGrillaViewModel =
                ViewModelProviders.of(this).get(RegistroGrillaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_registro_grilla, container, false);

        final Spinner spProducto = root.findViewById(R.id.spProducto);
        final EditText etNombre = root.findViewById(R.id.etNombre);
        final EditText etLink = root.findViewById(R.id.etLink);
        Button btnGuardar = root.findViewById(R.id.btnGuardar);

        ArrayList<String> productosValor = new ArrayList<>();
        for (Producto producto: AndroidApplication.listaProducto) {
            productosValor.add(producto.getNombre());
        }

        ArrayAdapter adapter = new ArrayAdapter(getContext(),
                android.R.layout.simple_spinner_dropdown_item, productosValor);
        spProducto.setAdapter(adapter);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String producto = spProducto.getSelectedItem().toString();
                String nombre = etNombre.getText().toString();
                String link = etLink.getText().toString();

                Imagen imagen = new Imagen();
                imagen.setProducto(producto);
                imagen.setNombre(nombre);
                imagen.setLink(link);
                AndroidApplication.listaImagen.add(imagen);
                Toast.makeText(getContext(),
                        "Imagen registrada", Toast.LENGTH_SHORT).show();
                etNombre.setText("");
                etLink.setText("");
                etNombre.requestFocus();

            }
        });

        return root;
    }
}