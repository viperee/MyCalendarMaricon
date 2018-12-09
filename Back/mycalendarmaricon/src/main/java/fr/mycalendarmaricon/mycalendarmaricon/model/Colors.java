package fr.mycalendarmaricon.mycalendarmaricon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Colors {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "_primary")
	@NotEmpty
	private String primary;
	@Column(name = "_secondary")
	@NotEmpty
	private String secondary;

	public Colors() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Colors(Long id, @NotEmpty String primary, @NotEmpty String secondary) {
		super();
		this.id = id;
		this.primary = primary;
		this.secondary = secondary;
	}

	public Colors(@NotEmpty String primary, @NotEmpty String secondary) {
		super();
		this.primary = primary;
		this.secondary = secondary;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrimary() {
		return primary;
	}

	public void setPrimary(String primary) {
		this.primary = primary;
	}

	public String getSecondary() {
		return secondary;
	}

	public void setSecondary(String secondary) {
		this.secondary = secondary;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((primary == null) ? 0 : primary.hashCode());
		result = prime * result + ((secondary == null) ? 0 : secondary.hashCode());
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
		Colors other = (Colors) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		}
		return true;
	}

}
