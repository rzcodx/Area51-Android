package pe.area51.clase04_02.ui.registro_tipo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import pe.area51.clase04_02.AndroidApplication;
import pe.area51.clase04_02.R;
import pe.area51.clase04_02.Tipo;

public class RegistroTipoFragment extends Fragment {

    private RegistroTipoModel registroTipoModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        registroTipoModel =
                ViewModelProviders.of(this).get(RegistroTipoModel.class);
        View root = inflater.inflate(R.layout.fragment_registro_tipo, container, false);

        // final -> Para que la variable pueda ser accesible en el método del botón guardar
        final EditText etTipo = root.findViewById(R.id.etTipo);
        Button btnGuardar = root.findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tipo = etTipo.getText().toString();

                Tipo obj = new Tipo();
                obj.setNombre(tipo);
                AndroidApplication.listaTipo.add(obj);

                AndroidApplication.hideKeyboardFrom(getContext(), etTipo);

                Toast.makeText(getContext(),
                        "Tipo registrado", Toast.LENGTH_SHORT).show();
                etTipo.setText("");
                etTipo.requestFocus();
            }
        });

        return root;
    }
}