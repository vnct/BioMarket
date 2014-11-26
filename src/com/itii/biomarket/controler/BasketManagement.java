package com.itii.biomarket.controler;

import java.sql.SQLException;
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
	}

	public List<Article> getArticles() throws SQLException {
		return basketDB.getArticles();
	}

	public List<Article> getArticle(String nomArticle) throws SQLException {
		return basketDB.getArticle(nomArticle);
	}

	public Article getArticleAuPlusProche(String nomArticle,
			float latitudeClient, float longitudeClient) throws SQLException {
		return basketDB.getArticleAuPlusProche(nomArticle, latitudeClient,
				longitudeClient);
	}

	public List<Article> getBasket() {
		return articles;
		// Partie a re-Commiter si mes modifs sont pas bonnes, JEROME.
		// return null;
	}

	public void removeItem(Article article) {
		if (articles.contains(article)) {
			articles.remove(article);
		}
		// Tout supprimer si mes modifs sont nulles, JEROME.
	}

	public void addItem(Article article) {
		articles.add(article);
		// Tout supprimer si mes modifs sont nulles, JEROME.
	}
	public void removeBasket() {
		
		
	}
	public void addItem(String nomArticle) {
		
	}
}
