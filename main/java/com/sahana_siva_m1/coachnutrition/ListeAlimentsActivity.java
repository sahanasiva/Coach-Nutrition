package com.sahana_siva_m1.coachnutrition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ListeAlimentsActivity extends AppCompatActivity {

    private CoachNutContentResolver contentResolver;
    private EditText ed_aliment, ed_calories;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_aliments);
        init();
    }

    private void init() {
        contentResolver = new CoachNutContentResolver(this);
        ed_aliment = (EditText) findViewById(R.id.ed_aliment);
        ed_calories = (EditText) findViewById(R.id.ed_calories);
    }

    public void ajouter(View b_ajouter) {
        String aliment = ed_aliment.getText().toString().trim(), calories = ed_calories.getText().toString().trim();
        if (!aliment.equalsIgnoreCase("") && !calories.equalsIgnoreCase("")){
            boolean ajouterAliments = contentResolver.ajouterAliments(aliment.toLowerCase(), Integer.parseInt(calories));
            if (ajouterAliments){
                clear();
            }else{
                clear();
                toast = new Toast(this, "Veuillez réessayer!");
            }
        }
    }

    private void clear() {
        ed_aliment.getText().clear();
        ed_calories.getText().clear();
        ed_aliment.requestFocus();
    }
}
