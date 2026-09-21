package com.example.studyflowdev.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyflowdev.R;
import com.example.studyflowdev.model.Conteudo;

import java.util.List;

public class ConteudoAdapter extends RecyclerView.Adapter<ConteudoAdapter.ViewHolder> {

    private List<Conteudo> conteudos;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Conteudo conteudo);
    }

    public ConteudoAdapter(List<Conteudo> conteudos, OnItemClickListener listener) {
        this.conteudos = conteudos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_conteudo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Conteudo conteudo = conteudos.get(position);
        holder.textTitulo.setText(conteudo.getTitulo());
        holder.textDescricao.setText(conteudo.getDescricao());
        holder.progressBar.setProgress(conteudo.getProgresso());
        holder.textPorcentagem.setText(conteudo.getProgresso() + "%");

        holder.itemView.setOnClickListener(v -> listener.onItemClick(conteudo));
    }

    @Override
    public int getItemCount() {
        return conteudos.size();
    }

    public void updateList(List<Conteudo> newList) {
        this.conteudos = newList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTitulo;
        TextView textDescricao;
        ProgressBar progressBar;
        TextView textPorcentagem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitulo = itemView.findViewById(R.id.text_titulo_conteudo);
            textDescricao = itemView.findViewById(R.id.text_descricao_conteudo);
            progressBar = itemView.findViewById(R.id.progress_conteudo);
            textPorcentagem = itemView.findViewById(R.id.text_progresso_porcentagem);
        }
    }
}
