package com.sahana_siva_m1.coachnutrition;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class ObjectifActivity extends AppCompatActivity {

    private Toast message;
    EditText obj_min, obj_max;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objectif);
        init();
    }

    private void init() {
        obj_min = (EditText) findViewById(R.id.ed_obj_min);
        obj_max = (EditText) findViewById(R.id.ed_obj_max);
    }

    public void valider(View view) {
        String min = obj_min.getText().toString(), max = obj_max.getText().toString();
        if (!min.equals("") && !max.equals("")){
            int minimum = Integer.parseInt(obj_min.getText().toString()), maximum = Integer.parseInt(obj_max.getText().toString());
            if ((minimum > 0) && (maximum > 0) && (minimum < maximum)){
                SharedPreferences pref = getSharedPreferences("OBJECTIF", Context.MODE_PRIVATE);
                SharedPreferences.Editor prefEdit = pref.edit();
                prefEdit.putInt("obj_min", minimum);
                prefEdit.putInt("obj_max", maximum);
                prefEdit.apply();
                //Retour à MainActivity
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }else{
                message = new Toast(this, "Veuillez vérifier vos données.");
            }
        }else{
            message = new Toast(this, "Veuillez fixer un objectif cohérent.");
        }
    }

}
