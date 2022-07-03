package tn.esprit.asi.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Like implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id  // cle primaire 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idLike;
	@Temporal(TemporalType.DATE)
	private Date Date;
	private Boolean Statut;
	
	@ManyToOne
	Commentaire Commentaire;
	
	@Column(name="Publication")
	private String Publication;
	
}
