package br.com.cep.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class DefaultError {

    private HttpStatus httpStatus;
    private int code;
    private String message;
}
