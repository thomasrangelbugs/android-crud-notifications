package com.thomasrangelbugs.cadastroconteudos.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.thomasrangelbugs.cadastroconteudos.model.Conteudo;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "conteudos.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_CONTEUDOS = "conteudos";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITULO = "titulo";
    private static final String COLUMN_DESCRICAO = "descricao";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_CONTEUDOS + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TITULO + " TEXT NOT NULL, "
                + COLUMN_DESCRICAO + " TEXT NOT NULL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTEUDOS);
        onCreate(db);
    }

    public long inserirConteudo(String titulo, String descricao) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITULO, titulo);
        values.put(COLUMN_DESCRICAO, descricao);
        return db.insert(TABLE_CONTEUDOS, null, values);
    }

    public int getTotalConteudos() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_CONTEUDOS, null);
        int total = 0;

        if (cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }

        cursor.close();
        return total;
    }

    public List<Conteudo> listarConteudos() {
        List<Conteudo> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_CONTEUDOS,
                null,
                null,
                null,
                null,
                null,
                COLUMN_ID + " DESC"
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String titulo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITULO));
                String descricao = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRICAO));
                lista.add(new Conteudo(id, titulo, descricao));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return lista;
    }
}
