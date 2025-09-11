package br.com.fiap.app.usuario.infrastructure.exception;

import br.com.fiap.app.usuario.infrastructure.exception.custom.AddressRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.DuplicateEmailException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.EmailRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.NameRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.PasswordRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.UserRequiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AddressRequiredException.class)
    public ResponseEntity<ObjectNode> handleAddressRequiredException(AddressRequiredException ex) {
        log.warn("[Usuario - Cadastra usuário] O endereço do usuário é obrigatório");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageConverter("O endereço do usuário é obrigatório"));
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ObjectNode> handleDuplicateEmailException(DuplicateEmailException ex) {
        log.warn("[Usuario - Cadastra usuário] Email já cadastrado");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(messageConverter("Email já cadastrado"));
    }

    @ExceptionHandler(EmailRequiredException.class)
    public ResponseEntity<ObjectNode> handleEmailRequiredException(EmailRequiredException ex) {
        log.warn("[Usuario - Cadastra usuário] O email do usuário é obrigatório");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageConverter("O email do usuário é obrigatório"));
    }

    @ExceptionHandler(NameRequiredException.class)
    public ResponseEntity<ObjectNode> handleNameRequiredException(NameRequiredException ex) {
        log.warn("[Usuario - Cadastra usuário] O nome do usuário é obrigatório");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageConverter("O nome do usuário é obrigatório"));
    }

    @ExceptionHandler(PasswordRequiredException.class)
    public ResponseEntity<ObjectNode> handlePasswordRequiredException(PasswordRequiredException ex) {
        log.warn("[Usuario - Cadastra usuário] A senha do usuário é obrigatória");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageConverter("A senha do usuário é obrigatória"));
    }

    @ExceptionHandler(UserRequiredException.class)
    public ResponseEntity<ObjectNode> handleUserRequiredException(UserRequiredException ex) {
        log.warn("[Usuario - Cadastra usuário] O usuário é obrigatório");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messageConverter("O usuário é obrigatório"));
    }


    ObjectNode messageConverter(String message) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();
        json.put("message", message);
        return json;
    }
}
