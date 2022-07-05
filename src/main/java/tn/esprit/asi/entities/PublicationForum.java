package tn.esprit.asi.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class PublicationForum implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id  // cle primaire 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idPub;
	@Temporal(TemporalType.DATE)
	private Date date;
	private String intitule;
	private String theme;
	private String contenu;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="publication")
	private Set<Commentaire> commentaire;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="publication")
	private Set<ToLike> Tolikes;
	
	

}
