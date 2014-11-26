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
	

	public Commercant getInfosCommercant(float latitude, float longitude) {
		return storeDB.getInfosCommercant(latitude, longitude);
	}
	public List<Commercant> getMagasinsDansPerimetre(float latitudeClient, float longitudeClient, float distance_max) {
		return storeDB.getMagasinsDansPerimetre(latitudeClient, longitudeClient, distance_max);
	}
	public List<Commercant> orderbyPertinence(List<Article> articles)
	{
		Commercant commercant = null;
		int i = 0;
		TreeMap<Commercant, Integer> treeMap = new TreeMap<Commercant, Integer>();
		/*
		* Trying to get a map with shops associated to the number of
		* occurrences apparitions
		*/
		for (Article article : articles) {
		commercant = storeDB.getInfosCommercant(article.getLatitude_dg(),
		article.getLongitude_dg());
		if (treeMap.containsKey(commercant)) {
		i = (Integer) treeMap.get(commercant);
		treeMap.put(commercant, i++);
		} else {
		treeMap.put(commercant, 0);
		}
		}

		commercants = new ArrayList<Commercant>();
		/* Compare while there is some other shop to classify */
		while (treeMap.size() > 0) {

		/* initialize before Compare */
		int max = treeMap.get(treeMap.firstKey());
		Commercant commercantMax = treeMap.firstKey();
		/* Iterate, compare, and change commercantMax if it's needed */
		for (Commercant key : treeMap.keySet()) {
			if (max < treeMap.get(key)) {
				max = treeMap.get(key);
				commercantMax = key;
			}
		}
		commercants.add(commercantMax);

		treeMap.remove(commercantMax);

		}


		/* Return the list */

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