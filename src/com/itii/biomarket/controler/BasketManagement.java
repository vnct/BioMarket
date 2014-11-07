package com.itii.biomarket.controler;

import java.util.List;

import com.itii.biomarket.model.Article;
import com.itii.biomarket.model.BasketDB;

import android.content.Context;


public class BasketManagement {

	private BasketDB basketDB  = null;
	public BasketManagement(Context pContext) {
		basketDB = new BasketDB(pContext);
		basketDB.open();
	}
	public List<Article> getArticles()
	{
		return basketDB.getArticles();
	}
	public List<Article> getArticle(String nomArticle)
	{
		return basketDB.getArticle(nomArticle);
	}
	public Article getArticleAuPlusProche(String nomArticle, float latitudeClient, float longitudeClient) {
		return basketDB.getArticleAuPlusProche(nomArticle, latitudeClient, longitudeClient);
	}
	public List<Article> getBasket()
	{
		return null;
		
	}
	public void removeItem(Article article)
	{
	}
	public void addItem(Article article)
	{
	}
	
	
}
