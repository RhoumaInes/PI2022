package tn.esprit.asi.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
public class Badge implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Boolean isTrophy;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getIsTrophy() {
		return isTrophy;
	}
	public void setIsTrophy(Boolean isTrophy) {
		this.isTrophy = isTrophy;
	}
	public Badge() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
