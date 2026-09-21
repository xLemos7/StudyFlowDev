package com.example.studyflowdev.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyflowdev.R;
import com.example.studyflowdev.data.DatabaseHelper;
import com.example.studyflowdev.model.Tecnologia;
import com.example.studyflowdev.ui.adapter.TecnologiaAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TecnologiasFragment extends Fragment {

    private RecyclerView recyclerView;
    private TecnologiaAdapter adapter;
    private DatabaseHelper dbHelper;
    private FloatingActionButton fabAdd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tecnologias, container, false);

        recyclerView = view.findViewById(R.id.recycler_tecnologias);
        fabAdd = view.findViewById(R.id.fab_add_tecnologia);

        dbHelper = new DatabaseHelper(getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        
        List<Tecnologia> lista = dbHelper.getAllTecnologias();
        adapter = new TecnologiaAdapter(lista, tecnologia -> {
            Intent intent = new Intent(getActivity(), ConteudosActivity.class);
            intent.putExtra("tecnologia_id", tecnologia.getId());
            intent.putExtra("tecnologia_nome", tecnologia.getNome());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        fabAdd.setOnClickListener(v -> showAddTecnologiaDialog());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null && dbHelper != null) {
            adapter.updateList(dbHelper.getAllTecnologias());
        }
    }

    private void showAddTecnologiaDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.title_add_tecnologia);

        final EditText input = new EditText(getContext());
        input.setHint(R.string.hint_nome_tecnologia);
        input.setSingleLine();
        builder.setView(input);

        builder.setPositiveButton(R.string.btn_salvar, (dialog, win) -> {
            String nome = input.getText().toString().trim();
            if (!nome.isEmpty()) {
                Tecnologia nova = new Tecnologia(nome);
                dbHelper.insertTecnologia(nova);
                adapter.updateList(dbHelper.getAllTecnologias());
                Toast.makeText(getContext(), "Tecnologia cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), R.string.msg_tecnologia_obrigatoria, Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton(R.string.btn_cancelar, (dialog, win) -> dialog.cancel());

        builder.show();
    }
}
