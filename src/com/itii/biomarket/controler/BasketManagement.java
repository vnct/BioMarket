package com.itii.biomarket.controler;

import java.sql.SQLException;
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
	public List<Article> getArticles() throws SQLException
	{
		return basketDB.getArticles();
	}
	public List<Article> getArticle(String nomArticle) throws SQLException
	{
		return basketDB.getArticle(nomArticle);
	}
	public Article getArticleAuPlusProche(String nomArticle, float latitudeClient, float longitudeClient) throws SQLException {
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
