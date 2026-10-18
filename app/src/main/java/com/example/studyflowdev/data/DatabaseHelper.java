package com.example.studyflowdev.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.studyflowdev.model.Anotacao;
import com.example.studyflowdev.model.Conteudo;
import com.example.studyflowdev.model.Desafio;
import com.example.studyflowdev.model.Favorito;
import com.example.studyflowdev.model.Meta;
import com.example.studyflowdev.model.SessaoEstudo;
import com.example.studyflowdev.model.Tecnologia;
import com.example.studyflowdev.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "studyflow.db";
    private static final int DATABASE_VERSION = 2;

    // --- TABELAS ---
    public static final String TABLE_USUARIO = "usuario";
    public static final String TABLE_TECNOLOGIA = "tecnologia";
    public static final String TABLE_CONTEUDO = "conteudo";
    public static final String TABLE_META = "meta";
    public static final String TABLE_SESSAO = "sessao_estudo";
    public static final String TABLE_DESAFIO = "desafio";
    public static final String TABLE_ANOTACAO = "anotacao";
    public static final String TABLE_FAVORITO = "favorito";

    // --- COLUNAS USUARIO ---
    public static final String COL_USER_ID = "id";
    public static final String COL_USER_NOME = "nome";
    public static final String COL_USER_EMAIL = "email";
    public static final String COL_USER_SENHA = "senha";
    public static final String COL_USER_DATA = "data_cadastro";

    // --- COLUNAS TECNOLOGIA ---
    public static final String COL_TEC_ID = "id";
    public static final String COL_TEC_NOME = "nome";

    // --- COLUNAS CONTEUDO ---
    public static final String COL_CONT_ID = "id";
    public static final String COL_CONT_TEC_ID = "tecnologia_id";
    public static final String COL_CONT_TITULO = "titulo";
    public static final String COL_CONT_DESCRICAO = "descricao";
    public static final String COL_CONT_PROGRESSO = "progresso";

    // --- COLUNAS META ---
    public static final String COL_META_ID = "id";
    public static final String COL_META_USER_ID = "usuario_id";
    public static final String COL_META_TITULO = "titulo";
    public static final String COL_META_DESCRICAO = "descricao";
    public static final String COL_META_HORAS_ALVO = "horas_alvo";
    public static final String COL_META_DATA_LIMITE = "data_limite";
    public static final String COL_META_STATUS = "status";

    // --- COLUNAS SESSAO_ESTUDO ---
    public static final String COL_SESSAO_ID = "id";
    public static final String COL_SESSAO_USER_ID = "usuario_id";
    public static final String COL_SESSAO_CONT_ID = "conteudo_id";
    public static final String COL_SESSAO_DURACAO = "duracao_minutos";
    public static final String COL_SESSAO_DATA = "data_sessao";
    public static final String COL_SESSAO_OBS = "observacoes";

    // --- COLUNAS DESAFIO ---
    public static final String COL_DESAFIO_ID = "id";
    public static final String COL_DESAFIO_USER_ID = "usuario_id";
    public static final String COL_DESAFIO_TEC_ID = "tecnologia_id";
    public static final String COL_DESAFIO_TITULO = "titulo";
    public static final String COL_DESAFIO_DESCRICAO = "descricao";
    public static final String COL_DESAFIO_DIFICULDADE = "nivel_dificuldade";
    public static final String COL_DESAFIO_STATUS = "status";

    // --- COLUNAS ANOTACAO ---
    public static final String COL_ANNOT_ID = "id";
    public static final String COL_ANNOT_USER_ID = "usuario_id";
    public static final String COL_ANNOT_CONT_ID = "conteudo_id";
    public static final String COL_ANNOT_TITULO = "titulo";
    public static final String COL_ANNOT_TEXTO = "texto";
    public static final String COL_ANNOT_DATA = "data_criacao";

    // --- COLUNAS FAVORITO ---
    public static final String COL_FAV_ID = "id";
    public static final String COL_FAV_USER_ID = "usuario_id";
    public static final String COL_FAV_CONT_ID = "conteudo_id";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL CREATE USUARIO
        String createUsuario = "CREATE TABLE " + TABLE_USUARIO + " (" +
                COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_USER_NOME + " TEXT NOT NULL, " +
                COL_USER_EMAIL + " TEXT NOT NULL UNIQUE, " +
                COL_USER_SENHA + " TEXT NOT NULL, " +
                COL_USER_DATA + " TEXT DEFAULT CURRENT_TIMESTAMP)";

        // SQL CREATE TECNOLOGIA
        String createTecnologia = "CREATE TABLE " + TABLE_TECNOLOGIA + " (" +
                COL_TEC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TEC_NOME + " TEXT NOT NULL)";

        // SQL CREATE CONTEUDO
        String createConteudo = "CREATE TABLE " + TABLE_CONTEUDO + " (" +
                COL_CONT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_CONT_TEC_ID + " INTEGER NOT NULL, " +
                COL_CONT_TITULO + " TEXT NOT NULL, " +
                COL_CONT_DESCRICAO + " TEXT, " +
                COL_CONT_PROGRESSO + " INTEGER DEFAULT 0, " +
                "FOREIGN KEY(" + COL_CONT_TEC_ID + ") REFERENCES " + TABLE_TECNOLOGIA + "(" + COL_TEC_ID + ") ON DELETE CASCADE)";

        // SQL CREATE META
        String createMeta = "CREATE TABLE " + TABLE_META + " (" +
                COL_META_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_META_USER_ID + " INTEGER NOT NULL, " +
                COL_META_TITULO + " TEXT NOT NULL, " +
                COL_META_DESCRICAO + " TEXT, " +
                COL_META_HORAS_ALVO + " INTEGER DEFAULT 0, " +
                COL_META_DATA_LIMITE + " TEXT, " +
                COL_META_STATUS + " TEXT DEFAULT 'PENDENTE', " +
                "FOREIGN KEY(" + COL_META_USER_ID + ") REFERENCES " + TABLE_USUARIO + "(" + COL_USER_ID + ") ON DELETE CASCADE)";

        // SQL CREATE SESSAO_ESTUDO
        String createSessao = "CREATE TABLE " + TABLE_SESSAO + " (" +
                COL_SESSAO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_SESSAO_USER_ID + " INTEGER NOT NULL, " +
                COL_SESSAO_CONT_ID + " INTEGER NOT NULL, " +
                COL_SESSAO_DURACAO + " INTEGER NOT NULL, " +
                COL_SESSAO_DATA + " TEXT DEFAULT CURRENT_TIMESTAMP, " +
                COL_SESSAO_OBS + " TEXT, " +
                "FOREIGN KEY(" + COL_SESSAO_USER_ID + ") REFERENCES " + TABLE_USUARIO + "(" + COL_USER_ID + ") ON DELETE CASCADE, " +
                "FOREIGN KEY(" + COL_SESSAO_CONT_ID + ") REFERENCES " + TABLE_CONTEUDO + "(" + COL_CONT_ID + ") ON DELETE CASCADE)";

        // SQL CREATE DESAFIO
        String createDesafio = "CREATE TABLE " + TABLE_DESAFIO + " (" +
                COL_DESAFIO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_DESAFIO_USER_ID + " INTEGER NOT NULL, " +
                COL_DESAFIO_TEC_ID + " INTEGER NOT NULL, " +
                COL_DESAFIO_TITULO + " TEXT NOT NULL, " +
                COL_DESAFIO_DESCRICAO + " TEXT, " +
                COL_DESAFIO_DIFICULDADE + " TEXT, " +
                COL_DESAFIO_STATUS + " TEXT DEFAULT 'PENDENTE', " +
                "FOREIGN KEY(" + COL_DESAFIO_USER_ID + ") REFERENCES " + TABLE_USUARIO + "(" + COL_USER_ID + ") ON DELETE CASCADE, " +
                "FOREIGN KEY(" + COL_DESAFIO_TEC_ID + ") REFERENCES " + TABLE_TECNOLOGIA + "(" + COL_TEC_ID + ") ON DELETE CASCADE)";

        // SQL CREATE ANOTACAO
        String createAnotacao = "CREATE TABLE " + TABLE_ANOTACAO + " (" +
                COL_ANNOT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_ANNOT_USER_ID + " INTEGER NOT NULL, " +
                COL_ANNOT_CONT_ID + " INTEGER NOT NULL, " +
                COL_ANNOT_TITULO + " TEXT NOT NULL, " +
                COL_ANNOT_TEXTO + " TEXT NOT NULL, " +
                COL_ANNOT_DATA + " TEXT DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY(" + COL_ANNOT_USER_ID + ") REFERENCES " + TABLE_USUARIO + "(" + COL_USER_ID + ") ON DELETE CASCADE, " +
                "FOREIGN KEY(" + COL_ANNOT_CONT_ID + ") REFERENCES " + TABLE_CONTEUDO + "(" + COL_CONT_ID + ") ON DELETE CASCADE)";

        // SQL CREATE FAVORITO
        String createFavorito = "CREATE TABLE " + TABLE_FAVORITO + " (" +
                COL_FAV_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_FAV_USER_ID + " INTEGER NOT NULL, " +
                COL_FAV_CONT_ID + " INTEGER NOT NULL, " +
                "FOREIGN KEY(" + COL_FAV_USER_ID + ") REFERENCES " + TABLE_USUARIO + "(" + COL_USER_ID + ") ON DELETE CASCADE, " +
                "FOREIGN KEY(" + COL_FAV_CONT_ID + ") REFERENCES " + TABLE_CONTEUDO + "(" + COL_CONT_ID + ") ON DELETE CASCADE)";

        db.execSQL(createUsuario);
        db.execSQL(createTecnologia);
        db.execSQL(createConteudo);
        db.execSQL(createMeta);
        db.execSQL(createSessao);
        db.execSQL(createDesafio);
        db.execSQL(createAnotacao);
        db.execSQL(createFavorito);

        // Inserir dados iniciais padronizados para o MVP
        db.execSQL("INSERT INTO " + TABLE_USUARIO + " (" + COL_USER_NOME + ", " + COL_USER_EMAIL + ", " + COL_USER_SENHA + ") VALUES ('Estudante Dev', 'estudante@studyflow.com', '123456')");
        db.execSQL("INSERT INTO " + TABLE_TECNOLOGIA + " (" + COL_TEC_NOME + ") VALUES ('Java')");
        db.execSQL("INSERT INTO " + TABLE_TECNOLOGIA + " (" + COL_TEC_NOME + ") VALUES ('Android Nativo')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANOTACAO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DESAFIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SESSAO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_META);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTEUDO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TECNOLOGIA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIO);
        onCreate(db);
    }

    // ==========================================
    // --- CRUD USUARIO ---
    // ==========================================

    public long insertUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_USER_NOME, usuario.getNome());
        values.put(COL_USER_EMAIL, usuario.getEmail());
        values.put(COL_USER_SENHA, usuario.getSenha());
        long id = db.insert(TABLE_USUARIO, null, values);
        db.close();
        return id;
    }

    public Usuario getUsuarioByEmail(String email) {
        Usuario usuario = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USUARIO, null, COL_USER_EMAIL + " = ?",
                new String[]{email}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(COL_USER_ID));
            String nome = cursor.getString(cursor.getColumnIndexOrThrow(COL_USER_NOME));
            String userEmail = cursor.getString(cursor.getColumnIndexOrThrow(COL_USER_EMAIL));
            String senha = cursor.getString(cursor.getColumnIndexOrThrow(COL_USER_SENHA));
            String data = cursor.getString(cursor.getColumnIndexOrThrow(COL_USER_DATA));

            usuario = new Usuario(id, nome, userEmail, senha, data);
            cursor.close();
        }
        db.close();
        return usuario;
    }

    public Usuario getUsuarioById(long usuarioId) {
        Usuario usuario = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USUARIO, null, COL_USER_ID + " = ?",
                new String[]{String.valueOf(usuarioId)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(COL_USER_ID));
            String nome = cursor.getString(cursor.getColumnIndexOrThrow(COL_USER_NOME));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(COL_USER_EMAIL));
            String senha = cursor.getString(cursor.getColumnIndexOrThrow(COL_USER_SENHA));
            String data = cursor.getString(cursor.getColumnIndexOrThrow(COL_USER_DATA));

            usuario = new Usuario(id, nome, email, senha, data);
            cursor.close();
        }
        db.close();
        return usuario;
    }

    public int updateUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_USER_NOME, usuario.getNome());
        values.put(COL_USER_EMAIL, usuario.getEmail());
        values.put(COL_USER_SENHA, usuario.getSenha());
        int rows = db.update(TABLE_USUARIO, values, COL_USER_ID + " = ?", new String[]{String.valueOf(usuario.getId())});
        db.close();
        return rows;
    }

    public int deleteUsuario(long usuarioId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE_USUARIO, COL_USER_ID + " = ?", new String[]{String.valueOf(usuarioId)});
        db.close();
        return rows;
    }

    // ==========================================
    // --- CRUD TECNOLOGIA ---
    // ==========================================

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

    public int deleteTecnologia(long tecnologiaId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE_TECNOLOGIA, COL_TEC_ID + " = ?", new String[]{String.valueOf(tecnologiaId)});
        db.close();
        return rows;
    }

    // ==========================================
    // --- CRUD CONTEUDO ---
    // ==========================================

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

    public int deleteConteudo(long conteudoId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE_CONTEUDO, COL_CONT_ID + " = ?", new String[]{String.valueOf(conteudoId)});
        db.close();
        return rows;
    }

    // ==========================================
    // --- CRUD META ---
    // ==========================================

    public long insertMeta(Meta meta) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_META_USER_ID, meta.getUsuarioId());
        values.put(COL_META_TITULO, meta.getTitulo());
        values.put(COL_META_DESCRICAO, meta.getDescricao());
        values.put(COL_META_HORAS_ALVO, meta.getHorasAlvo());
        values.put(COL_META_DATA_LIMITE, meta.getDataLimite());
        values.put(COL_META_STATUS, meta.getStatus());
        long id = db.insert(TABLE_META, null, values);
        db.close();
        return id;
    }

    public List<Meta> getMetasPorUsuario(long usuarioId) {
        List<Meta> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_META, null, COL_META_USER_ID + " = ?",
                new String[]{String.valueOf(usuarioId)}, null, null, COL_META_ID + " DESC");

        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COL_META_ID));
                String titulo = cursor.getString(cursor.getColumnIndexOrThrow(COL_META_TITULO));
                String descricao = cursor.getString(cursor.getColumnIndexOrThrow(COL_META_DESCRICAO));
                int horas = cursor.getInt(cursor.getColumnIndexOrThrow(COL_META_HORAS_ALVO));
                String data = cursor.getString(cursor.getColumnIndexOrThrow(COL_META_DATA_LIMITE));
                String status = cursor.getString(cursor.getColumnIndexOrThrow(COL_META_STATUS));

                lista.add(new Meta(id, usuarioId, titulo, descricao, horas, data, status));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lista;
    }

    public int updateMetaStatus(long metaId, String novoStatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_META_STATUS, novoStatus);
        int rows = db.update(TABLE_META, values, COL_META_ID + " = ?", new String[]{String.valueOf(metaId)});
        db.close();
        return rows;
    }

    public int deleteMeta(long metaId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE_META, COL_META_ID + " = ?", new String[]{String.valueOf(metaId)});
        db.close();
        return rows;
    }

    // ==========================================
    // --- CRUD SESSAO_ESTUDO ---
    // ==========================================

    public long insertSessaoEstudo(SessaoEstudo sessao) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_SESSAO_USER_ID, sessao.getUsuarioId());
        values.put(COL_SESSAO_CONT_ID, sessao.getConteudoId());
        values.put(COL_SESSAO_DURACAO, sessao.getDuracaoMinutos());
        values.put(COL_SESSAO_OBS, sessao.getObservacoes());
        long id = db.insert(TABLE_SESSAO, null, values);
        db.close();
        return id;
    }

    public List<SessaoEstudo> getSessoesPorUsuario(long usuarioId) {
        List<SessaoEstudo> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_SESSAO, null, COL_SESSAO_USER_ID + " = ?",
                new String[]{String.valueOf(usuarioId)}, null, null, COL_SESSAO_ID + " DESC");

        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COL_SESSAO_ID));
                long conteudoId = cursor.getLong(cursor.getColumnIndexOrThrow(COL_SESSAO_CONT_ID));
                int duracao = cursor.getInt(cursor.getColumnIndexOrThrow(COL_SESSAO_DURACAO));
                String data = cursor.getString(cursor.getColumnIndexOrThrow(COL_SESSAO_DATA));
                String obs = cursor.getString(cursor.getColumnIndexOrThrow(COL_SESSAO_OBS));

                lista.add(new SessaoEstudo(id, usuarioId, conteudoId, duracao, data, obs));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lista;
    }

    public int getTotalMinutosEstudadosPorUsuario(long usuarioId) {
        int total = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + COL_SESSAO_DURACAO + ") FROM " + TABLE_SESSAO +
                " WHERE " + COL_SESSAO_USER_ID + " = ?", new String[]{String.valueOf(usuarioId)});

        if (cursor.moveToFirst()) {
            total = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return total;
    }

    public int deleteSessaoEstudo(long sessaoId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE_SESSAO, COL_SESSAO_ID + " = ?", new String[]{String.valueOf(sessaoId)});
        db.close();
        return rows;
    }

    // ==========================================
    // --- CRUD DESAFIO ---
    // ==========================================

    public long insertDesafio(Desafio desafio) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_DESAFIO_USER_ID, desafio.getUsuarioId());
        values.put(COL_DESAFIO_TEC_ID, desafio.getTecnologiaId());
        values.put(COL_DESAFIO_TITULO, desafio.getTitulo());
        values.put(COL_DESAFIO_DESCRICAO, desafio.getDescricao());
        values.put(COL_DESAFIO_DIFICULDADE, desafio.getNivelDificuldade());
        values.put(COL_DESAFIO_STATUS, desafio.getStatus());
        long id = db.insert(TABLE_DESAFIO, null, values);
        db.close();
        return id;
    }

    public List<Desafio> getDesafiosPorUsuarioETecnologia(long usuarioId, long tecnologiaId) {
        List<Desafio> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DESAFIO, null,
                COL_DESAFIO_USER_ID + " = ? AND " + COL_DESAFIO_TEC_ID + " = ?",
                new String[]{String.valueOf(usuarioId), String.valueOf(tecnologiaId)},
                null, null, COL_DESAFIO_ID + " DESC");

        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COL_DESAFIO_ID));
                String titulo = cursor.getString(cursor.getColumnIndexOrThrow(COL_DESAFIO_TITULO));
                String desc = cursor.getString(cursor.getColumnIndexOrThrow(COL_DESAFIO_DESCRICAO));
                String dif = cursor.getString(cursor.getColumnIndexOrThrow(COL_DESAFIO_DIFICULDADE));
                String status = cursor.getString(cursor.getColumnIndexOrThrow(COL_DESAFIO_STATUS));

                lista.add(new Desafio(id, usuarioId, tecnologiaId, titulo, desc, dif, status));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lista;
    }

    public int updateStatusDesafio(long desafioId, String novoStatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_DESAFIO_STATUS, novoStatus);
        int rows = db.update(TABLE_DESAFIO, values, COL_DESAFIO_ID + " = ?", new String[]{String.valueOf(desafioId)});
        db.close();
        return rows;
    }

    public int deleteDesafio(long desafioId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE_DESAFIO, COL_DESAFIO_ID + " = ?", new String[]{String.valueOf(desafioId)});
        db.close();
        return rows;
    }

    // ==========================================
    // --- CRUD ANOTACAO ---
    // ==========================================

    public long insertAnotacao(Anotacao anotacao) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_ANNOT_USER_ID, anotacao.getUsuarioId());
        values.put(COL_ANNOT_CONT_ID, anotacao.getConteudoId());
        values.put(COL_ANNOT_TITULO, anotacao.getTitulo());
        values.put(COL_ANNOT_TEXTO, anotacao.getTexto());
        long id = db.insert(TABLE_ANOTACAO, null, values);
        db.close();
        return id;
    }

    public List<Anotacao> getAnotacoesPorConteudo(long conteudoId) {
        List<Anotacao> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ANOTACAO, null, COL_ANNOT_CONT_ID + " = ?",
                new String[]{String.valueOf(conteudoId)}, null, null, COL_ANNOT_ID + " DESC");

        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COL_ANNOT_ID));
                long usuarioId = cursor.getLong(cursor.getColumnIndexOrThrow(COL_ANNOT_USER_ID));
                String titulo = cursor.getString(cursor.getColumnIndexOrThrow(COL_ANNOT_TITULO));
                String texto = cursor.getString(cursor.getColumnIndexOrThrow(COL_ANNOT_TEXTO));
                String data = cursor.getString(cursor.getColumnIndexOrThrow(COL_ANNOT_DATA));

                lista.add(new Anotacao(id, usuarioId, conteudoId, titulo, texto, data));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lista;
    }

    public int updateAnotacao(Anotacao anotacao) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_ANNOT_TITULO, anotacao.getTitulo());
        values.put(COL_ANNOT_TEXTO, anotacao.getTexto());
        int rows = db.update(TABLE_ANOTACAO, values, COL_ANNOT_ID + " = ?", new String[]{String.valueOf(anotacao.getId())});
        db.close();
        return rows;
    }

    public int deleteAnotacao(long anotacaoId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE_ANOTACAO, COL_ANNOT_ID + " = ?", new String[]{String.valueOf(anotacaoId)});
        db.close();
        return rows;
    }

    // ==========================================
    // --- CRUD FAVORITO ---
    // ==========================================

    public long addFavorito(long usuarioId, long conteudoId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FAV_USER_ID, usuarioId);
        values.put(COL_FAV_CONT_ID, conteudoId);
        long id = db.insert(TABLE_FAVORITO, null, values);
        db.close();
        return id;
    }

    public boolean isFavorito(long usuarioId, long conteudoId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_FAVORITO, null,
                COL_FAV_USER_ID + " = ? AND " + COL_FAV_CONT_ID + " = ?",
                new String[]{String.valueOf(usuarioId), String.valueOf(conteudoId)},
                null, null, null);

        boolean fav = (cursor != null && cursor.getCount() > 0);
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return fav;
    }

    public int removeFavorito(long usuarioId, long conteudoId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE_FAVORITO,
                COL_FAV_USER_ID + " = ? AND " + COL_FAV_CONT_ID + " = ?",
                new String[]{String.valueOf(usuarioId), String.valueOf(conteudoId)});
        db.close();
        return rows;
    }
}
