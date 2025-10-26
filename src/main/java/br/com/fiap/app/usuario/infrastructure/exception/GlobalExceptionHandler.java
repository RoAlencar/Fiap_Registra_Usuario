package br.com.fiap.app.usuario.infrastructure.exception;

import br.com.fiap.app.usuario.infrastructure.exception.custom.AddressRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.DuplicateEmailException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.EmailRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.LoginRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.ModificaUsuarioException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.NameRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.NewPasswordEqualsOldPasswordException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.NewPasswordRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.NoChangesDetectedException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.OldPasswordInvalidException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.OldPasswordRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.PasswordNotValidException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.PasswordRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.PasswordUpdateNotAllowedException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.TipoUsuarioRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.UserNotFoundException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.UserRequiredException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;
import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(ModificaUsuarioException.class)
        public ResponseEntity<ProblemDetail> handleModificaUsuarioException(ModificaUsuarioException ex,
                        HttpServletRequest request) {
                log.error("[Usuario - Atualiza Usuario] Não foi possivel atualizar o usuário");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(buildProblemDetail(HttpStatus.BAD_REQUEST, "Não foi possivel atualizar o usuário",
                                                request));
        }

        @ExceptionHandler(NoChangesDetectedException.class)
        public ResponseEntity<ProblemDetail> handleNoChangesDetectedException(NoChangesDetectedException ex,
                        HttpServletRequest request) {
                log.error("[Usuario - Atualiza Usuario] Não foram detectadas alterações nos dados do usuário");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(buildProblemDetail(HttpStatus.BAD_REQUEST,
                                                "Não foram detectadas alterações nos dados do usuário",
                                                request));
        }

        @ExceptionHandler(TipoUsuarioRequiredException.class)
        public ResponseEntity<ProblemDetail> handleTipoUsuarioException(TipoUsuarioRequiredException ex,
                        HttpServletRequest request) {
                log.error("[Usuario - Cadastra usuário] O Tipo de usuário é obrigatório.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(buildProblemDetail(HttpStatus.BAD_REQUEST, "O tipo de usuário é obrigatório",
                                                request));
        }

        // TODO -> Validar se essa forma vai ser efetiva E/OU achar outra forma.
        @ExceptionHandler(UserNotFoundException.class)
        public ResponseEntity<ProblemDetail> handleUserNotFoundException(UserNotFoundException ex,
                        HttpServletRequest request) {
                String httpMethod = request.getMethod();
                log.error("[Usuario - {}] O usuario não encontrado.", httpMethod);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(buildProblemDetail(HttpStatus.NOT_FOUND, "O usuario não foi encontrado",
                                                request));
        }

        @ExceptionHandler(AddressRequiredException.class)
        public ResponseEntity<ProblemDetail> handleAddressRequiredException(AddressRequiredException ex,
                        HttpServletRequest request) {
                log.warn("[Usuario - Cadastra usuário] O endereço do usuário é obrigatório");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(buildProblemDetail(HttpStatus.BAD_REQUEST, "O endereço do usuário é obrigatório",
                                                request));
        }

        @ExceptionHandler(DuplicateEmailException.class)
        public ResponseEntity<ProblemDetail> handleDuplicateEmailException(DuplicateEmailException ex,
                        HttpServletRequest request) {
                log.warn("[Usuario - Cadastra usuário] Email já cadastrado");
                return ResponseEntity.status(HttpStatus.CONFLICT)
                                .body(buildProblemDetail(HttpStatus.CONFLICT, "Email já cadastrado", request));
        }

        @ExceptionHandler(EmailRequiredException.class)
        public ResponseEntity<ProblemDetail> handleEmailRequiredException(EmailRequiredException ex,
                        HttpServletRequest request) {
                log.warn("[Usuario - Cadastra usuário] O email do usuário é obrigatório");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(buildProblemDetail(HttpStatus.BAD_REQUEST, "O email do usuário é obrigatório",
                                                request));
        }

        @ExceptionHandler(NameRequiredException.class)
        public ResponseEntity<ProblemDetail> handleNameRequiredException(NameRequiredException ex,
                        HttpServletRequest request) {
                log.warn("[Usuario - Cadastra usuário] O nome do usuário é obrigatório");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(buildProblemDetail(HttpStatus.BAD_REQUEST, "O nome do usuário é obrigatório",
                                                request));
        }

        @ExceptionHandler(LoginRequiredException.class)
        public ResponseEntity<ProblemDetail> handleLoginRequiredException(LoginRequiredException ex,
                        HttpServletRequest request) {
                log.warn("[Usuario - Cadastra usuário] O login do usuário é obrigatório");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildProblemDetail(HttpStatus.BAD_REQUEST,
                                "O login do usuário é obrigatório", request));
        }

        @ExceptionHandler(OldPasswordRequiredException.class)
        public ResponseEntity<ProblemDetail> handleOldPasswordRequiredException(OldPasswordRequiredException ex,
                        HttpServletRequest request) {
                log.warn("[Usuario - Atualizar senha] O campo 'senha' é obrigatório");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(buildProblemDetail(HttpStatus.BAD_REQUEST, "O campo senha é obrigatório",
                                                request));
        }

        @ExceptionHandler(OldPasswordInvalidException.class)
        public ResponseEntity<ProblemDetail> handleOldPasswordInvalidException(OldPasswordInvalidException ex,
                        HttpServletRequest request) {
                log.warn("[Usuario - Atualizar senha] A senha informada é inválida");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(buildProblemDetail(HttpStatus.BAD_REQUEST, "A senha informada é inválida",
                                                request));
        }

        @ExceptionHandler(NewPasswordRequiredException.class)
        public ResponseEntity<ProblemDetail> handleNewPasswordRequiredException(NewPasswordRequiredException ex,
                        HttpServletRequest request) {
                log.warn("[Usuario - Atualizar senha] O campo 'nova senha' é obrigatório");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(buildProblemDetail(HttpStatus.BAD_REQUEST, "O campo 'nova senha' é obrigatório",
                                                request));
        }

        @ExceptionHandler(NewPasswordEqualsOldPasswordException.class)
        public ResponseEntity<ProblemDetail> handleNewPasswordEqualsOldPasswordException(
                        NewPasswordEqualsOldPasswordException ex, HttpServletRequest request) {
                log.warn("[Usuario - Atualizar senha] A nova senha não pode ser igual à senha atual");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(buildProblemDetail(HttpStatus.BAD_REQUEST,
                                                "A nova senha não pode ser igual à senha atual", request));
        }

        @ExceptionHandler(PasswordRequiredException.class)
        public ResponseEntity<ProblemDetail> handlePasswordRequiredException(PasswordRequiredException ex,
                        HttpServletRequest request) {
                log.warn("[Usuario - Cadastra usuário] A senha do usuário é obrigatória");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(buildProblemDetail(HttpStatus.BAD_REQUEST, "A senha do usuário é obrigatória",
                                                request));
        }

        @ExceptionHandler(PasswordUpdateNotAllowedException.class)
        public ResponseEntity<ProblemDetail> handlePasswordUpdateNotAllowedException(
                        PasswordUpdateNotAllowedException ex,
                        HttpServletRequest request) {
                log.warn("[Usuario - Atualiza usuário] A senha do usuário só é modificada por endpoint próprio");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildProblemDetail(HttpStatus.BAD_REQUEST,
                                "A senha do usuário só é modificada por endpoint próprio", request));
        }

        @ExceptionHandler(PasswordNotValidException.class)
        public ResponseEntity<ProblemDetail> handlePasswordNotValidException(PasswordNotValidException ex,
                                                                             HttpServletRequest request) {
                log.warn("[Usuario - Atualiza senha] A senha informada é inválida");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildProblemDetail(HttpStatus.BAD_REQUEST,
                                "A senha do usuário é inválida", request));
        }

        @ExceptionHandler(UserRequiredException.class)
        public ResponseEntity<ProblemDetail> handleUserRequiredException(UserRequiredException ex,
                        HttpServletRequest request) {
                log.warn("[Usuario - Cadastra usuário] O usuário é obrigatório");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(buildProblemDetail(HttpStatus.BAD_REQUEST, "O usuário é obrigatório", request));
        }

        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<ProblemDetail> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                        HttpServletRequest request) {

                if (ex.getCause() instanceof InvalidFormatException cause && cause.getTargetType().isEnum()) {
                        String field = cause.getPath().isEmpty() ? "valor desconhecido"
                                        : cause.getPath().get(0).getFieldName();
                        String acceptedValues = Arrays.stream(cause.getTargetType().getEnumConstants())
                                        .map(Object::toString)
                                        .collect(Collectors.joining(", "));

                        String detail = String.format(
                                        "O campo '%s' recebeu um valor inválido. Valores aceitos: %s",
                                        field, acceptedValues);

                        log.warn("[Enum inválido] {} - {}", field, detail);
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                        .body(buildProblemDetail(HttpStatus.BAD_REQUEST, detail, request));
                }

                log.error("[Erro de leitura JSON] {}", ex.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body(buildProblemDetail(HttpStatus.BAD_REQUEST,
                                                "Erro ao processar a requisição. Verifique o corpo do JSON enviado.",
                                                request));
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
