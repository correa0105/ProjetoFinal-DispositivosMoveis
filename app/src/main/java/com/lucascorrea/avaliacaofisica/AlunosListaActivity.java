package com.lucascorrea.avaliacaofisica;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class AlunosListaActivity extends AppCompatActivity {

    private ListView listViewAlunos;
    private List<Aluno> listaDeAlunos;
    private ArrayAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_alunos);

        listViewAlunos = findViewById(R.id.listViewAlunos);

        listViewAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Aluno aluno = listaDeAlunos.get(i);

                Intent intent = new Intent(AlunosListaActivity.this, AlunoPerfilActivity.class);
                intent.putExtra("aluno", aluno);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        carregarAlunos();
    }

    private void carregarAlunos() {
        listaDeAlunos = AlunoDAO.carregarAlunos(this);

        if (listaDeAlunos.isEmpty()) {
            listViewAlunos.setEnabled(false);
            String[] emptyList = {"Não possui alunos cadastrados em sua carteira"};
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, emptyList);
        } else {
            listViewAlunos.setEnabled(true);
            List<String> alunoNomes = new ArrayList<>();
            for (Aluno aluno : listaDeAlunos) {
                alunoNomes.add(aluno.getNome());
            }
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, alunoNomes);
        }

        listViewAlunos.setAdapter(adapter);
    }

}
