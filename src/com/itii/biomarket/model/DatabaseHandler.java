package com.itii.biomarket.model;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
	
	String NAME_DB;
    String PATH_DB;
    Context context;

	public DatabaseHandler(Context context, String name, String path, CursorFactory factory, int version) {
		super(context, name, factory, version);
		this.context = context;
		NAME_DB = name;
        PATH_DB = path;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}
	
	
	public SQLiteDatabase openDataBase() throws SQLException{
        SQLiteDatabase dbRetour = null;

        try {
        	dbRetour = SQLiteDatabase.openDatabase(PATH_DB + NAME_DB, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (Exception e) {
        	Log.d("Probl�me application", "Probl�me: pas de base de donn�es sur le t�l�phone ! Installez la base avant de lancer l'application");
        	
        }
        return dbRetour;
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
 
        try{
        	Log.d("DataBaseHandler: fonction CheckDatabase","test - check de la base de donn�es");
            checkDB = SQLiteDatabase.openDatabase(PATH_DB + NAME_DB, null, SQLiteDatabase.OPEN_READONLY);
        }catch (SQLiteException e) {
            //database does't exist yet.
        	Log.d("Probl�me application", "Probl�me: pas de base de donn�es sur le t�l�phone ! Installez la base avant de lancer l'application");
        }
 
        if (checkDB != null)
            checkDB.close();
 
        return checkDB != null ? true : false;
    }

}
