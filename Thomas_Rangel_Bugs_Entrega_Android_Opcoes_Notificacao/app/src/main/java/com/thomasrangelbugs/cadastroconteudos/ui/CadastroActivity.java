package com.thomasrangelbugs.cadastroconteudos.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.thomasrangelbugs.cadastroconteudos.R;
import com.thomasrangelbugs.cadastroconteudos.db.DatabaseHelper;
import com.thomasrangelbugs.cadastroconteudos.utils.NotificacaoHelper;
import com.thomasrangelbugs.cadastroconteudos.utils.PermissaoHelper;

public class CadastroActivity extends BaseMenuActivity {

    private TextInputEditText editTitulo;
    private TextInputEditText editDescricao;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        setTitle(R.string.titulo_cadastro);

        PermissaoHelper.solicitarPermissaoNotificacaoSeNecessario(this);

        databaseHelper = new DatabaseHelper(this);
        editTitulo = findViewById(R.id.editTitulo);
        editDescricao = findViewById(R.id.editDescricao);
        MaterialButton buttonSalvar = findViewById(R.id.buttonSalvar);

        buttonSalvar.setOnClickListener(view -> salvarConteudo());
    }

    private void salvarConteudo() {
        String titulo = editTitulo.getText() != null ? editTitulo.getText().toString().trim() : "";
        String descricao = editDescricao.getText() != null ? editDescricao.getText().toString().trim() : "";

        boolean valido = true;

        if (titulo.isEmpty()) {
            editTitulo.setError(getString(R.string.campo_obrigatorio));
            valido = false;
        }

        if (descricao.isEmpty()) {
            editDescricao.setError(getString(R.string.campo_obrigatorio));
            valido = false;
        }

        if (!valido) {
            return;
        }

        long idInserido = databaseHelper.inserirConteudo(titulo, descricao);

        if (idInserido <= 0) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.alerta_erro_titulo)
                    .setMessage(R.string.erro_salvar)
                    .setPositiveButton(android.R.string.ok, null)
                    .show();
            return;
        }

        int totalConteudos = databaseHelper.getTotalConteudos();
        if (totalConteudos % 10 == 0) {
            NotificacaoHelper.mostrarNotificacaoMultiploDeDez(this, totalConteudos);
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    protected int getCurrentMenuId() {
        return R.id.menu_cadastro;
    }
}
