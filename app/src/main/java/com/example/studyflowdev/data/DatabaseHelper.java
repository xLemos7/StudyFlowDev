package com.example.studyflowdev.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.studyflowdev.model.Conteudo;
import com.example.studyflowdev.model.Tecnologia;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "studyflow.db";
    private static final int DATABASE_VERSION = 1;

    // Tabelas
    private static final String TABLE_TECNOLOGIA = "tecnologia";
    private static final String TABLE_CONTEUDO = "conteudo";

    // Colunas Tecnologia
    private static final String COL_TEC_ID = "id";
    private static final String COL_TEC_NOME = "nome";

    // Colunas Conteudo
    private static final String COL_CONT_ID = "id";
    private static final String COL_CONT_TEC_ID = "tecnologia_id";
    private static final String COL_CONT_TITULO = "titulo";
    private static final String COL_CONT_DESCRICAO = "descricao";
    private static final String COL_CONT_PROGRESSO = "progresso";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTecnologia = "CREATE TABLE " + TABLE_TECNOLOGIA + " (" +
                COL_TEC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TEC_NOME + " TEXT NOT NULL)";

        String createConteudo = "CREATE TABLE " + TABLE_CONTEUDO + " (" +
                COL_CONT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_CONT_TEC_ID + " INTEGER NOT NULL, " +
                COL_CONT_TITULO + " TEXT NOT NULL, " +
                COL_CONT_DESCRICAO + " TEXT, " +
                COL_CONT_PROGRESSO + " INTEGER DEFAULT 0, " +
                "FOREIGN KEY(" + COL_CONT_TEC_ID + ") REFERENCES " + TABLE_TECNOLOGIA + "(" + COL_TEC_ID + "))";

        db.execSQL(createTecnologia);
        db.execSQL(createConteudo);

        // Inserir dados iniciais para não ficar vazio no MVP
        db.execSQL("INSERT INTO " + TABLE_TECNOLOGIA + " (nome) VALUES ('Java')");
        db.execSQL("INSERT INTO " + TABLE_TECNOLOGIA + " (nome) VALUES ('Android Nativo')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTEUDO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TECNOLOGIA);
        onCreate(db);
    }

    // --- CRUD TECNOLOGIA ---

    public long insertTecnologia(Tecnologia tecnologia) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TEC_NOME, tecnologia.getNome());
        long id = db.insert(TABLE_TECNOLOGIA, null, values);
        db.close();
        return id;
    }

    public List<Tecnologia> getAllTecnologias() {
        List<Tecnologia> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_TECNOLOGIA, null);

        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COL_TEC_ID));
                String nome = cursor.getString(cursor.getColumnIndexOrThrow(COL_TEC_NOME));
                lista.add(new Tecnologia(id, nome));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lista;
    }

    // --- CRUD CONTEUDO ---

    public long insertConteudo(Conteudo conteudo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_CONT_TEC_ID, conteudo.getTecnologiaId());
        values.put(COL_CONT_TITULO, conteudo.getTitulo());
        values.put(COL_CONT_DESCRICAO, conteudo.getDescricao());
        values.put(COL_CONT_PROGRESSO, conteudo.getProgresso());
        long id = db.insert(TABLE_CONTEUDO, null, values);
        db.close();
        return id;
    }

    public List<Conteudo> getConteudosPorTecnologia(long tecnologiaId) {
        List<Conteudo> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CONTEUDO + " WHERE " + COL_CONT_TEC_ID + " = ?",
                new String[]{String.valueOf(tecnologiaId)});

        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COL_CONT_ID));
                String titulo = cursor.getString(cursor.getColumnIndexOrThrow(COL_CONT_TITULO));
                String descricao = cursor.getString(cursor.getColumnIndexOrThrow(COL_CONT_DESCRICAO));
                int progresso = cursor.getInt(cursor.getColumnIndexOrThrow(COL_CONT_PROGRESSO));
                lista.add(new Conteudo(id, tecnologiaId, titulo, descricao, progresso));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lista;
    }

    public int updateProgressoConteudo(long conteudoId, int novoProgresso) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_CONT_PROGRESSO, novoProgresso);
        int rows = db.update(TABLE_CONTEUDO, values, COL_CONT_ID + " = ?", new String[]{String.valueOf(conteudoId)});
        db.close();
        return rows;
    }
}
