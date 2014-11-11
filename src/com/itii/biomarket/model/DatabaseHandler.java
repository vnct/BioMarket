package com.itii.biomarket.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
 

public class DatabaseHandler extends SQLiteOpenHelper {
 
	// script SQLite - nom des tables et des colonnes
	public static final String CATEGORIE_KEY = "id_categorie"; 
	public static final String CATEGORIE_NOM = "nom_categorie";
	
	public static final String PRODUITS_KEY = "id_produit"; 
	public static final String PRODUITS_NOM = "nom_produit";
	public static final String PRODUITS_ORIGINE = "origine_produit";
	
	public static final String COMMERCANT_KEY = "id_commercant"; 
	public static final String COMMERCANT_NOM = "nom_commercant";
	public static final String COMMERCANT_TEL = "tel_commercant";
	
	public static final String AP_KEY = "id_ap";
	public static final String AP_CP = "CP_ap";
	public static final String AP_NUMERO = "numero_ap";
	public static final String AP_RUE = "rue_ap";
	
	public static final String VILLE_KEY = "id_ville";
	public static final String VILLE_NOM = "nom_ville";
	
	public static final String DG_KEY = "id_dg";
	public static final String DG_LATITUDE = "latitude_dg";
	public static final String DG_LONGITUDE = "longitude_dg";
 
	public static final String PRODUITS_TABLE_NAME = "produit";
	public static final String CATEGORIE_TABLE_NAME = "categorie";
	public static final String COMMERCANT_TABLE_NAME = "commercant";
	public static final String AP_TABLE_NAME = "adressepostale";
	public static final String VILLE_TABLE_NAME = "ville";
	public static final String DG_TABLE_NAME = "donneesgeographiques";
	public static final String VEND_TABLE_NAME = "vend";
	
 
	// script SQLite -- creation des tables
	public static final String CATEGORIE_TABLE_CREATE = 
			"CREATE TABLE " + CATEGORIE_TABLE_NAME + " (" 
					+ CATEGORIE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT , " 
					+ CATEGORIE_NOM + " TEXT NOT NULL " 
			+ ");";
	public static final String PRODUITS_TABLE_CREATE = 
			"CREATE TABLE " + PRODUITS_TABLE_NAME + " (" 
					+ PRODUITS_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT , " 
					+ PRODUITS_NOM + " TEXT NOT NULL , " 
					+ PRODUITS_ORIGINE + " TEXT NOT NULL , " 
					+ CATEGORIE_KEY + " INTEGER NOT NULL , " 
					+ "FOREIGN KEY ( " + CATEGORIE_KEY + ") REFERENCES "+CATEGORIE_TABLE_NAME+"(" + CATEGORIE_KEY + ") "
			+ ");";
	public static final String COMMERCANT_TABLE_CREATE = 
			"CREATE TABLE " + COMMERCANT_TABLE_NAME + " (" 
					+ COMMERCANT_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT , " 
					+ COMMERCANT_NOM + " TEXT NOT NULL , " 
					+ COMMERCANT_TEL + " TEXT NOT NULL , " 
					+ AP_KEY + " INTEGER NOT NULL , " 
					+ DG_KEY + " INTEGER NOT NULL , " 
					+ "FOREIGN KEY ( " + AP_KEY + ") REFERENCES "+AP_TABLE_NAME+"(" + AP_KEY + ") , "
					+ "FOREIGN KEY ( " + DG_KEY + ") REFERENCES "+DG_TABLE_NAME+"(" + DG_KEY + ") "
			+ ");";
	public static final String AP_TABLE_CREATE = 
			"CREATE TABLE " + AP_TABLE_NAME + " (" 
					+ AP_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT , " 
					+ AP_CP + " TEXT NOT NULL , " 
					+ AP_NUMERO + " TEXT NOT NULL , " 
					+ AP_RUE + " TEXT NOT NULL , " 
					+ VILLE_KEY + " INTEGER NOT NULL , " 
					+ "FOREIGN KEY ( " + VILLE_KEY + ") REFERENCES "+VILLE_TABLE_NAME+"(" + VILLE_KEY + ") "
			+ ");";
	public static final String VILLE_TABLE_CREATE = 
			"CREATE TABLE " + VILLE_TABLE_NAME + " (" 
					+ VILLE_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT , " 
					+ VILLE_NOM + " TEXT NOT NULL " 
			+ ");";
	public static final String DG_TABLE_CREATE = 
			"CREATE TABLE " + DG_TABLE_NAME + " (" 
					+ DG_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT , " 
					+ DG_LATITUDE + " REAL NOT NULL , " 
					+ DG_LONGITUDE + " REAL NOT NULL "
			+ ");";
	public static final String VEND_TABLE_CREATE = 
			"CREATE TABLE " + VEND_TABLE_NAME + " (" 
					+ COMMERCANT_KEY + " INTEGER NOT NULL , " 
					+ PRODUITS_KEY + " INTEGER NOT NULL , " 
					+ "PRIMARY KEY (" + COMMERCANT_KEY + "," + PRODUITS_KEY + ") ,"
					+ "FOREIGN KEY ( " + COMMERCANT_KEY + ") REFERENCES "+COMMERCANT_TABLE_NAME+"(" + COMMERCANT_KEY + ") , "
					+ "FOREIGN KEY ( " + PRODUITS_KEY + ") REFERENCES "+PRODUITS_TABLE_NAME+"(" + PRODUITS_KEY + ") "
			+ ");";

	// script SQLite -- creation données
	public static final String CATEGORIE_TABLE_DATA = 
			"INSERT INTO " + CATEGORIE_TABLE_NAME + " ( " + CATEGORIE_NOM + ")"
			+ "VALUES"
			+"('Fruits et Legumes'),"
			+"('Epicerie Salee'),"
			+"('Epicerie Sucree'),"
			+"('Produits Frais'),"
			+"('Vrac');";
	public static final String PRODUITS_TABLE_DATA = 
			"INSERT INTO " + PRODUITS_TABLE_NAME + " ( " + PRODUITS_NOM + ", " + PRODUITS_ORIGINE + "," + CATEGORIE_KEY + ")"
			+ "VALUES"
			+"('carotte', 'inde', 1),"
			+"('salade', 'france', 1),"
			+"('poireau', 'maroc', 1),"
			+"('tomate', 'italie', 1),"
			+"('poivron', 'france', 1);";
	public static final String DG_TABLE_DATA = 
			"INSERT INTO " + DG_TABLE_NAME + " ( " + DG_LATITUDE + ", " + DG_LONGITUDE + ")"
			+ "VALUES"
			+"(43.600,7.100),"
			+"(43.723,7.199),"
			+"(43.120,5.935),"
			+"(48.872,2.343),"
			+"(43.11458100,5.85821800),"
			+"(43.138,6.009),"
			+"(43.128696,6.012652),"
			+"(43.100,5.825),"
			+"(43.4482392,5.863105),"
			+"(43.124011,6.1476887);";
	
	public static final String COMMERCANT_TABLE_DATA = 
			"INSERT INTO " + COMMERCANT_TABLE_NAME + " ( " + COMMERCANT_NOM + ", " + COMMERCANT_TEL + ", " + AP_KEY + ", " + DG_KEY + ")"
			+ "VALUES"
			+"('MarketBIO Antibes','04 92 91 46 79',1,1),"
			+"('MarketBIO Nice Lingostière','04 93 18 62 79',2,2),"
			+"('MarketBIO Toulon MAYOL','04 94 41 98 79',3,3),"
			+"('MarketBIO Paris Faubourg','01 47 70 08 02',4,4),"
			+"('MarketBIO La Seyne sur Mer','04 94 99 20 45',5,5),"
			+"('MarketBIO La Valette du Var','04 94 52 30 58',6,6),"
			+"('MarketBIO La Garde','04 94 58 45 78',7,7),"
			+"('MarketBIO Six Fours Les Plages','04 94 25 55 06',8,8),"
			+"('MarketBIO Saint Maximin','04 94 20 30 54',9,9),"
			+"('MarketBIO Hyeres','04 94 52 36 78',10,10);";
	public static final String AP_TABLE_DATA = 
			"INSERT INTO " + AP_TABLE_NAME + " ( " + AP_CP + ", " + AP_NUMERO + ", " + AP_RUE + ", " + VILLE_KEY + ")"
			+ "VALUES"
			+"('06600','1530','Chemin de Saint-Claude',1),"
			+"('06200','6202','Route de Digne',2),"
			+"('83000','1','Rue du Murier',3),"
			+"('75009','9','Rue du Faubourg Montmartre',4),"
			+"('83500','1','Avenue Jean Bartolini',5),"
			+"('83160','1','Route Université',6),"
			+"('83130','119','Rue Pierre Revelli',7),"
			+"('83140','1','avenue de la mer',8),"
			+"('83470','1','Chemin de la Gare',9),"
			+"('83400','22','Avenue du Marechal Delattre de Tassigny',10);";
	public static final String VILLE_TABLE_DATA = 
			"INSERT INTO " + VILLE_TABLE_NAME + " ( " + VILLE_NOM + ")"
			+ "VALUES"
			+"('ANTIBES'),"
			+"('NICE'),"
			+"('TOULON'),"
			+"('PARIS'),"
			+"('LA SEYNE SUR MER'),"
			+"('LA VALETTE DU VAR'),"
			+"('LA GARDE'),"
			+"('SIX FOURS LES PLAGES'),"
			+"('SAINT MAXIMIN'),"
			+"('HYERES');";
	public static final String VEND_TABLE_DATA = 
			"INSERT INTO " + VEND_TABLE_NAME + " ( " + COMMERCANT_KEY + "," + PRODUITS_KEY + ")"
			+ "VALUES"
			+"(1,1),"
			+"(1,2),"
			+"(1,3),"
			+"(3,4),"
			+"(3,5),"
			+"(5,1),"
			+"(5,2),"
			+"(6,2),"
			+"(6,4),"
			+"(6,5),"
			+"(7,3),"
			+"(7,4),"
			+"(7,5),"
			+"(8,1);";
	
	// script de suppression drop table 
	public static final String PRODUITS_TABLE_DROP = "DROP TABLE IF EXISTS " + PRODUITS_TABLE_NAME +";";
	public static final String CATEGORIE_TABLE_DROP = "DROP TABLE IF EXISTS " + CATEGORIE_TABLE_NAME +";";
	public static final String COMMERCANT_TABLE_DROP = "DROP TABLE IF EXISTS " + COMMERCANT_TABLE_NAME +";";
	public static final String AP_TABLE_DROP = "DROP TABLE IF EXISTS " + AP_TABLE_NAME +";";
	public static final String VILLE_TABLE_DROP = "DROP TABLE IF EXISTS " + VILLE_TABLE_NAME +";";
	public static final String DG_TABLE_DROP = "DROP TABLE IF EXISTS " + DG_TABLE_NAME +";";
	public static final String VEND_TABLE_DROP = "DROP TABLE IF EXISTS " + VEND_TABLE_NAME +";";
 
	public DatabaseHandler(Context context, String name, CursorFactory factory, int version)
	{
		super(context, name, factory, version);
	}
 
 
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		
		Log.d("Creation base", "creation de la table "+CATEGORIE_TABLE_NAME);
		db.execSQL(CATEGORIE_TABLE_CREATE); 
		db.execSQL(CATEGORIE_TABLE_DATA); 
		Log.d("Creation base", "creation de la table "+PRODUITS_TABLE_NAME);
		db.execSQL(PRODUITS_TABLE_CREATE);
		db.execSQL(PRODUITS_TABLE_DATA); 
		Log.d("Creation base", "creation de la table "+COMMERCANT_TABLE_NAME);
		db.execSQL(COMMERCANT_TABLE_CREATE);
		db.execSQL(COMMERCANT_TABLE_DATA); 
		Log.d("Creation base", "creation de la table "+AP_TABLE_NAME);
		db.execSQL(AP_TABLE_CREATE);
		db.execSQL(AP_TABLE_DATA); 
		Log.d("Creation base", "creation de la table "+VILLE_TABLE_NAME);
		db.execSQL(VILLE_TABLE_CREATE);
		db.execSQL(VILLE_TABLE_DATA);
		Log.d("Creation base", "creation de la table "+DG_TABLE_NAME);
		db.execSQL(DG_TABLE_CREATE);
		db.execSQL(DG_TABLE_DATA);
		Log.d("Creation base", "creation de la table "+VEND_TABLE_NAME);
		db.execSQL(VEND_TABLE_CREATE);
		db.execSQL(VEND_TABLE_DATA);
		
	}
 
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
		db.execSQL(CATEGORIE_TABLE_DROP);  
		db.execSQL(PRODUITS_TABLE_DROP); 
		db.execSQL(COMMERCANT_TABLE_DROP); 
		db.execSQL(AP_TABLE_DROP); 
		db.execSQL(VILLE_TABLE_DROP); 
		db.execSQL(DG_TABLE_DROP); 
		db.execSQL(VEND_TABLE_DROP); 
		this.onCreate(db);
		// TODO Auto-generated method stub
	}
 
 
 
 
}