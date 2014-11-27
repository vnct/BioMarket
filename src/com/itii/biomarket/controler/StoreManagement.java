package com.itii.biomarket.controler;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import com.itii.biomarket.model.Article;
import com.itii.biomarket.model.Commercant;
import com.itii.biomarket.model.StoreDB;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class StoreManagement  {	
	private List<Commercant> commercants = null;
	private StoreDB storeDB = null;
	public StoreManagement(Context pContext) {
		storeDB = new StoreDB(pContext);
		storeDB.open();
	}

	public List<Commercant> getAllCommercant()
	{
		return storeDB.getMagasins();
	}
	public Commercant getInfosCommercant(float latitude, float longitude) {
		return storeDB.getInfosCommercant(latitude, longitude);
	}
	public List<Commercant> getMagasinsDansPerimetre(float latitudeClient, float longitudeClient, float distance_max) {
		return storeDB.getMagasinsDansPerimetre(latitudeClient, longitudeClient, distance_max);
	}
	/*public List<Commercant> orderbyPertinence(List<Article> articles)
	{
		TreeMap<Commercant, Integer> treeMap = new TreeMap<Commercant, Integer>();
		
		System.out.println(commercants.size());
		for (Article article : articles) {
			
			List<Commercant> commercants = storeDB.getMagasins();
			for(Commercant com :  commercants)
			{
				if(com.getLatitude_dg()==article.getLatitude_dg())
				{
					if(com.getLongitude_dg()==article.getLongitude_dg())
					{
						if (treeMap.containsKey(com)) {
							Integer i = (Integer) treeMap.get(com);
							treeMap.remove(com);
							treeMap.put(com, i++);
						} else {
							treeMap.put(com, 0);
						}
					}
				}
			}
		}
		commercants = new ArrayList<Commercant>();
		for (TreeMap.Entry<Commercant, Integer> entree : treeMap.entrySet()) {
			
			
			System.out.println("Clé : "+entree.getKey()+" Valeur : "+entree.getValue());
		
		}
		while (treeMap.size() > 0) {

			
			int max = treeMap.get(treeMap.firstKey());
			Commercant commercantMax = treeMap.firstKey();
		
			for (Commercant key : treeMap.keySet()) {
				if (max < treeMap.get(key)) {
					max = treeMap.get(key);
					commercantMax = key;
				}
			}
			commercants.add(commercantMax);

			treeMap.remove(commercantMax);

		}
		
		return commercants;
		
	}*/
	public Commercant getCommercantHavingTheProduct(Article article) {

		Commercant commercant = null;

		List<Commercant> commercantsList = storeDB.getMagasins();

		for (Commercant commercant2 : commercantsList) {

			if (article.getLatitude_dg() == commercant2.getLatitude_dg()

					&& article.getLongitude_dg() == commercant2

					.getLongitude_dg()) {

				commercant = commercant2;

			}

		}

		return commercant;

	}


	public List<Commercant> orderbyPertinence(List<Article> articles) {

		Commercant commercant = null;


		int i = 0;

		TreeMap<Commercant, Integer> treeMap = new TreeMap<Commercant, Integer>();


		/*

		 * Trying to get a map with shops associated to the number of

		 * occurrences apparitions

		 */


		for (Article article : articles) {

			commercant = getCommercantHavingTheProduct(article);

			/* ANcien code avant "commercant = ":storeDB.getInfosCommercant(article.getLatitude_dg(),

		article.getLongitude_dg());*/

			if (treeMap.containsKey(commercant)) {

				i = (Integer) treeMap.get(commercant);
				
				treeMap.put(commercant, i++);

			} else {

				treeMap.put(commercant, 1);

			}

		}

		commercants = new ArrayList<Commercant>();
		
		

		for (TreeMap.Entry<Commercant, Integer> entree : treeMap.entrySet()) {
			commercants.add(entree.getKey());
		
		}




	

		return commercants;

	}
	public Commercant findCommercant(List<Article> articles)
	{
		return orderbyPertinence(articles).get(0);
	}
	public List<Commercant> findAllCommercants(List<Article> articles)
	{
		return orderbyPertinence(articles);
	}


}