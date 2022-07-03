package tn.esprit.asi.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Partenaire implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idPartenaire;
	private String nomPartenaire;
	private String adrPartenaire;
	private SecteurActivite secteur;
	@Temporal(TemporalType.DATE)
	private Date dateCreation;
	private String description;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="partenaireOffre")
	private Set<Offre> listeOffres;

}
