package com.itii.biomarket.model;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class StoreDB extends DAOBase {	
	
	public StoreDB(Context pContext) {
		super(pContext);
		// TODO Auto-generated constructor stub
	}

	// renvoi la liste des articles (+ cat�gories)
	public List<Commercant> getMagasins() {
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
				Log.d("magasins","magasin r�cup�r� : "+m.getNom());
				l.add(m);
			}
			c.close();
			return l;
		}
		else
			return null;
	}
	
	
	
	// renvoi les infos d'un magasin en fonction de sa position g�ographique
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
        	
        		// magasin associ�
        		Commercant c = DAOS.getInfosCommercant(m.getLatitude_dg(), m.getLongitude_dg());
        		if(c != null) {
        			result += c.getId() + ", " + c.getNom() + ", " + c.getAdr_numero_rue() + ", " + c.getAdr_nom_rue() + ", " + c.getAdr_cp() + ", " + c.getAdr_ville() + ", " + c.getTel() + "\n"; 
        		}
        		else
        			result += "pas de magasin associ� dans la base ...";
        		
        		
			}
			
			intitule.setText(result);
        }
        else
        	intitule.setText("Pas de donn�es trouv�es");  
		 */
		
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
		else
			return null;
	}
	
	// fonction qui renvoi les magasins qui concernent un certain p�rim�tre autour de l'utilisateur (non optimis� - calcul de distance directe, sans tenir compte d'un r�el algorithme)
	// la distance maximum en nombre de m�tres
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
        		// magasin associ�
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
		else
			return null;
	} 

	
}
