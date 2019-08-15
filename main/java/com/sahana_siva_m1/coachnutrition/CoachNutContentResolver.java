package com.sahana_siva_m1.coachnutrition;


import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.util.Log;

public class CoachNutContentResolver {

    public final static String TABLE_ALIMENTS = "aliments";
    public final static String COLONNE_ALIMENT = "aliment";
    public final static String COLONNE_CALORIES = "calorie";

    public final static String TABLE_DONNEES_NUTRITIONNELLES = "donneesnutritionnelles";
    public final static String COLONNE_DATE = "date";
    public final static String COLONNE_NB_REPAS = "nbrepas";
    public final static String COLONNE_QTE_MANGE = "quantite";

    private static String authority = "com.sahana_siva_m1.coachnutrition";

    private Context context;
    private ContentResolver contentResolver;

    public CoachNutContentResolver(Context c) {
        context = c;
        contentResolver = context.getContentResolver();
    }

    public boolean ajouterAliments(String aliment, int calorie){
        long resultat_insertion = -1;
        try {
            ContentValues values = new ContentValues();
            values.put(COLONNE_ALIMENT, aliment);
            values.put(COLONNE_CALORIES, calorie);

            Uri.Builder builder = new Uri.Builder();
            builder.scheme("content").authority(authority).appendPath(TABLE_ALIMENTS);
            Uri uri = builder.build();
            uri = contentResolver.insert(uri,values);
            resultat_insertion = ContentUris.parseId(uri);
        }catch (SQLiteException e){ //SQLiteConstraintException
            Log.d("ajouterAliments", "Erreur : " + e);
        }

        if (resultat_insertion != -1){
            Log.d("ajouterAliments", "Insertion reussie");
            return true;
        } else{
            Log.d("ajouterAliments", "Erreur insertion");
            return false;
        }
    }

   /* public void ajouterDataNut(Date date, int qte){
        long resultat_insertion = -1;

        try {
            ContentValues values = new ContentValues();
            values.put(COLONNE_DATE, date);
            values.put(COLONNE_QTE_MANGE, qte);

            Uri.Builder builder = new Uri.Builder();
            builder.scheme("content").authority(authority).appendPath(TABLE_DONNEES_NUTRITIONNELLES);
            Uri uri = builder.build();
            uri = contentResolver.insert(uri,values);
            resultat_insertion = ContentUris.parseId(uri);
        }catch (SQLiteException e){ //SQLiteConstraintException
            Log.d("ajouterDataNut", "Erreur : " + e);
        }

        if (resultat_insertion != -1){
            Log.d("ajouterDataNut", "Insertion reussie");
        } else
            Log.d("ajouterDataNut", "Erreur insertion");
    }*/

}
