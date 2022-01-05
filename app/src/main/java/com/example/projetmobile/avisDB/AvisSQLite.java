package com.example.projetmobile.avisDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AvisSQLite extends SQLiteOpenHelper {

    public static final String TABLE_AVIS = "T_Avis";
    public static final String COLUMN_AVIS_ID ="_id";
    public static final String COLUMN_AVIS_URL ="Avis_url";
    public static final String COLUMN_AVIS_SCORE ="Avis_score";

    private final static String CREATE_TABLE = "CREATE TABLE " + TABLE_AVIS + " ("+COLUMN_AVIS_ID+" INTEGER PRIMARY KEY, "
            + COLUMN_AVIS_URL + " VARCHAR(255), " + COLUMN_AVIS_SCORE + " VARCHAR(225) );";


    public AvisSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
