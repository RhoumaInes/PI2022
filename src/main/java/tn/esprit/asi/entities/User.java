package tn.esprit.asi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IDUser;
    @Column(nullable = false, unique = true, length = 50)
    private String UserName;
    @Column(nullable = false, unique = true)
    private String Email;
    @Transient
    @JsonIgnore
    private String Password;
    @Column(nullable = false, name = "Password")
    @JsonIgnore
    private String EncryPassword;
    @Column(nullable = false)
    private String Nom;
    @Column(nullable = false)
    private String Prenom;
    @Column(nullable = false)
    private int Age;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date DateNaissance;
    private String TitreProfile;
    private String PosteActuel;
    private String Secteur;
    private String Pays;
    private String Ville;
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private UserState Etat;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @JsonIgnore
    private Date DateInsertion;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @JsonIgnore
    private Date DateModification;
    @JsonIgnore
    private String EmailVerifyKey;
    @Temporal(TemporalType.DATE)
    @JsonIgnore
    private Date DateEmailVerifyKey;
    @JsonIgnore
    private String PasswordResetKey;
    @Temporal(TemporalType.DATE)
    @JsonIgnore
    private Date DatePasswordResetKey;

    @OneToOne
    private UserRole UserRole;
}
