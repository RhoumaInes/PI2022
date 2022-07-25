package tn.esprit.asi.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SignUpRequest {
    private String nom;
    private String prenom;
    private int age;
    private String username;
    private Date datenaissance;
    private String email;
    private String password;
}
