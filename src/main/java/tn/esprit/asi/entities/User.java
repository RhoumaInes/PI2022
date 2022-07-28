package tn.esprit.asi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("idUser")
    private Long IDUser;
    @Column(nullable = false, unique = true, length = 50)
    @JsonProperty("username")
    private String UserName;
    @JsonIgnore
    private String image;
    @Column(nullable = false, unique = true)
    @JsonProperty("email")
    private String Email;
    @Transient
    //@JsonIgnore
    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    private String Password;
    @Column(nullable = false, name = "Password")
    @JsonIgnore
    //@JsonProperty("encrypassword")
    private String EncryPassword;
    @Column(nullable = false)
    @JsonProperty("nom")
    private String Nom;
    @Column(nullable = false)
    @JsonProperty("prenom")
    private String Prenom;
    @Column(nullable = false)
    @JsonProperty("age")
    private int Age;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @JsonProperty("datenaissance")
    private Date DateNaissance;
    @JsonProperty("titreprofile")
    private String TitreProfile;
    @JsonProperty("posteactuel")
    private String PosteActuel;
    @JsonProperty("secteur")
    private String Secteur;
    @JsonProperty("pays")
    private String Pays;
    @JsonProperty("ville")
    private String Ville;
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    //@JsonProperty("etat")
    private UserState Etat;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @JsonIgnore
    //@JsonProperty("dateinsertion")
    private Date DateInsertion;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @JsonIgnore
    //@JsonProperty("datemodification")
    private Date DateModification;
    @JsonIgnore
    //@JsonProperty("emailverifykey")
    private String EmailVerifyKey;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    //@JsonProperty("dateemailverifykey")
    private Date DateEmailVerifyKey;
    //@JsonProperty(value = "passwordresetKey")
    @JsonIgnore
    private String PasswordResetKey;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    //@JsonProperty("datepasswordresetKey")
    private Date DatePasswordResetKey;

    @OneToOne
    //@JsonProperty("userrole")
    @JsonIgnore
    private UserRole UserRole;

    public User(String userName, String email, String password, String nom, String prenom, int age, Date dateNaissance, String titreProfile, String posteActuel, String secteur, String pays, String ville) {
        UserName = userName;
        Email = email;
        Password = password;
        Nom = nom;
        Prenom = prenom;
        Age = age;
        DateNaissance = dateNaissance;
        TitreProfile = titreProfile;
        PosteActuel = posteActuel;
        Secteur = secteur;
        Pays = pays;
        Ville = ville;
    }
}
