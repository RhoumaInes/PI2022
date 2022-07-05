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
	private Date dateCom;
	private String contenu;
	
	@ManyToOne
	PublicationForum publication;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="commentaire")
	private Set<ToLike> listlikes;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Commentaire> comments; // c bn 

	

}
