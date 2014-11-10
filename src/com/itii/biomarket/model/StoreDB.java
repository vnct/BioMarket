package com.itii.biomarket.model;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

public class StoreDB extends DAOBase {	
	
	public StoreDB(Context pContext) {
		super(pContext);
		// TODO Auto-generated constructor stub
	}

	// renvoi la liste des magasins (+ latitude/longitude)
	public List<Commercant> getMagasins() {
		
		// CODE
			/*
			 * 
				Example of usage 
				---------------- 
				
				StoreDB DAOS = new StoreDB(this);
		        DAOS.open();
		
		        List<Commercant> CommercantGet = null;
				CommercantGet = DAOS.getMagasins();
		        String result = "";
		        if(CommercantGet != null) {
					for(Commercant m : CommercantGet)
		        		result += m.getId() + ", " + m.getNom() + ", " + m.getLatitude_dg() + ", " + m.getLongitude_dg() + "\n"; 
		        	intitule.setText(result);
		        }
		        else
		        	intitule.setText("Pas de données trouvées");
			 
			 *
			 */
		
		try {
			List<Commercant> l = new LinkedList<Commercant>();
			Cursor c = mDb.rawQuery("SELECT id_commercant, nom_commercant, latitude_dg, longitude_dg "
					+ "FROM commercant, donneesgeographiques "
					+ "WHERE commercant.id_dg = donneesgeographiques.id_dg"
					, null);
			if(c.getCount() > 0){
				//Log.d("test", "ok, on parcours la liste ..., nombre de colonnes : "+c.getCount());
				while (c.moveToNext()) {
					long idC = c.getInt(0);
					String nomC = c.getString(1);
					float latitudeC = c.getFloat(2);
					float longitudeC = c.getFloat(3);
					Commercant m = new Commercant(idC, nomC, latitudeC, longitudeC);
					Log.d("magasins","magasin récupéré : "+m.getNom());
					l.add(m);
				}
				c.close();
				return l;
			}
		} catch (SQLException e) {
			Log.e("Fonction GetMagasins (storeManagement) : ", "Problème avec la requête SELECT", e);
		} catch(Exception ex) {
			Log.e("Fonction GetMagasins (storeManagement) : ", "Problème autre que SQL dans la fonction", ex);
		}
		return null;
	}

	// renvoi les infos d'un magasin en fonction de sa position géographique
	public Commercant getInfosCommercant(float latitude, float longitude) {
		
		/*CODE
		 * 
		 
		  	Example of usage
		  	----------------
		 
			BasketDB basket = new BasketDB(this);
	        basket.open();
	        StoreDB DAOS = new StoreDB(this);
	        DAOS.open();
	
	        List<Article> ArticleGet = null;
			try {
				ArticleGet = basket.getArticle("poivron");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        String result = "";
	        if(ArticleGet != null) {
				for(Article m : ArticleGet) {
	        		result += m.getId() + ", " + m.getNom() + ", " + m.getOrigine() + ", " + m.getCategorie() + ", " + m.getLatitude_dg() + ", " + m.getLongitude_dg() + "\n"; 
	        	
	        		// magasin associé
	        		Commercant c = DAOS.getInfosCommercant(m.getLatitude_dg(), m.getLongitude_dg());
	        		if(c != null) {
	        			result += c.getId() + ", " + c.getNom() + ", " + c.getAdr_numero_rue() + ", " + c.getAdr_nom_rue() + ", " + c.getAdr_cp() + ", " + c.getAdr_ville() + ", " + c.getTel() + "\n"; 
	        		}
	        		else
	        			result += "pas de magasin associé dans la base ...";
				}
				intitule.setText(result);
	        }
	        else
	        	intitule.setText("Pas de données trouvées");  
	        	
	       
		 */
		try {
			Cursor c = mDb.rawQuery(
					"SELECT id_commercant, nom_commercant, tel_commercant, numero_ap, rue_ap, CP_ap, nom_ville "
							+ "FROM commercant, donneesgeographiques, adressepostale, ville "
							+ "WHERE commercant.id_dg = donneesgeographiques.id_dg "
							+ "AND adressepostale.id_ap = commercant.id_ap "
							+ "AND ville.id_ville = adressepostale.id_ville "
							+ "AND latitude_dg='" + latitude + "' AND longitude_dg='" + longitude +"';"
							, null);
			if(c.getCount() > 0){
				//Log.d("test", "ok, on parcours la liste ..., nombre de colonnes : "+c.getCount());
				c.moveToFirst();
				long idC = c.getInt(0);
				String nomC = c.getString(1);
				String telC = c.getString(2);
				String numAdrC = c.getString(3);
				String rueAdrC = c.getString(4);
				String CPAdrC = c.getString(5);
				String villeC = c.getString(6);
				c.close();
				return new Commercant(idC, nomC, telC, numAdrC, rueAdrC, CPAdrC, villeC, latitude, longitude);
			}
		} catch (SQLException e) {
			Log.e("Fonction getInfosCommercant (storeManagement) : ", "Problème avec la requête SELECT", e);
		} catch(Exception ex) {
			Log.e("Fonction getInfosCommercant (storeManagement) : ", "Problème autre que SQL dans la fonction", ex);
		}
		return null;
	}
	
	// renvoi les infos d'un magasin en fonction de sa position géographique et celle de celle de l'utilisateur
	public Commercant getInfosCommercantWithDistanceUser(float latitude, float longitude, float latitudeClient, float longitudeClient) {
		
		/*CODE
		 * 
		 
		  	Example of usage
		  	----------------
		 
			float la = (float) 43.6;
	        float lo = (float) 7.1;
	        
	        BasketDB basket = new BasketDB(this);
	        basket.open();
	        StoreDB DAOS = new StoreDB(this);
	        DAOS.open();
	
	        List<Article> ArticleGet = null;
			try {
				try {
					ArticleGet = basket.getArticle("poivron");
				} catch (java.sql.SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        String result = "";
	        if(ArticleGet != null) {
				for(Article m : ArticleGet) {
	        		result += m.getId() + ", " + m.getNom() + ", " + m.getOrigine() + ", " + m.getCategorie() + ", " + m.getLatitude_dg() + ", " + m.getLongitude_dg() + "\n"; 
	        	
	        		// magasin associé
	        		Commercant c = DAOS.getInfosCommercantWithDistanceUser(m.getLatitude_dg(), m.getLongitude_dg(), la, lo);
	        		if(c != null) {
	        			result += c.getId() + ", " + c.getNom() + ", " + c.getAdr_numero_rue() + ", " + c.getAdr_nom_rue() + ", " + c.getAdr_cp() + ", " + c.getAdr_ville() + ", " + c.getTel() + ", " + c.getDistanceWithUser() + "\n"; 
	        		}
	        		else
	        			result += "pas de magasin associé dans la base ...";
				}
				intitule.setText(result);
	        }
	        else
	        	intitule.setText("Pas de données trouvées");   
	        	
	       
		 */
		try {
			Cursor c = mDb.rawQuery(
					"SELECT id_commercant, nom_commercant, tel_commercant, numero_ap, rue_ap, CP_ap, nom_ville "
							+ "FROM commercant, donneesgeographiques, adressepostale, ville "
							+ "WHERE commercant.id_dg = donneesgeographiques.id_dg "
							+ "AND adressepostale.id_ap = commercant.id_ap "
							+ "AND ville.id_ville = adressepostale.id_ville "
							+ "AND latitude_dg='" + latitude + "' AND longitude_dg='" + longitude +"';"
							, null);
			if(c.getCount() > 0){
				//Log.d("test", "ok, on parcours la liste ..., nombre de colonnes : "+c.getCount());
				c.moveToFirst();
				long idC = c.getInt(0);
				String nomC = c.getString(1);
				String telC = c.getString(2);
				String numAdrC = c.getString(3);
				String rueAdrC = c.getString(4);
				String CPAdrC = c.getString(5);
				String villeC = c.getString(6);
				c.close();
				return new Commercant(idC, nomC, telC, numAdrC, rueAdrC, CPAdrC, villeC, latitude, longitude, getDistanceBetweenTwoPoints(latitudeClient, longitudeClient, latitude, longitude));
			}
		} catch (SQLException e) {
			Log.e("Fonction getInfosCommercant (storeManagement) : ", "Problème avec la requête SELECT", e);
		} catch(Exception ex) {
			Log.e("Fonction getInfosCommercant (storeManagement) : ", "Problème autre que SQL dans la fonction", ex);
		}
		return null;
	}
	
	// fonction qui renvoi les magasins qui concernent un certain périmètre autour de l'utilisateur (non optimisé - calcul de distance directe, sans tenir compte d'un réel algorithme)
	// la distance maximum en nombre de mètres
	public List<Commercant> getMagasinsDansPerimetre(float latitudeClient, float longitudeClient, float distance_max) {
		
		/*CODE
		 * 
			
			Example of usage
			----------------
			
			StoreDB DAOS = new StoreDB(this);
	    	DAOS.open();
			
			float la = (float) 43.12;
	        float lo = (float) 5.935;
	        
	        List<Commercant> CommercantGet = DAOS.getMagasinsDansPerimetre(la, lo, 10000);
	        String result = "";
	        if(CommercantGet != null) {
				for(Commercant m : CommercantGet) {
	        		// magasin associé
	        		Commercant c = DAOS.getInfosCommercantWithDistanceUser(m.getLatitude_dg(), m.getLongitude_dg(), la, lo);
	        		if(c != null) {
	        			result += c.getId() + ", " + c.getNom() + ", " + c.getAdr_numero_rue() + ", " + c.getAdr_nom_rue() + ", " + c.getAdr_cp() + ", " + c.getAdr_ville() + ", " + c.getTel() + ", " + c.getLatitude_dg() + ", " + c.getLongitude_dg() + ", " + c.getDistanceWithUser() + "\n"; 
	        		}
				}
				intitule.setText(result);
				//Log.d("liste magasins",result);
	        }
	        else
	        	intitule.setText("pas de magasin proche dans la base ...");
		 */
		try {
			List<Commercant> l = new LinkedList<Commercant>();
			List<Commercant> magasinsDansPerimetre = new LinkedList<Commercant>();
			l = getMagasins();
			if(l != null) {
				for(Commercant m : l) {
					if(getDistanceBetweenTwoPoints(latitudeClient, longitudeClient, m.getLatitude_dg(), m.getLongitude_dg()) < distance_max) 
						//Log.d("Magasin ok", "Le magasin suivant est ok " + m.getNom());
						magasinsDansPerimetre.add(m);
				}
				if(magasinsDansPerimetre.size() > 0)
					return magasinsDansPerimetre;
				else
					return null;
			}
		} catch (SQLException e) {
			Log.e("Fonction getMagasinsDansPerimetre (storeManagement) : ", "Problème avec la requête SELECT", e);
		} catch(Exception ex) {
			Log.e("Fonction getMagasinsDansPerimetre (storeManagement) : ", "Problème autre que SQL dans la fonction", ex);
		}
		return null;
	} 
}
