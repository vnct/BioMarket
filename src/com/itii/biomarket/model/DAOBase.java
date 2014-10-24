package com.itii.biomarket.model;

import java.io.File;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

public abstract class DAOBase {
	  // Nous sommes � la premi�re version de la base
	  // Si je d�cide de la mettre � jour, il faudra changer cet attribut
	  protected final static int VERSION = 1;
	  // Le nom du fichier qui repr�sente ma base
	  protected final static String NOM = "biomarket.db";
	  protected final static String PATH = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator;
	    
	  protected SQLiteDatabase mDb = null;
	  protected DatabaseHandler mHandler = null;
	    
	  public DAOBase(Context pContext) {
	    this.mHandler = new DatabaseHandler(pContext, NOM, PATH, null, VERSION);
	  }
	    
	  public SQLiteDatabase open() {
	    // Pas besoin de fermer la derni�re base puisque getWritableDatabase s'en charge
	    mDb = mHandler.openDataBase();
	    return mDb;
	  }
	    
	  public void close() {
	    mDb.close();
	  }
	    
	  public SQLiteDatabase getDb() {
	    return mDb;
	  }
	  
	  protected static double getDistanceBetweenTwoPoints(float x1, float y1, float x2, float y2) {
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