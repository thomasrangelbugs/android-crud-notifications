package com.thomasrangelbugs.cadastroconteudos.ui;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.thomasrangelbugs.cadastroconteudos.R;

public abstract class BaseMenuActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opcoes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.menu_listagem && getCurrentMenuId() != R.id.menu_listagem) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        }

        if (itemId == R.id.menu_cadastro && getCurrentMenuId() != R.id.menu_cadastro) {
            startActivity(new Intent(this, CadastroActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected abstract int getCurrentMenuId();
}
