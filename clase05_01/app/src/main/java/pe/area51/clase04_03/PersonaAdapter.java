package pe.area51.clase04_03;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Persona> lista;

    public PersonaAdapter(Context context, ArrayList<Persona> lista) {
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_persona, parent, false);
        return new PersonaViewHolder(view);
    }

    class PersonaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvEdad, tvDireccion;
        CardView contenedor;

        public PersonaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvEdad = itemView.findViewById(R.id.tvEdad);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            contenedor = itemView.findViewById(R.id.contenedor);
        }

        public void bind(int position) {
            final Persona persona = lista.get(position);
            tvNombre.setText(persona.getNombre() + " " + persona.getApellido());
            tvEdad.setText(persona.getEdad());
            tvDireccion.setText(persona.getDireccion());

            contenedor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Seleccione una opción");
                    builder.setPositiveButton("Modificar",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(context, RegistroActivity.class);
                                    intent.putExtra("persona", persona);
                                    context.startActivity(intent);
                                }
                            });
                    builder.setNegativeButton("Eliminar",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    SentenciaSQL sentenciaSQL = new SentenciaSQL(context);
                                    sentenciaSQL.eliminar(persona);

                                    lista.remove(persona);
                                    // notificación al adapter
                                    notifyDataSetChanged();

                                    Toast.makeText(context,
                                            "Persona eliminada", Toast.LENGTH_SHORT).show();
                                }
                            });
                    builder.show();
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PersonaViewHolder personaViewHolder = (PersonaViewHolder) holder;
        personaViewHolder.bind(position);

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
