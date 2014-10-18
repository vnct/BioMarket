package com.itii.biomarket.model;

public class Commercant {
	private long id;
	private String nom;
	private String tel;
	private String adr_cp;
	private String adr_numero_rue;
	private String adr_nom_rue;
	private String adr_ville;
	private float latitude_dg;
	private float longitude_dg;
	
	public Commercant(long id, String nom, String tel, String adr_numero_rue, String adr_nom_rue, String adr_cp, String adr_ville, float latitude_dg, float longitude_dg) {
		super();
		this.id = id;
		this.nom = nom;
		this.tel = tel;
		this.adr_cp = adr_cp;
		this.adr_numero_rue = adr_numero_rue;
		this.adr_nom_rue = adr_nom_rue;
		this.adr_ville = adr_ville;
		this.latitude_dg = latitude_dg;
		this.longitude_dg = longitude_dg;
	}
	
	public Commercant(long id, String nom, float latitude_dg, float longitude_dg) {
		super();
		this.id = id;
		this.nom = nom;
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
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * @return the adr_cp
	 */
	public String getAdr_cp() {
		return adr_cp;
	}
	/**
	 * @param adr_cp the adr_cp to set
	 */
	public void setAdr_cp(String adr_cp) {
		this.adr_cp = adr_cp;
	}
	/**
	 * @return the adr_numero_rue
	 */
	public String getAdr_numero_rue() {
		return adr_numero_rue;
	}
	/**
	 * @param adr_numero_rue the adr_numero_rue to set
	 */
	public void setAdr_numero_rue(String adr_numero_rue) {
		this.adr_numero_rue = adr_numero_rue;
	}
	/**
	 * @return the adr_nom_rue
	 */
	public String getAdr_nom_rue() {
		return adr_nom_rue;
	}
	/**
	 * @param adr_nom_rue the adr_nom_rue to set
	 */
	public void setAdr_nom_rue(String adr_nom_rue) {
		this.adr_nom_rue = adr_nom_rue;
	}
	/**
	 * @return the adr_ville
	 */
	public String getAdr_ville() {
		return adr_ville;
	}
	/**
	 * @param adr_ville the adr_ville to set
	 */
	public void setAdr_ville(String adr_ville) {
		this.adr_ville = adr_ville;
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
