package pe.area51.clase04_02.ui.registro_listado;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import pe.area51.clase04_02.Producto;
import pe.area51.clase04_02.R;
import pe.area51.clase04_02.Tipo;

public class RegistroListadoFragment extends Fragment {

    private RegistroListadoViewModel registroListadoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        registroListadoViewModel =
                ViewModelProviders.of(this).get(RegistroListadoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_registro_listado, container, false);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Spinner spTipo = view.findViewById(R.id.spTipo);
        final EditText etNombre = view.findViewById(R.id.etNombre);
        final EditText etDescripcion = view.findViewById(R.id.etDescripcion);
        final EditText etPrecio = view.findViewById(R.id.etPrecio);
        Button btnGuardar = view.findViewById(R.id.btnGuardar);

        ArrayList<String> tipoValores = new ArrayList<>();
        for (Tipo tipo: AndroidApplication.listaTipo) {
            tipoValores.add(tipo.getNombre());
        }

        ArrayAdapter adapter = new ArrayAdapter(getContext(),
                android.R.layout.simple_spinner_dropdown_item, tipoValores);
        spTipo.setAdapter(adapter);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tipo = spTipo.getSelectedItem().toString();
                String nombre = etNombre.getText().toString();
                String descripcion = etDescripcion.getText().toString();
                String precio = etPrecio.getText().toString();

                Producto obj = new Producto();
                obj.setNombre(nombre);
                obj.setTipo(tipo);
                obj.setDescripcion(descripcion);
                obj.setPrecio(precio);
                AndroidApplication.listaProducto.add(obj);

                AndroidApplication.hideKeyboardFrom(getContext(), etPrecio);

                Toast.makeText(getContext(),
                        "Producto registrado", Toast.LENGTH_SHORT).show();

                spTipo.setSelection(0);
                etNombre.setText("");
                etDescripcion.setText("");
                etPrecio.setText("");
                etNombre.requestFocus();
            }
        });
    }
}