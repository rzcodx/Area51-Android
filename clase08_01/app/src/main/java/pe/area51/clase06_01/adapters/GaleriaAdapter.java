package pe.area51.clase06_01.adapters;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

import pe.area51.clase06_01.fragments.GaleriaFragment;
import pe.area51.clase06_01.net.response.foto.Foto;

public class GaleriaAdapter extends FragmentPagerAdapter {
    private ArrayList<Foto> lista;

    public GaleriaAdapter(FragmentManager fm) {
        super(fm);
        lista = new ArrayList<>();
    }

    public void agregarLista(ArrayList<Foto> datos) {
        lista.clear();
        lista.addAll(datos);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        GaleriaFragment fragment = new GaleriaFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        //bundle.putParcelableArrayList("lista", lista);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return lista.size();
    }
}
