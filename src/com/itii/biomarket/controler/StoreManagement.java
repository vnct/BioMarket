package com.itii.biomarket.controler;



import java.util.LinkedList;
import java.util.List;

import com.itii.biomarket.model.Article;
import com.itii.biomarket.model.Commercant;
import com.itii.biomarket.model.StoreDB;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class StoreManagement  {	
	
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
		return null;
	}
	public Commercant findCommercant(List<Article> articles)
	{
		return null;
	}


}