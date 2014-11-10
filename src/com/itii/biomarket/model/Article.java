package com.itii.biomarket.model;

public class Article {
	private long id;
	private String nom;
	private String origine;
	private String categorie;
	private float latitude_dg;
	private float longitude_dg;
	


	public Article(long id, String nom, String origine, String categorie) {
		super();
		this.id = id;
		this.nom = nom;
		this.origine = origine;
		this.categorie = categorie;
		this.latitude_dg = -1;
		this.longitude_dg = -1;
	}
	
	public Article(long id, String nom, String origine, String categorie, float latitude_dg, float longitude_dg) {
		super();
		this.id = id;
		this.nom = nom;
		this.origine = origine;
		this.categorie = categorie;
		this.latitude_dg = latitude_dg;
		this.longitude_dg = longitude_dg;
	}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the origine
	 */
	public String getOrigine() {
		return origine;
	}
	/**
	 * @param origine the origine to set
	 */
	public void setOrigine(String origine) {
		this.origine = origine;
	}
	/**
	 * @return the categorie
	 */
	public String getCategorie() {
		return categorie;
	}
	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	/**
	 * @return the latitude_dg
	 */
	public float getLatitude_dg() {
		return latitude_dg;
	}

	/**
	 * @param latitude_dg the latitude_dg to set
	 */
	public void setLatitude_dg(float latitude_dg) {
		this.latitude_dg = latitude_dg;
	}

	/**
	 * @return the longitude_dg
	 */
	public float getLongitude_dg() {
		return longitude_dg;
	}

	/**
	 * @param longitude_dg the longitude_dg to set
	 */
	public void setLongitude_dg(float longitude_dg) {
		this.longitude_dg = longitude_dg;
	}
	
}
