package com.example.french;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner mySpinner;
    ConstraintLayout colorLayout,numberLayout,daysLayout;
//    ImageView yellow;
//    TextView jaune;
//    ImageView red;
//    TextView rouge;
//    ImageView black;
//    TextView noir;
//    ImageView green;
//    TextView vert;
//    ImageView blue;
//    TextView bleu;
//
//    ImageView one;
//    ImageView two;
//    ImageView three;
//    ImageView four;
//    ImageView five;
//    ImageView six;
//    ImageView seven;
//    ImageView eight;
//    ImageView nine;
//    ImageView ten;
//
//    TextView un;
//    TextView deux;
//    TextView trois;
//    TextView quatre;
//    TextView cinq;
//    TextView sixx;
//    TextView sept;
//    TextView huit;
//    TextView neuf;
//    TextView dix;
//
//    ImageView monday;
//    ImageView tuesday;
//    ImageView wednsday;
//    ImageView thursday;
//    ImageView friday;
//    ImageView saturday;
//    ImageView sunday;
//
//    TextView lundi;
//    TextView mardi;
//    TextView mercredi;
//    TextView jeudi;
//    TextView vendredi;
//    TextView samedi;
//    TextView dimanche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySpinner = findViewById(R.id.my_spinner);
        colorLayout = findViewById(R.id.colorsLayout);
        numberLayout = findViewById(R.id.numbersLayout);
        daysLayout = findViewById(R.id.daysLayout);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, new String[]{"Colors in french", "Numbers in french", "Days in french"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);
        mySpinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        switch (position) {
            case 0: // First item selected
                colorLayout.setVisibility(View.VISIBLE);
                numberLayout.setVisibility(View.GONE);
                daysLayout.setVisibility(View.GONE);
                break;
            case 1: // Second item selected
                colorLayout.setVisibility(View.GONE);
                numberLayout.setVisibility(View.VISIBLE);
                daysLayout.setVisibility(View.GONE);
                break;
            case 2: // Third item selected
                colorLayout.setVisibility(View.GONE);
                numberLayout.setVisibility(View.GONE);
                daysLayout.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Do something when nothing is selected
    }
}
