package com.sahana_siva_m1.coachnutrition;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseCoachNutrition extends SQLiteOpenHelper {

    public final static int VERSION = 1;
    public final static String DB_NAME = "base_coach_nutrition";

    public final static String TABLE_ALIMENTS = "aliments";
    public final static String COLONNE_ALIMENT = "aliment";
    public final static String COLONNE_CALORIES = "calorie";

    public final static String TABLE_DONNEES_NUTRITIONNELLES = "donneesnutritionnelles";
    public final static String COLONNE_DATE = "date";
    public final static String COLONNE_NB_REPAS = "nbrepas";
    public final static String COLONNE_QTE_MANGE = "quantite";

    public final static String CREATE_ALIMENTS = "create table " + TABLE_ALIMENTS + "(" +
            COLONNE_ALIMENT + " string primary key, " +
            COLONNE_CALORIES + " integer " + ");";

    public final static String CREATE_DONNEES_NUTRITIONNELLES = "create table " + TABLE_DONNEES_NUTRITIONNELLES + "(" +
            COLONNE_DATE + " date, " +
            COLONNE_NB_REPAS + " integer autoincrement, " +
            COLONNE_ALIMENT + " string references aliments," +
            COLONNE_QTE_MANGE + " integer," +
            "primary key(" + COLONNE_DATE + ", " + COLONNE_NB_REPAS + "));";


    private static BaseCoachNutrition ourInstance;

    public static BaseCoachNutrition getInstance(Context context) {
        if (ourInstance == null)
            ourInstance = new BaseCoachNutrition(context);
        return ourInstance;
    }

    private BaseCoachNutrition(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ALIMENTS);
        //db.execSQL(CREATE_DONNEES_NUTRITIONNELLES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("drop table if exists " + TABLE_ALIMENTS);
            //db.execSQL("drop table if exists " + TABLE_DONNEES_NUTRITIONNELLES);
            onCreate(db);
        }
    }

}
