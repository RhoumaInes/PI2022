package tn.esprit.asi.Utils;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class BodyResponse<T> {
    private String Message;
    @Enumerated(EnumType.STRING)
    private ResponseStatus Status;
    private T Data;
}
