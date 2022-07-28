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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Evenement implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idEvent;
	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	private String libelle;
	private Long duree;
	private String emplacement;
	@Temporal(TemporalType.DATE)
	private Date dateCreation;
	@Column(length=8000)
	private String description;
	private Float prixEvent;
	private Long maxNumberPlaces;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="eventRatting")
	@JsonIgnore
	private Set<Ratting> listRattingEvnt;
	
	/*@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Participation> participation;*/
}
