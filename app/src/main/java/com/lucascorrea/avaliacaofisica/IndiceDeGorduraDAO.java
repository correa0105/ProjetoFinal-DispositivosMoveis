package com.lucascorrea.avaliacaofisica;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class IndiceDeGorduraDAO {

    public static void salvarIndiceBanco(Context context, Aluno aluno, IndiceDeGordura indiceDeGordura) {
        SQLiteDatabase db = new DatabaseHelper(context).getWritableDatabase();

        ContentValues valoresDB = new ContentValues();

        valoresDB.put("aluno_id", aluno.getId());
        valoresDB.put("data", indiceDeGordura.getData());
        valoresDB.put("indiceBf", indiceDeGordura.calcularIndiceGorduraCorporal());

        db.insert("indices", null, valoresDB);
    }

    public static List<IndiceDeGordura> carregarIndices(Context context, Aluno aluno) {
        SQLiteDatabase db =  new DatabaseHelper(context).getReadableDatabase();

        List<IndiceDeGordura> listaDeIndices = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT id, data, indiceBf FROM indices WHERE aluno_id = " + aluno.getId() + " ORDER BY data", null);

        if(cursor !=  null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                IndiceDeGordura indiceDeGordura = new IndiceDeGordura(aluno, cursor.getString(1), cursor.getDouble(2));
                indiceDeGordura.setId(cursor.getInt(0));
                listaDeIndices.add(indiceDeGordura);
            } while (cursor.moveToNext());
        }

        return listaDeIndices;
    }

}
