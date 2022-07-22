package tn.esprit.asi.payload;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class ApiResponse<T> {
    private String Message;
    @Enumerated(EnumType.STRING)
    private ResponseStatus Status;
    private T Data;
}
