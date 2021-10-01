package com.example.basedatos;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RvFormularioAdaptador extends RecyclerView.Adapter<RvFormularioAdaptador.ViewHolder> {

    int pagina;
    Activity activity;

    public RvFormularioAdaptador(int pagina, Activity activity) {
        this.pagina   = pagina;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.et_formulario,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        BaseDatos baseDatos = new BaseDatos(activity);
        ContentValues contentValues = new ContentValues();

        SharedPreferences sharedPreferences = activity.getSharedPreferences("Formulario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        switch (position){

            case 0: holder.layoutEtDato.setHint(ConstantesBaseDatos.NOMBRE);break;
            case 1: holder.layoutEtDato.setHint(ConstantesBaseDatos.OCUPACION);break;
            case 2: holder.layoutEtDato.setHint(ConstantesBaseDatos.EMAIL);break;
            case 3: holder.layoutEtDato.setHint(ConstantesBaseDatos.CELULAR);break;
            case 4: holder.layoutEtDato.setHint(ConstantesBaseDatos.DIRECCION);break;
        }

        if(pagina==1){
            String dato = baseDatos.obtenerDato(position);
            holder.etDato.setText(dato);
        }
        else {
            switch (position){

                case 0: holder.etDato.setText(sharedPreferences.getString("Nombre",""));break;
                case 1: holder.etDato.setText(sharedPreferences.getString("Ocupacion",""));break;
                case 2: holder.etDato.setText(sharedPreferences.getString("Email",""));break;
                case 3: holder.etDato.setText(sharedPreferences.getString("Celular",""));break;
                case 4: holder.etDato.setText(sharedPreferences.getString("Direccion",""));break;
            }
        }

        holder.etDato.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {

                int position = holder.getAdapterPosition();
                String dato  = String.valueOf(holder.etDato.getText());

                if(pagina==1){
                    switch (position){
                        case 0: contentValues.put(ConstantesBaseDatos.NOMBRE, dato);break;
                        case 1: contentValues.put(ConstantesBaseDatos.OCUPACION, dato);break;
                        case 2: contentValues.put(ConstantesBaseDatos.EMAIL, dato);break;
                        case 3: contentValues.put(ConstantesBaseDatos.CELULAR, dato);break;
                        case 4: contentValues.put(ConstantesBaseDatos.DIRECCION, dato);break;
                    }
                    contentValues.put(ConstantesBaseDatos.POSITION,position);

                    String datoBD = baseDatos.obtenerDato(position);
                    if(datoBD==null){baseDatos.insertarDato(contentValues);}
                    else baseDatos.actualizarDato(contentValues,position);
                }

                else {
                    switch (position) {
                        case 0: editor.putString("Nombre", dato);break;
                        case 1: editor.putString("Ocupacion", dato);break;
                        case 2: editor.putString("Email", dato);break;
                        case 3: editor.putString("Celular", dato);break;
                        case 4: editor.putString("Direccion", dato);break;
                    }
                    editor.apply();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextInputLayout layoutEtDato;
        private final TextInputEditText etDato;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            layoutEtDato = itemView.findViewById(R.id.layoutEtDato);
            etDato = itemView.findViewById(R.id.etDato);
        }
    }
}
