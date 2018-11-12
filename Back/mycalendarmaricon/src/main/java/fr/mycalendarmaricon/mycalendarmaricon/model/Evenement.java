package fr.mycalendarmaricon.mycalendarmaricon.model;

public class Evenement {
	private String titre;
	private String dateDebut;
	private String dateFin;
	private String couleur;

	public Evenement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Evenement(String titre, String dateDebut, String dateFin, String couleur) {
		super();
		this.titre = titre;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.couleur = couleur;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
}
