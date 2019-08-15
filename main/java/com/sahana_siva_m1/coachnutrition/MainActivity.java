package com.sahana_siva_m1.coachnutrition;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toast message;
    private TextView tv_obj_min, tv_obj_max;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
    }

    private void init() {
        tv_obj_min = (TextView) findViewById(R.id.tv_obj_min);
        tv_obj_max = (TextView) findViewById(R.id.tv_obj_max);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //recuperer objectif stockee dans les pref
        SharedPreferences pref = getSharedPreferences("OBJECTIF", Context.MODE_PRIVATE);
        int obj_min = pref.getInt("obj_min", 2000); //2000 : valeur par defaut
        int obj_max = pref.getInt("obj_max", 2100); //2100 : valeur par defaut

        tv_obj_min.setText(String.valueOf(obj_min));
        tv_obj_max.setText(String.valueOf(obj_max));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.activity_bilan:
                faireToast("Bilan");
                intent = new Intent(this, BilanActivity.class);
                startActivity(intent);
                return true;
            case R.id.activity_objectif:
                //faireToast("Objectif");
                intent = new Intent(this, ObjectifActivity.class);
                startActivity(intent);
                return true;
            case R.id.activity_listealiments:
                faireToast("Liste");
                intent = new Intent(this, ListeAlimentsActivity.class);
                startActivity(intent);
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    public void faireToast(String msg){
        message = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        message.show();
    }

}
