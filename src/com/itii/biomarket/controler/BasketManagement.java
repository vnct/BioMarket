package com.itii.biomarket.controler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itii.biomarket.model.Article;
import com.itii.biomarket.model.BasketDB;

import android.content.Context;

public class BasketManagement {
	private BasketDB basketDB = null;
	private List<Article> articles = null;

	public BasketManagement(Context pContext) {
		basketDB = new BasketDB(pContext);
		basketDB.open();
		articles = new ArrayList<Article>();
	}

	/*
	 * Récupere les articles de la BDD
	 */
	public List<Article> getArticles() throws SQLException {
		return basketDB.getArticles();
	}

	/*
	 * Récupere les articles en fonction de son nom
	 */
	public List<Article> getArticle(String nomArticle) throws SQLException {
		return basketDB.getArticle(nomArticle);
	}

	
	/*
	 * Récupère les articles au plus proche
	 * 
	 */
	public Article getArticleAuPlusProche(String nomArticle,
			float latitudeClient, float longitudeClient) throws SQLException {
		return basketDB.getArticleAuPlusProche(nomArticle, latitudeClient,
				longitudeClient);
	}

	/* 
	 * R2cupère le basket
	 */
	public List<Article> getBasket() {
		//System.out.println(articles.size());
		return articles;
		// Partie a re-Commiter si mes modifs sont pas bonnes, JEROME.
		// return null;
	}

	/*
	 * SUpprime un article du basket
	 */
	public void removeItem(Article article) {
		if (articles.contains(article)) {
			articles.remove(article);
		}
		// Tout supprimer si mes modifs sont nulles, JEROME.
	}

	/*
	 * Ajout un article dans le basket
	 */
	public void addItem(Article article) {
		articles.add(article);
		// Tout supprimer si mes modifs sont nulles, JEROME.
	}
	/*
	 * 
	 * Supprime le basket
	 */
	public void removeBasket() {
		
		articles.clear();
	}
	/*
	 * Ajoute un item en fonction de son nom
	 */
	public Boolean addItem(String nomArticle)  {
		
		List<Article> articleslist = new ArrayList<Article>();
		try {
			articleslist = getArticle(nomArticle);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
		
		if(articleslist!=null)
		{
			articles.add(articleslist.get(0));
			return true;
		}
		else
		{
			return false;
		}
			
		
	}
}
