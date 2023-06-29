package com.lucascorrea.avaliacaofisica;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public static void salvarAlunoBanco(Context context, Aluno aluno) {
        SQLiteDatabase db = new DatabaseHelper(context).getWritableDatabase();

        ContentValues valoresDB = new ContentValues();

        valoresDB.put("nome", aluno.getNome());
        valoresDB.put("altura", aluno.getAltura());
        valoresDB.put("peso", aluno.getPeso());

        db.insert("alunos", null, valoresDB);
    }

    public static void editarPesoAluno(Context context, Aluno aluno, double peso){
        SQLiteDatabase db = new DatabaseHelper(context).getWritableDatabase();

        ContentValues valoresDB = new ContentValues();
        valoresDB.put("peso", peso);

        db.update("alunos", valoresDB, " id = " + aluno.getId(), null  );
    }

    public static List<Aluno> carregarAlunos(Context context) {
        SQLiteDatabase db =  new DatabaseHelper(context).getReadableDatabase();

        List<Aluno> listaDeAlunos = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT id, nome, altura, peso FROM alunos ORDER BY id", null);

        if(cursor !=  null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Aluno aluno = new Aluno(cursor.getString(1), cursor.getDouble(2 ), cursor.getDouble(3));
                aluno.setId(cursor.getInt(0));
                listaDeAlunos.add(aluno);
            } while (cursor.moveToNext());
        }

        return listaDeAlunos;
    }

}
