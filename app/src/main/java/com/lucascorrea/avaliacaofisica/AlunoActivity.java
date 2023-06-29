package com.lucascorrea.avaliacaofisica;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AlunoActivity extends AppCompatActivity {

    private EditText editNome;
    private EditText editAltura;
    private EditText editPeso;
    private Button btnSalvarAluno;
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);

        editNome = findViewById(R.id.editNome);
        editAltura = findViewById(R.id.editAltura);
        editPeso = findViewById(R.id.editPeso);
        btnSalvarAluno = findViewById(R.id.btnSalvarAluno);

        btnSalvarAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarAluno();
            }
        });
    }

    private void salvarAluno() {
        String nome = editNome.getText().toString();
        String alturaText = editAltura.getText().toString();
        String pesoText = editPeso.getText().toString();

        alturaText = alturaText.replace(",", ".");
        pesoText = pesoText.replace(",", ".");

        double altura = Double.parseDouble(alturaText);
        double peso = Double.parseDouble(pesoText);

        aluno = new Aluno(nome, altura, peso);

        AlunoDAO.salvarAlunoBanco(this, aluno);

        editNome.setText("");
        editAltura.setText("");
        editPeso.setText("");
        aluno = null;
    }
}
