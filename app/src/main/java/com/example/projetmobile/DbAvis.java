package com.example.projetmobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbAvis {
    private final static int VERSION_BDD = 1;
    private static SQLiteDatabase bdd;
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

    public static SQLiteDatabase getBdd(){

        return bdd;
    }

    public long insertAvis(Avisweb avisweb){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        values.put(AvisSQLite.COLUMN_AVIS_URL,avisweb.get_web());
        values.put(AvisSQLite.COLUMN_AVIS_SCORE,avisweb.get_score());
        long id = bdd.insert(AvisSQLite.TABLE_AVIS,null,values);

        return id;
    }

    public void updateavis(Avisweb avisweb){
        ContentValues cv = new ContentValues();
        cv.put(AvisSQLite.COLUMN_AVIS_SCORE, avisweb.get_score());
        bdd.update(AvisSQLite.TABLE_AVIS,cv,AvisSQLite.COLUMN_AVIS_URL+"=?", new String[]{avisweb.get_web()});
    }

    public Avisweb findurl(String s){
        Cursor cursor = bdd.query(AvisSQLite.TABLE_AVIS, new String[]{AvisSQLite.COLUMN_AVIS_URL,AvisSQLite.COLUMN_AVIS_SCORE},
                AvisSQLite.COLUMN_AVIS_URL+" =?", new String[]{s},null,null,null,null);
        return cursorToLivre(cursor);
    }



    public Avisweb cursorToLivre(Cursor cursor){
        if (cursor.getCount() == 0){
            return null;}
        else{
            cursor.moveToFirst();
            Avisweb avis1 = new Avisweb();
            avis1.set_websitename(cursor.getString(0));
            avis1.set_score(cursor.getInt(1));
            System.out.println(avis1);
            return avis1;
        }
    }
}
