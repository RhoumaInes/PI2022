package tn.esprit.asi.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("idrole")
    private Long IDRole;
    @Enumerated(EnumType.STRING)
    @JsonProperty("libelle")
    private Role Libelle;

    public UserRole(Role libelle) {
        Libelle = libelle;
    }
}
