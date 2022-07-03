package tn.esprit.asi.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Offre implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idOffre;
	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	private String libelle;
	private Long duree;
	@Temporal(TemporalType.DATE)
	private Date dateCreation;
	private String description;
	@ManyToOne
	@JoinColumn(name="fk_id_partenaire")
	private Partenaire partenaireOffre;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="offreRatting")
	private Set<Ratting> listRattingOffre;
}
