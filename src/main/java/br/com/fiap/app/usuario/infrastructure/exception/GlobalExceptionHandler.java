package br.com.fiap.app.usuario.infrastructure.exception;

import br.com.fiap.app.usuario.infrastructure.exception.custom.AddressRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.DuplicateEmailException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.EmailRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.ModificaUsuarioException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.NameRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.PasswordRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.TipoUsuarioRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.UserNotFoundException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.UserRequiredException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ModificaUsuarioException.class)
    public ResponseEntity<ProblemDetail> handleModificaUsuarioException(ModificaUsuarioException ex, HttpServletRequest request) {
        log.error("[Usuario - Atualiza Usuario] Não foi possivel atualizar o usuário");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildProblemDetail(HttpStatus.BAD_REQUEST,"Não foi possivel atualizar o usuário",request));
    }

    @ExceptionHandler(TipoUsuarioRequiredException.class)
    public ResponseEntity<ProblemDetail> handleTipoUsuarioException(TipoUsuarioRequiredException ex, HttpServletRequest request) {
        log.error("[Usuario - Cadastra usuário] O Tipo de usuário é obrigatório.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildProblemDetail(HttpStatus.BAD_REQUEST,"O tipo de usuário é obrigatório",request));
    }

    //TODO -> Validar se essa forma vai ser efetiva E/OU achar outra forma.
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleUserNotFoundException(UserNotFoundException ex, HttpServletRequest request) {
        String httpMethod = request.getMethod();
        log.error("[Usuario - {}] O usuario não encontrado.", httpMethod);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildProblemDetail(HttpStatus.NOT_FOUND,"O usuario não foi encontrado.",request));
    }

    @ExceptionHandler(AddressRequiredException.class)
    public ResponseEntity<ProblemDetail> handleAddressRequiredException(AddressRequiredException ex,HttpServletRequest request) {
        log.warn("[Usuario - Cadastra usuário] O endereço do usuário é obrigatório");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildProblemDetail(HttpStatus.BAD_REQUEST,"O endereço do usuário é obrigatório",request));
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ProblemDetail> handleDuplicateEmailException(DuplicateEmailException ex, HttpServletRequest request) {
        log.warn("[Usuario - Cadastra usuário] Email já cadastrado");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(buildProblemDetail(HttpStatus.CONFLICT,"Email já cadastrado",request));
    }

    @ExceptionHandler(EmailRequiredException.class)
    public ResponseEntity<ProblemDetail> handleEmailRequiredException(EmailRequiredException ex, HttpServletRequest request) {
        log.warn("[Usuario - Cadastra usuário] O email do usuário é obrigatório");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildProblemDetail(HttpStatus.BAD_REQUEST,"O email do usuário é obrigatório",request));
    }

    @ExceptionHandler(NameRequiredException.class)
    public ResponseEntity<ProblemDetail> handleNameRequiredException(NameRequiredException ex, HttpServletRequest request) {
        log.warn("[Usuario - Cadastra usuário] O nome do usuário é obrigatório");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildProblemDetail(HttpStatus.BAD_REQUEST,"O nome do usuário é obrigatório",request));
    }

    @ExceptionHandler(PasswordRequiredException.class)
    public ResponseEntity<ProblemDetail> handlePasswordRequiredException(PasswordRequiredException ex, HttpServletRequest request) {
        log.warn("[Usuario - Cadastra usuário] A senha do usuário é obrigatória");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildProblemDetail(HttpStatus.BAD_REQUEST,"A senha do usuário é obrigatória",request));
    }

    @ExceptionHandler(UserRequiredException.class)
    public ResponseEntity<ProblemDetail> handleUserRequiredException(UserRequiredException ex, HttpServletRequest request) {
        log.warn("[Usuario - Cadastra usuário] O usuário é obrigatório");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildProblemDetail(HttpStatus.BAD_REQUEST,"O usuário é obrigatório",request));
    }


    private ProblemDetail buildProblemDetail(HttpStatus status, String detail, HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatus(status);
        problem.setTitle(status.getReasonPhrase());
        problem.setDetail(detail);
        problem.setInstance(URI.create(request.getRequestURI()));
        problem.setType(URI.create("https://api.fiap.com/errors/" + status.value()));
        return problem;
    }
}
