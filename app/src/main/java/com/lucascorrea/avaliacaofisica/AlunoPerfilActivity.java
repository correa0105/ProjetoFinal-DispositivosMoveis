package com.lucascorrea.avaliacaofisica;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AlunoPerfilActivity extends AppCompatActivity {

    private TextView textNome;
    private TextView textAltura;
    private TextView textPeso;
    private TextView textPesoMagro;
    private TextView textPesoGordo;
    private ListView listViewIndices;
    private EditText editNovoPeso;
    private Button btnSalvarAluno;
    private List<IndiceDeGordura> bfAtualDaLista;
    private List<IndiceDeGordura> listaDeIndices;
    private ArrayAdapter adapter;
    private Button btnCadastrarDobras;
    private Aluno aluno;
    private Button bntEditPeso;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno_perfil);
        aluno = (Aluno) getIntent().getSerializableExtra("aluno");

        textNome = findViewById(R.id.textNome);
        textAltura = findViewById(R.id.textAltura);
        textPeso = findViewById(R.id.textPeso);
        textPesoGordo = findViewById(R.id.textPesoGordo);
        textPesoMagro = findViewById(R.id.textPesoMagro);
        bntEditPeso = findViewById(R.id.bntEditPeso);

        listViewIndices = findViewById(R.id.listViewIndices);
        btnCadastrarDobras = findViewById(R.id.btnCadastrarDobras);

        textNome.setText(aluno.getNome());
        textAltura.setText(String.valueOf(aluno.getAltura()) + "m");
        textPeso.setText(String.valueOf(aluno.getPeso()) + "kg");

        btnCadastrarDobras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlunoPerfilActivity.this, CadastroDobrasActivity.class);
                intent.putExtra("aluno", aluno);
                startActivity(intent);
            }
        });

        bntEditPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exibirPopup();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        carregarIndices();
        calcularPesoGordo();
        calcularPesoMagro();
    }

    private void exibirPopup() {
        View popupView = getLayoutInflater().inflate(R.layout.activity_popup_aluno, null);

        editNovoPeso = popupView.findViewById(R.id.editNovoPeso);
        btnSalvarAluno = popupView.findViewById(R.id.btnSalvarAluno);

        Dialog dialog = new Dialog(this);
        dialog.setContentView(popupView);

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.gravity = Gravity.CENTER;

        dialog.show();
        dialog.getWindow().setAttributes(layoutParams);

        btnSalvarAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double novoPeso = Double.parseDouble(editNovoPeso.getText().toString());
                Toast.makeText(AlunoPerfilActivity.this, "Novo peso: " + novoPeso, Toast.LENGTH_SHORT).show();

                AlunoDAO.editarPesoAluno(AlunoPerfilActivity.this, aluno, novoPeso);

                dialog.dismiss();
                aluno.setPeso(novoPeso);
                textPeso.setText(String.valueOf(aluno.getPeso()) + "kg");

                calcularPesoGordo();
                calcularPesoMagro();
            }
        });
    }

    private void calcularPesoGordo() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        double pesoAtual = aluno.getPeso();
        double bfAtual = retornaBfAtual();
        double pesoGordo = (pesoAtual * bfAtual)/100;
        String pesoGordoFormatado = decimalFormat.format(pesoGordo);
        textPesoGordo.setText("Você possui " + pesoGordoFormatado + "kg de PESO GORDO");
    }

    private void calcularPesoMagro() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        double pesoAtual = aluno.getPeso();
        double bfAtual = retornaBfAtual();
        double pesoGordo = (pesoAtual * bfAtual)/100;
        double pesoMagro = pesoAtual - pesoGordo;

        String pesoMagroFormatado = decimalFormat.format(pesoMagro);
        textPesoMagro.setText("Você possui " + pesoMagroFormatado + "kg de PESO MAGRO");
    }

    private double retornaBfAtual () {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        bfAtualDaLista = IndiceDeGorduraDAO.carregarIndices(this, aluno);

        double bfAtual = 0.0;

        if (bfAtualDaLista.isEmpty()) {
        } else {
            IndiceDeGordura ultimoIndice = listaDeIndices.get(listaDeIndices.size() - 1);
            String ultimoIndiceFormatado = decimalFormat.format(ultimoIndice.getIndiceDeGorduraCorporal());
            bfAtual = Double.parseDouble(ultimoIndiceFormatado);
        }
        return bfAtual;
    }

    private void carregarIndices() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        listaDeIndices = IndiceDeGorduraDAO.carregarIndices(this, aluno);

        if (listaDeIndices.isEmpty()) {
            listViewIndices.setEnabled(false);
            String[] emptyList = {"Não possui registros de avaliação fisica"};
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, emptyList);
        } else {
            listViewIndices.setEnabled(true);
            List<String> indiceEData = new ArrayList<>();
            for (IndiceDeGordura indiceDeGordura : listaDeIndices) {
                String formattedIndice = decimalFormat.format(indiceDeGordura.getIndiceDeGorduraCorporal());
                indiceEData.add(formattedIndice + "%" + " - " + indiceDeGordura.getData());
            }
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, indiceEData);
        }

        listViewIndices.setAdapter(adapter);
    }

}
