package com.example.projetmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbAvis {
    private final static int VERSION_BDD = 1;
    private SQLiteDatabase bdd;
    private AvisSQLite maBaseSQLite;
    public int id = 0;

    public DbAvis(Context context){
        //On crée la BDD et sa table :
        maBaseSQLite = new AvisSQLite(context,"Avis.db",null,VERSION_BDD);
    }
    public void open(){
        bdd = maBaseSQLite.getWritableDatabase();

    }


    public void close(){

        maBaseSQLite.close();
    }

    public SQLiteDatabase getBdd(){
        return bdd;
    }

    public long insertAvis(Avisweb avisweb){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        open();
        ContentValues values = new ContentValues();
        values.put(AvisSQLite.COLUMN_AVIS_URL,avisweb.get_web());
        values.put(AvisSQLite.COLUMN_AVIS_SCORE,avisweb.get_score());
        long id = bdd.insert(AvisSQLite.TABLE_AVIS,null,values);
        close();
        return id;
    }

    public boolean findurl(String s){
        Cursor cursor = bdd.query(AvisSQLite.TABLE_AVIS, new String[]{AvisSQLite.COLUMN_AVIS_URL,AvisSQLite.COLUMN_AVIS_SCORE},
                AvisSQLite.COLUMN_AVIS_URL+" =?", new String[]{s},null,null,null,null);
        return cursortoavis(cursor);
    }

    public boolean cursortoavis(Cursor cursor){

        if (cursor.getCount() == 0){
            return false;
        }
        else{
           return true;
        }
    }
}
