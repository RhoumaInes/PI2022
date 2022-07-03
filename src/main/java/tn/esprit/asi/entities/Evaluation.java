package tn.esprit.asi.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
<<<<<<< HEAD:src/main/java/tn/esprit/asi/entities/Ratting.java
public class Ratting implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
=======
public class Evaluation implements Serializable {
>>>>>>> 9c1128fd9a2f5b89e78183b56603216a38e3b888:src/main/java/tn/esprit/asi/entities/Evaluation.java
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer note;
	@Temporal(TemporalType.DATE)
	private Date date;
<<<<<<< HEAD:src/main/java/tn/esprit/asi/entities/Ratting.java
	@ManyToOne
	@JoinColumn(name="fk_id_event")
	private Evenement eventRatting;
	@ManyToOne
	@JoinColumn(name="fk_id_offre")
	private Offre offreRatting;
=======
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getNote() {
		return note;
	}
	public void setNote(Integer note) {
		this.note = note;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Evaluation() {
		super();
	}
>>>>>>> 9c1128fd9a2f5b89e78183b56603216a38e3b888:src/main/java/tn/esprit/asi/entities/Evaluation.java
}
