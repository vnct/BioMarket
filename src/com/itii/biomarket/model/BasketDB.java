package com.itii.biomarket.model;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class BasketDB extends DAOBase {	
	
	public BasketDB(Context pContext) {
		super(pContext);
		// TODO Auto-generated constructor stub
	}

	// renvoi la liste des articles (+ cat�gories)
		public List<Article> getArticles() {
			// CODE
			/*Example of usage 
			 * 
			BasketDB DAOS = new BasketDB(this);
	        DAOS.open();

	        List<Article> ArticleGet = DAOS.getArticles();
	        String result = "";
	        if(ArticleGet != null) {
				for(Article m : ArticleGet)
	        		result += m.getId() + ", " + m.getNom() + ", " + m.getOrigine() + ", " + m.getCategorie() + "\n"; 
	        	intitule.setText(result);
	        }
	        else
	        	intitule.setText("Pas de donn�es trouv�es");  
			 */

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
			else
				return null;
		}

		// renvoi la liste des articles (liste car un article peut �tre pr�sent dans plusieurs magasins).
		public List<Article> getArticle(String nomArticle) {
			if(nomArticle == null || nomArticle == "") {
				Log.d("Probl�me application: fonction getArticle (StoreManagement)", "Aucun nom d'article pass� � la fonction (ou vide)");
				System.exit(0);
			}
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
					Log.d("infos articles","Recup�r�es : " + c.getFloat(4) + ", " + c.getFloat(5));
					Article m = new Article(idP, nomP, origineP, categorieP, latitudeP, longitudeP);
					l.add(m);
				}
				c.close();
				return l;
			}
			else
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
	        	intitule.setText("Pas de donn�es trouv�es");  
			 */
			
			if(nomArticle == null || nomArticle == "") {
				Log.d("Probl�me application: fonction getArticleAuPlusProche (StoreManagement)", "Aucun nom d'article pass� � la fonction (ou vide)");
				System.exit(0);
			}
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
			else
				return null;
		}
}
