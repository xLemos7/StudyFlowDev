package com.example.studyflowdev.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyflowdev.R;
import com.example.studyflowdev.data.DatabaseHelper;
import com.example.studyflowdev.model.Conteudo;
import com.example.studyflowdev.ui.adapter.ConteudoAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ConteudosActivity extends AppCompatActivity {

    private long tecnologiaId;
    private String tecnologiaNome;

    private RecyclerView recyclerView;
    private ConteudoAdapter adapter;
    private DatabaseHelper dbHelper;
    private FloatingActionButton fabAdd;
    private TextView textTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteudos);

        tecnologiaId = getIntent().getLongExtra("tecnologia_id", -1);
        tecnologiaNome = getIntent().getStringExtra("tecnologia_nome");

        textTitulo = findViewById(R.id.text_tecnologia_titulo);
        recyclerView = findViewById(R.id.recycler_conteudos);
        fabAdd = findViewById(R.id.fab_add_conteudo);

        if (tecnologiaNome != null) {
            textTitulo.setText(tecnologiaNome);
        }

        dbHelper = new DatabaseHelper(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Conteudo> lista = dbHelper.getConteudosPorTecnologia(tecnologiaId);
        adapter = new ConteudoAdapter(lista, conteudo -> showUpdateProgressoDialog(conteudo));
        recyclerView.setAdapter(adapter);

        fabAdd.setOnClickListener(v -> showAddConteudoDialog());
    }

    private void showAddConteudoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.title_add_conteudo);

        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_conteudo, null);
        builder.setView(dialogView);

        final EditText editTitulo = dialogView.findViewById(R.id.edit_titulo_conteudo);
        final EditText editDescricao = dialogView.findViewById(R.id.edit_descricao_conteudo);

        builder.setPositiveButton(R.string.btn_salvar, (dialog, which) -> {
            String titulo = editTitulo.getText().toString().trim();
            String descricao = editDescricao.getText().toString().trim();

            if (!titulo.isEmpty()) {
                Conteudo novo = new Conteudo(tecnologiaId, titulo, descricao, 0);
                dbHelper.insertConteudo(novo);
                adapter.updateList(dbHelper.getConteudosPorTecnologia(tecnologiaId));
                Toast.makeText(ConteudosActivity.this, "Conteúdo adicionado com sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ConteudosActivity.this, R.string.msg_conteudo_obrigatorio, Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton(R.string.btn_cancelar, (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void showUpdateProgressoDialog(final Conteudo conteudo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atualizar Progresso");

        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_update_progresso, null);
        builder.setView(dialogView);

        final TextView textProgresso = dialogView.findViewById(R.id.text_progresso_atual);
        final SeekBar seekBar = dialogView.findViewById(R.id.seekbar_progresso);

        seekBar.setProgress(conteudo.getProgresso());
        textProgresso.setText(getString(R.string.title_progresso, conteudo.getProgresso()));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textProgresso.setText(getString(R.string.title_progresso, progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        builder.setPositiveButton(R.string.btn_salvar, (dialog, which) -> {
            int novoProgresso = seekBar.getProgress();
            dbHelper.updateProgressoConteudo(conteudo.getId(), novoProgresso);
            adapter.updateList(dbHelper.getConteudosPorTecnologia(tecnologiaId));
            Toast.makeText(ConteudosActivity.this, "Progresso atualizado com sucesso!", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton(R.string.btn_cancelar, (dialog, which) -> dialog.cancel());
        builder.show();
    }
}
