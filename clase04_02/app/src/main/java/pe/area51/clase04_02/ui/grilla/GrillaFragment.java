package pe.area51.clase04_02.ui.grilla;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import pe.area51.clase04_02.AndroidApplication;
import pe.area51.clase04_02.ImagenAdapter;
import pe.area51.clase04_02.R;

public class GrillaFragment extends Fragment {

    private GrillaViewModel grillaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        grillaViewModel =
                ViewModelProviders.of(this).get(GrillaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_grilla, container, false);

        GridView gvDatos = root.findViewById(R.id.gvDatos);
        ImagenAdapter adapter = new ImagenAdapter(getContext(), 0,
                AndroidApplication.listaImagen);
        gvDatos.setAdapter(adapter);

        return root;
    }
}