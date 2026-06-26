package com.thomasrangelbugs.cadastroconteudos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thomasrangelbugs.cadastroconteudos.R;
import com.thomasrangelbugs.cadastroconteudos.model.Conteudo;

import java.util.ArrayList;
import java.util.List;

public class ConteudoAdapter extends RecyclerView.Adapter<ConteudoAdapter.ConteudoViewHolder> {

    private final List<Conteudo> itens = new ArrayList<>();

    public void atualizarItens(List<Conteudo> novosItens) {
        itens.clear();
        itens.addAll(novosItens);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ConteudoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_conteudo, parent, false);
        return new ConteudoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConteudoViewHolder holder, int position) {
        Conteudo conteudo = itens.get(position);
        holder.textTitulo.setText(conteudo.getTitulo());
        holder.textDescricao.setText(conteudo.getDescricao());
        holder.textId.setText(holder.itemView.getContext().getString(R.string.descricao_item_id, conteudo.getId()));
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }

    static class ConteudoViewHolder extends RecyclerView.ViewHolder {
        private final TextView textTitulo;
        private final TextView textDescricao;
        private final TextView textId;

        public ConteudoViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitulo = itemView.findViewById(R.id.textTitulo);
            textDescricao = itemView.findViewById(R.id.textDescricao);
            textId = itemView.findViewById(R.id.textId);
        }
    }
}
