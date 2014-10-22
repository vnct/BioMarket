package com.itii.biomarket.model;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

public class StoreManagement extends DAOBase {	
	
	public StoreManagement(Context pContext) {
		super(pContext);
		// TODO Auto-generated constructor stub
	}

	// renvoi la liste des articles (+ catégories)
	public List<Commercant> getMagasins() {
		try {
			List<Commercant> l = new LinkedList<Commercant>();
			Cursor c = mDb.rawQuery("SELECT id_commercant, nom_commercant, latitude_dg, longitude_dg "
					+ "FROM commercant, donnesgeographiques "
					+ "WHERE commercant.id_dg = donnesgeographiques.id_dg"
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
	
	// renvoi la liste des articles (+ catégories)
	public List<Article> getArticles() {
		// CODE
		/*Example of usage 
		 * 
		StoreManagement DAOS = new StoreManagement(this);
        DAOS.open();

        List<Article> ArticleGet = DAOS.getArticles();
        String result = "";
        if(ArticleGet != null) {
			for(Article m : ArticleGet)
        		result += m.getId() + ", " + m.getNom() + ", " + m.getOrigine() + ", " + m.getCategorie() + "\n"; 
        	intitule.setText(result);
        }
        else
        	intitule.setText("Pas de données trouvées");  
		 */
		try {
			List<Article> l = new LinkedList<Article>();
			Cursor c = mDb.rawQuery("SELECT id_produit, nom_produit, origine_produit, nom_categorie "
					+ "FROM produit "
					+ "INNER JOIN categorie "
					+ "ON produit.id_categorie = categorie.id_categorie"
					, null);
			if(c.getCount() > 0){
				//Log.d("test", "ok, on parcours la liste ..., nombre de colonnes : "+c.getCount());
				while (c.moveToNext()) {
					long idP = c.getInt(0);
					String nomP = c.getString(1);
					String origineP = c.getString(2);
					String categorieP = c.getString(3);
					Article m = new Article(idP, nomP, origineP, categorieP);
					l.add(m);
				}
				c.close();
				return l;
			}
		} catch (SQLException e) {
			Log.e("Fonction getArticles (storeManagement) : ", "Problème avec la requête SELECT", e);
		} catch(Exception ex) {
			Log.e("FonctiongetArticles (storeManagement) : ", "Problème autre que SQL dans la fonction", ex);
		}
		return null;
	}

	// renvoi la liste des articles (liste car un article peut être présent dans plusieurs magasins).
	public List<Article> getArticle(String nomArticle) {
		if(nomArticle == null || nomArticle == "") {
			Log.d("Problème application: fonction getArticle (StoreManagement)", "Aucun nom d'article passé à la fonction (ou vide)");
			System.exit(0);
		}
		try {
			List<Article> l = new LinkedList<Article>();
			Cursor c = mDb.rawQuery(
					"SELECT produit.id_produit, nom_produit, origine_produit, nom_categorie, latitude_dg, longitude_dg "
							+ "FROM produit, categorie, vend, commercant, donnesgeographiques "
							+ "WHERE produit.id_categorie = categorie.id_categorie "
							+ "AND commercant.id_commercant = vend.id_commercant "
							+ "AND produit.id_produit = vend.id_produit "
							+ "AND commercant.id_dg = donnesgeographiques.id_dg "
							+ "AND nom_produit='" + nomArticle + "'"
							, null);
			
			if(c.getCount() > 0){
				//Log.d("test", "ok, on parcours la liste ..., nombre de colonnes : "+c.getCount());
				while (c.moveToNext()) {
					long idP = c.getInt(0);
					String nomP = c.getString(1);
					String origineP = c.getString(2);
					String categorieP = c.getString(3);
					float latitudeP = c.getFloat(4);
					float longitudeP = c.getFloat(5);
					Log.d("infos articles","Recupérées : " + c.getFloat(4) + ", " + c.getFloat(5));
					Article m = new Article(idP, nomP, origineP, categorieP, latitudeP, longitudeP);
					l.add(m);
				}
				c.close();
				return l;
			}
		} catch (SQLException e) {
			Log.e("Fonction getArticle(nomArticle) (storeManagement) : ", "Problème avec la requête SELECT", e);
		} catch(Exception ex) {
			Log.e("Fonction getArticle(nomArticle) (storeManagement) : ", "Problème autre que SQL dans la fonction", ex);
		}
		return null;
	}
	
	// renvoi l'article, dont le magasin est le plus proche du client.
	public Article getArticleAuPlusProche(String nomArticle, float latitudeClient, float longitudeClient) {
		
		/*Example of usage
		 * 
		float la = (float) 43.6;
        float lo = (float) 7.1;

        Article m = DAOS.getArticleAuPlusProche("carotte", la, lo);
        String result = "";
        if(m != null) {			
			result += m.getId() + ", " + m.getNom() + ", " + m.getOrigine() + ", " + m.getCategorie() + "," + DAOS.getInfosCommercant(m.getLatitude_dg(), m.getLongitude_dg()).getNom() + "\n"; 
        	intitule.setText(result);
        }
        else
        	intitule.setText("Pas de données trouvées");  
		 */
		
		if(nomArticle == null || nomArticle == "") {
			Log.d("Problème application: fonction getArticleAuPlusProche (StoreManagement)", "Aucun nom d'article passé à la fonction (ou vide)");
			System.exit(0);
		}
		try {
			List<Article> articles = getArticle(nomArticle);
			if(articles != null) {
				if(articles.size() == 1) {
					return articles.get(0);
				}
				else {
					double distancePlusProche = -1;
					Article articleARetourner = null;
					for(Article m : articles)  {
						if(distancePlusProche == -1) {
							distancePlusProche = getDistanceBetweenTwoPoints(latitudeClient, longitudeClient, m.getLatitude_dg(), m.getLongitude_dg());
							articleARetourner = m;
						}
						else {
							double nouvelleDistance;
							nouvelleDistance = getDistanceBetweenTwoPoints(latitudeClient, longitudeClient, m.getLatitude_dg(), m.getLongitude_dg());
							if(nouvelleDistance < distancePlusProche) {
								distancePlusProche = nouvelleDistance;
								articleARetourner = m;
							}
						}
					}
					return articleARetourner;
				}
			}
		} catch (SQLException e) {
			Log.e("Fonction getArticleAuPlusProche(nomArticle, latitude et longitude) (storeManagement) : ", "Problème avec la requête SELECT", e);
		} catch(Exception ex) {
			Log.e("Fonction getArticleAuPlusProche(nomArticle, latitude et longitude) (storeManagement) : ", "Problème autre que SQL dans la fonction", ex);
		}
		return null;
	}
	
	// renvoi les infos d'un magasin en fonction de sa position géographique
	public Commercant getInfosCommercant(float latitude, float longitude) {
		
		/*Example of usage
		 * 
		StoreManagement DAOS = new StoreManagement(this);
        DAOS.open();

        List<Article> ArticleGet = DAOS.getArticle("poivron");
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
							+ "FROM commercant, donnesgeographiques, adressepostale, ville "
							+ "WHERE commercant.id_dg = donnesgeographiques.id_dg "
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
	
	// fonction qui renvoi les magasins qui concernent un certain périmètre autour de l'utilisateur (non optimisé - calcul de distance directe, sans tenir compte d'un réel algorithme)
	// la distance maximum en nombre de mètres
	@SuppressWarnings("unused")
	public List<Commercant> getMagasinsDansPerimetre(float latitudeClient, float longitudeClient, float distance_max) {
		
		/*Example of usage
		 * 
		float la = (float) 43.12;
        float lo = (float) 5.935;
        
        List<Commercant> CommercantGet = DAOS.getMagasinsDansPerimetre(la, lo, 10000);
        String result = "";
        if(CommercantGet != null) {
			for(Commercant m : CommercantGet) {
        		// magasin associé
        		Commercant c = DAOS.getInfosCommercant(m.getLatitude_dg(), m.getLongitude_dg());
        		if(c != null) {
        			result += c.getId() + ", " + c.getNom() + ", " + c.getAdr_numero_rue() + ", " + c.getAdr_nom_rue() + ", " + c.getAdr_cp() + ", " + c.getAdr_ville() + ", " + c.getTel() + ", " + c.getLatitude_dg() + ", " + c.getLongitude_dg() + "\n"; 
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