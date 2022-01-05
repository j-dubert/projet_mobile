package com.example.projetmobile;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.widget.Toast;

public class testprovider {

    private static Uri provideruri = Uri.parse("content://com.example.appcontact.provider/T_Contacts");

    /*
    public static void testprovidertest (Context context) throws RemoteException {
        ContentProviderClient yourCR =  context.getContentResolver().acquireContentProviderClient(provideruri );
        Cursor c =yourCR.query(provideruri, null, "*", null, null );
        if (c != null){
            System.out.println("--------------------------fuck yeah------------------------------");
        }else{
            System.out.println("fuck no");
        };
    }
    */


}
