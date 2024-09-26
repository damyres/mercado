package lista.supermercado.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ResponseError(String message, HttpStatus badRequest, LocalDateTime time) {

}



