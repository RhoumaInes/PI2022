package tn.esprit.asi.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Commentaire implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id  // cle primaire 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idCom;
	@Temporal(TemporalType.DATE)
	private Date Date;
	private String contenu;
	
	@ManyToOne
	PublicationForum Publication;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="Commentaire")
	private Set<Like> Likes;
	
	@OneToMany(cascade = CascadeType.REMOVE)
	private Set<Commentaire> Commentaire; //?????

}
