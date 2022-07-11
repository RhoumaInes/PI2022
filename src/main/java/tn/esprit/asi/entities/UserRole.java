package tn.esprit.asi.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IDRole;
    @Enumerated(EnumType.STRING)
    private Role Libelle;

    public UserRole(Role libelle) {
        Libelle = libelle;
    }
}
