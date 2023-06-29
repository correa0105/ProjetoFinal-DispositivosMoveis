package com.lucascorrea.avaliacaofisica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnAddAluno;
    private Button btnVerAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddAluno = findViewById(R.id.btnAddAluno);
        btnVerAlunos = findViewById(R.id.btnVerAlunos);

        btnAddAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AlunoActivity.class);
                startActivity(i);
            }
        });

        btnVerAlunos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AlunosListaActivity.class);
                startActivity(i);
            }
        });
    }
}