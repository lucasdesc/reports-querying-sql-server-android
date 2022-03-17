package com.empresa.lucas.relatorios.Relatorios;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.SimpleAdapter;


import com.empresa.lucas.relatorios.MainActivity;
import com.empresa.lucas.relatorios.BancoSQL.ConSQL;
import com.empresa.lucas.relatorios.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.empresa.lucas.relatorios.MainActivity.ValorData;
import static com.empresa.lucas.relatorios.MainActivity.ValorData2;
import static com.empresa.lucas.relatorios.MainActivity.maq;


public class RelatorioIP extends AppCompatActivity {

    Connection connection;
    SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorioip);

        btnload(new View(this)); }

    public void voltar(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void btnload(View v){
        final GridView list = (GridView) findViewById(R.id.gridview1);
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        try {
            ConSQL c= new ConSQL();
            connection = c.conclass();
            if (connection != null){
                String query = "select w.C_APEL, dc.IP, dc.EVS_START from TABLE1 w inner join TABLE2 dc on dc.MCH_CODE = w.C_MAQ where C_APEL = '" + maq + "'  and EVS_START between '" + ValorData + " 00:00:00.000' and '" + ValorData2 + " 23:59:59.000' order by EVS_START desc";

                Statement st = connection.createStatement();

                ResultSet resultSet= st.executeQuery(query);

                while (resultSet.next()){
                    Map<String,String> tab= new HashMap<String, String>();
                    tab.put("C_APEL", resultSet.getString("C_APEL"));
                    tab.put("IP", resultSet.getString("IP"));
                    tab.put("EVS_START", resultSet.getString("EVS_START"));
                    data.add(tab);

                }
                String[] from = {"C_APEL", "IP", "EVS_START"};
                int[] to={R.id.C_APEL, R.id.IP, R.id.EVS_START};
                adapter = new SimpleAdapter(RelatorioIP.this, data, R.layout.gridviewlayout, from, to);
                list.setAdapter(adapter);


            }

        }
        catch (Exception e){

        }
    }

}
