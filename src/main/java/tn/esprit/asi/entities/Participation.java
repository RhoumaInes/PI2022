package tn.esprit.asi.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@AllArgsConstructor
@NoArgsConstructor
public class Participation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idParticip;
	private Boolean status;
	private Boolean annulation;
	private Float montantPaye;
	@Temporal(TemporalType.DATE)
	private Date dateLastPaye;
	@Temporal(TemporalType.DATE)
	private Date dateParticipation;
	
	@ManyToOne
	@JsonIgnore
	private Evenement evenementPart;
	
	@ManyToOne
	@JsonIgnore
	private User userPart;
}
