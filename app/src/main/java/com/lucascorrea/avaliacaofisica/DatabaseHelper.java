package com.lucascorrea.avaliacaofisica;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "avaliacaofisica.db";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createAlunosTableQuery = "CREATE TABLE alunos (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, altura REAL, peso REAL)";
        db.execSQL(createAlunosTableQuery);

        String createIndicesTableQuery = "CREATE TABLE indices (id INTEGER PRIMARY KEY AUTOINCREMENT, aluno_id INTEGER, data TEXT, indiceBf REAL, FOREIGN KEY(aluno_id) REFERENCES alunos (id))";
        db.execSQL(createIndicesTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
