package fr.mycalendarmaricon.mycalendarmaricon.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

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
	@OneToOne(cascade=CascadeType.ALL)
	private Colors couleurs;
	private boolean journeeEntiere;

	public Event() {
		super();
	}

	public Event(Long id, String titre, String dateDebut, String dateFin, Colors couleurs, boolean journeeEntiere) {
		super();
		this.id = id;
		this.titre = titre;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.couleurs = couleurs;
		this.journeeEntiere = journeeEntiere;
	}

	public Event(String titre, String dateDebut, String dateFin, Colors couleurs, boolean journeeEntiere) {
		super();
		this.titre = titre;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.couleurs = couleurs;
		this.journeeEntiere = journeeEntiere;

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

	public Colors getCouleurs() {
		return couleurs;
	}

	public void setCouleurs(Colors couleurs) {
		this.couleurs = couleurs;
	}

	public boolean isJourneeEntiere() {
		return journeeEntiere;
	}

	public void setJourneeEntiere(boolean journeeEntiere) {
		this.journeeEntiere = journeeEntiere;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
