package com.example.telasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Collections;
import java.util.Random;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private EditText inicioInterv;
    private EditText fimInterv;
    private EditText quantidade;
    private Button sorteaBtn;
    private EditText valoresSorteados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicioInterv = (EditText) findViewById(R.id.inicioTxt);
        fimInterv = (EditText) findViewById(R.id.fimTxt);
        quantidade = (EditText) findViewById(R.id.quantidadeTxt);
        sorteaBtn = (Button) findViewById(R.id.sortearBtn);
        valoresSorteados = (EditText) findViewById(R.id.valoresTxt);

        cadastraEventos();
    }
    private void cadastraEventos(){
        sorteaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!inicioInterv.getText().toString().isEmpty() && !fimInterv.getText().toString().isEmpty() && !quantidade.getText().toString().isEmpty()) {
                    //leitura dos valores informados pelo usuário
                    int inicio = Integer.parseInt(inicioInterv.getText().toString());
                    int fim = Integer.parseInt(fimInterv.getText().toString());
                    int quant = Integer.parseInt(quantidade.getText().toString());

                    //armazena os valores sorteados
                    Vector<Integer> valores = new Vector<>();
                    Random gerador = new Random();

                    //sorteando valores no intervalo específicado pelo usuário
                    while (valores.size() < quant) {
                        int sorteado = gerador.nextInt(fim - inicio + 1) + inicio;

                        //verifica se já possui o número sorteado
                        if (!valores.contains(sorteado)) {
                            valores.add(sorteado);
                        }
                    }

                    //ordenar os elementos em ordem crescente
                    Collections.sort(valores);

                    //converte os valores em uma String única
                    String resultado = "";
                    for (int i = 0; i < valores.size(); i++) {
                        if(valores.get(i) < 10){
                            resultado += "0";
                        }
                        resultado += valores.get(i) + " ";
                    }
                    valoresSorteados.setText(resultado);
                }else{
                    Toast.makeText(MainActivity.this, "É preciso preencher todos os campos", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}