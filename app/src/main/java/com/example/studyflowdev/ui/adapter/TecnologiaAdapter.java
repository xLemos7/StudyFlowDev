package com.example.studyflowdev.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyflowdev.R;
import com.example.studyflowdev.model.Tecnologia;

import java.util.List;

public class TecnologiaAdapter extends RecyclerView.Adapter<TecnologiaAdapter.ViewHolder> {

    private List<Tecnologia> tecnologias;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Tecnologia tecnologia);
    }

    public TecnologiaAdapter(List<Tecnologia> tecnologias, OnItemClickListener listener) {
        this.tecnologias = tecnologias;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tecnologia, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tecnologia tecnologia = tecnologias.get(position);
        holder.textNome.setText(tecnologia.getNome());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(tecnologia));
    }

    @Override
    public int getItemCount() {
        return tecnologias.size();
    }

    public void updateList(List<Tecnologia> newList) {
        this.tecnologias = newList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textNome;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textNome = itemView.findViewById(R.id.text_nome_tecnologia);
        }
    }
}
