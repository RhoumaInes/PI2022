package tn.esprit.asi.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Evaluation implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer note;
	@Temporal(TemporalType.DATE)
	private Date date;

	@OneToMany
	@JsonIgnore
	private User IDUserTo;

	@OneToMany
	@JsonIgnore
	private User IDUserFrom;
}
