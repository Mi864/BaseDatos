package com.example.basedatos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentFormulario extends androidx.fragment.app.Fragment {

    int pagina;

    public FragmentFormulario(int pagina) {
        this.pagina = pagina;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_formulario,container,false);

        RecyclerView rvFormulario = view.findViewById(R.id.rvFormulario);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(RecyclerView.VERTICAL);
        rvFormulario.setLayoutManager(llm);

        RvFormularioAdaptador rvFormularioAdaptador = new RvFormularioAdaptador(pagina,getActivity());
        rvFormulario.setAdapter(rvFormularioAdaptador);

        return view;
    }
}
