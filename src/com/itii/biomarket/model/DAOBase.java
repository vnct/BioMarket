package com.itii.biomarket.model;

import java.io.File;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

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
}