package com.empresa.lucas.relatorios.Calendario;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import com.empresa.lucas.relatorios.MainActivity;
import com.empresa.lucas.relatorios.R;

public class Calendar extends AppCompatActivity {

public static String date;
    CalendarView calendar;
    TextView date_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);

        calendar = (CalendarView)
                findViewById(R.id.calendar);
        date_view = (TextView)
                findViewById(R.id.date_view);

        calendar
                .setOnDateChangeListener(
                        new CalendarView
                                .OnDateChangeListener() {

                            @Override

                            public void onSelectedDayChange(
                                    @NonNull CalendarView view,
                                    int year,
                                    int month,
                                    int dayOfMonth)
                            {

                                date = Integer.toString(year) + "-" + String.format("%02d", month + 1) + "-" + String.format("%02d", dayOfMonth);

                                date_view.setText(date);
                            }
                        });



        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }});
    };
}
