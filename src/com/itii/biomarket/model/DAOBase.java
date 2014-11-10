package com.itii.biomarket.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
 
 
public abstract class DAOBase {
	// Nous sommes la premiere version de la base
	//Si je décide de la mettre a jour, il faut changer cet attribut
 
	protected final static int VERSION = 1;
 
	//Nom du fichier de ma base
	protected final static String NOM = "biomarket.db";
 
	protected static SQLiteDatabase mDb = null;
	protected DatabaseHandler mHandler = null;
 
	public DAOBase(Context pContext)
	{
		this.mHandler = new DatabaseHandler(pContext, NOM, null, VERSION);
	}
 
	public SQLiteDatabase open()
	{
		//Pas besoin de fermer la derniere base puisque getWritableDatabase s'en charge
 
		mDb = mHandler.getReadableDatabase();
		return mDb;
	}
 
	public void close()
	{
		mDb.close();
	}
 
 
	public SQLiteDatabase getDb()
	{
		return mDb;
	}
	
	public static double getDistanceBetweenTwoPoints(float x1, float y1, float x2, float y2) {
        double R = 6371000; // m
        double dLat = Math.toRadians(x2 - x1);
        double dLon = Math.toRadians(y2 - y1);
        double lat1 = Math.toRadians(x1);
        double lat2 = Math.toRadians(x2);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2)
                * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;
        
        Log.d("Calcul distance", "entre " + x1 + ", " + y1 + " et " + x2 + "," + y2 +" => " + d);

        return d;
    }
 
 
}