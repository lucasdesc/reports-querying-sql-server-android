package com.empresa.lucas.relatorios;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.empresa.lucas.relatorios.Calendario.Calendar;
import com.empresa.lucas.relatorios.Calendario.Calendar2;
import com.empresa.lucas.relatorios.Relatorios.RelatorioIP;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.empresa.lucas.relatorios.Calendario.Calendar.date;
import static com.empresa.lucas.relatorios.Calendario.Calendar2.date2;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static String ValorData;
    public static String ValorData2;
    TextView Resultado;
    TextView Resultado2;
    public static String maq;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resultado = (TextView) findViewById(R.id.textView3);
        Resultado2 = (TextView) findViewById(R.id.textView6);
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);

        Resultado.setText(date);
        Resultado2.setText(date2);

        ValorData = Resultado.getText().toString();
        ValorData2 = Resultado2.getText().toString();


        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (date == null || date2 == null){
                    Toast.makeText(getApplicationContext(), "PREENCHA TODAS AS DATAS", Toast.LENGTH_SHORT).show();
                }
                else if (date.compareTo(date2) > 0){
                    Toast.makeText(getApplicationContext(), "DATA INÍCIO É MAIOR QUE DATA FIM", Toast.LENGTH_SHORT).show();
                }
                else if (date.compareTo(getDateTime()) > 0 || date2.compareTo(getDateTime()) > 0){
                    Toast.makeText(getApplicationContext(), "DATA INÍCIO OU FIM É FUTURA", Toast.LENGTH_SHORT).show();
                }
                else {
                Intent intent = new Intent(getApplicationContext(), RelatorioIP.class);
                startActivity(intent);}
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Calendar.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Calendar2.class);
                startActivity(intent);
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.numbers, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }
    private String getDateTime() {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        maq = parent.getItemAtPosition(position).toString();
        ((TextView) parent.getChildAt(0)).setTextSize(20);
        ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
