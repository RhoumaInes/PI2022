package tn.esprit.asi.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Actualite implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id  // cle primaire 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idAct;
	@Temporal(TemporalType.DATE)
	private Date Date;
	private String intitule;
	private String theme;
	private String contenu;																								


}
