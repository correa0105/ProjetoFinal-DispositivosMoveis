package com.lucascorrea.avaliacaofisica;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroDobrasActivity extends AppCompatActivity {

    private TextView textNome;
    private Button btnSalvarDobras;
    private Aluno aluno;
    private IndiceDeGordura indiceDeGordura;
    private EditText editData;
    private EditText editDobraTriceps;
    private EditText editDobraSubescapular;
    private EditText editDobraSuprailica;
    private EditText editDobraAbdominal;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_dobras);
        aluno = (Aluno) getIntent().getSerializableExtra("aluno");

        textNome = findViewById(R.id.textNome);
        editData= findViewById(R.id.editData);
        editDobraTriceps= findViewById(R.id.editDobraTriceps);
        editDobraSubescapular= findViewById(R.id.editDobraSubescapular);
        editDobraSuprailica= findViewById(R.id.editDobraSuprailica);
        editDobraAbdominal= findViewById(R.id.editDobraAbdominal);
        btnSalvarDobras = findViewById(R.id.bntSalvarDobras);

        textNome.setText(aluno.getNome());

        btnSalvarDobras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarDobras();
            }
        });
    }

    private void salvarDobras() {
        String data = editData.getText().toString();
        String tricepsText = editDobraTriceps.getText().toString();
        String subescapularText = editDobraSubescapular.getText().toString();
        String suprailicaText = editDobraSuprailica.getText().toString();
        String abdominalText = editDobraAbdominal.getText().toString();

        tricepsText = tricepsText.replace(",", ".");
        subescapularText = subescapularText.replace(",", ".");
        suprailicaText = suprailicaText.replace(",", ".");
        abdominalText = abdominalText.replace(",", ".");

        double dobraTriceps = Double.parseDouble(tricepsText);
        double dobraSubescapular = Double.parseDouble(subescapularText);
        double dobraSuprailica = Double.parseDouble(suprailicaText);
        double dobraAbdominal = Double.parseDouble(abdominalText);

        indiceDeGordura = new IndiceDeGordura(aluno, data, dobraTriceps, dobraSubescapular, dobraSuprailica, dobraAbdominal);

        IndiceDeGorduraDAO.salvarIndiceBanco(this, aluno, indiceDeGordura);

        editData.setText("");
        editDobraTriceps.setText("");
        editDobraSubescapular.setText("");
        editDobraSuprailica.setText("");
        editDobraAbdominal.setText("");
        indiceDeGordura = null;
    }

}
