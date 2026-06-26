package com.thomasrangelbugs.cadastroconteudos.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thomasrangelbugs.cadastroconteudos.R;
import com.thomasrangelbugs.cadastroconteudos.adapter.ConteudoAdapter;
import com.thomasrangelbugs.cadastroconteudos.db.DatabaseHelper;
import com.thomasrangelbugs.cadastroconteudos.model.Conteudo;
import com.thomasrangelbugs.cadastroconteudos.utils.PermissaoHelper;

import java.util.List;

public class MainActivity extends BaseMenuActivity {

    private RecyclerView recyclerView;
    private TextView textEmpty;
    private ConteudoAdapter adapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.titulo_listagem);

        PermissaoHelper.solicitarPermissaoNotificacaoSeNecessario(this);

        databaseHelper = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerViewConteudos);
        textEmpty = findViewById(R.id.textEmpty);

        adapter = new ConteudoAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarConteudos();
    }

    private void carregarConteudos() {
        List<Conteudo> conteudos = databaseHelper.listarConteudos();
        adapter.atualizarItens(conteudos);
        textEmpty.setVisibility(conteudos.isEmpty() ? View.VISIBLE : View.GONE);
    }

    @Override
    protected int getCurrentMenuId() {
        return R.id.menu_listagem;
    }
}
