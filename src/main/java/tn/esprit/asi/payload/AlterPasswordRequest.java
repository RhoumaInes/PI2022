package tn.esprit.asi.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlterPasswordRequest {
    private String username;
    private String newpassword;
    private String password;
}
