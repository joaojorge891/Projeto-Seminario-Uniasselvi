package br.com.uniasselvi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestControllerAdvice
public class ExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ParticipanteRegistrationException.class)
    public ResponseEntity<Map<String, String>> exception(ParticipanteRegistrationException ex) {
        return returnBadRequest(ex.getMessage());
    }

    // Em caso de excecao generica
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exception(Exception exception) {
        Map<String, String> response = prepareResponse(
                exception.getMessage(),
                "Por favor tente mais tarde ou entre em contato com o administrador",
                HttpStatus.INTERNAL_SERVER_ERROR.toString());
        log.info("Falha desconhecida.", exception);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, String> prepareResponse(String error, String solution, String status) {
        Map<String, String> response = new HashMap<>();
        response.put("Causa", error);
        response.put("Solução", solution);
        response.put("Status", status);
        return response;
    }

    private ResponseEntity<Map<String, String>> returnBadRequest(String message) {
        Map<String, String> response = prepareResponse(
                message,
                "Por favor informe uma entidade válida com restrições adequadas",
                HttpStatus.BAD_REQUEST.toString());
        log.info("Entidade não é valida.", message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
