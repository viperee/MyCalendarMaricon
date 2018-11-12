package fr.mycalendarmaricon.mycalendarmaricon.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Required;

@Entity
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotEmpty
	private String titre;
	@NotEmpty
	private String dateDebut;
	@NotEmpty
	private String dateFin;
	private String couleur;

	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Event(Long id, String titre, String dateDebut, String dateFin, String couleur) {
		super();
		this.id = id;
		this.titre = titre;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.couleur = couleur;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
