package br.com.fiap.app.usuario.infrastructure.exception;

import br.com.fiap.app.usuario.infrastructure.exception.custom.AddressRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.DuplicateEmailException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.EmailRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.ModificaUsuarioException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.NameRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.NewPasswordEqualsOldPasswordException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.NewPasswordRequiredException;
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
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

enum DummyEnum {VALOR1, VALOR2}

public class GlobalExceptionHandlerTest {

    GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    public void handleModificaUsuarioException() {
        ModificaUsuarioException exception = new ModificaUsuarioException();
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("api/v1/usuarios");

        ResponseEntity<ProblemDetail> response =
                globalExceptionHandler.handleModificaUsuarioException(exception, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Não foi possivel atualizar o usuário", response.getBody().getDetail());
    }

    @Test
    public void handleTipoUsuarioException() {
        TipoUsuarioRequiredException exception = new TipoUsuarioRequiredException();
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("api/v1/usuarios");

        ResponseEntity<ProblemDetail> response =
                globalExceptionHandler.handleTipoUsuarioException(exception, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("O tipo de usuário é obrigatório", response.getBody().getDetail());
    }

    @Test
    public void handleUserNotFoundException() {
        UserNotFoundException exception = new UserNotFoundException();
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("api/v1/usuarios");

        ResponseEntity<ProblemDetail> response =
                globalExceptionHandler.handleUserNotFoundException(exception, request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("O usuario não foi encontrado", response.getBody().getDetail());
    }

    @Test
    public void handleAddressRequiredException() {
        AddressRequiredException exception = new AddressRequiredException();
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("api/v1/usuarios");

        ResponseEntity<ProblemDetail> response =
                globalExceptionHandler.handleAddressRequiredException(exception, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("O endereço do usuário é obrigatório", response.getBody().getDetail());
    }

    @Test
    public void handleDuplicateEmailException() {
        DuplicateEmailException exception = new DuplicateEmailException();
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("api/v1/usuarios");

        ResponseEntity<ProblemDetail> response =
                globalExceptionHandler.handleDuplicateEmailException(exception, request);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Email já cadastrado", response.getBody().getDetail());
    }

    @Test
    public void handleEmailRequiredException() {
        EmailRequiredException exception = new EmailRequiredException();
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("api/v1/usuarios");

        ResponseEntity<ProblemDetail> response =
                globalExceptionHandler.handleEmailRequiredException(exception, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("O email do usuário é obrigatório", response.getBody().getDetail());
    }

    @Test
    public void handleNameRequiredException() {
        NameRequiredException exception = new NameRequiredException();
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("api/v1/usuarios");

        ResponseEntity<ProblemDetail> response =
                globalExceptionHandler.handleNameRequiredException(exception, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("O nome do usuário é obrigatório", response.getBody().getDetail());
    }

    @Test
    public void handleOldPasswordRequiredException() {
        OldPasswordRequiredException exception = new OldPasswordRequiredException();
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("api/v1/usuarios");

        ResponseEntity<ProblemDetail> response =
                globalExceptionHandler.handleOldPasswordRequiredException(exception, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("O campo senha antiga é obrigatório", response.getBody().getDetail());
    }

    @Test
    public void handleOldPasswordInvalidException() {
        OldPasswordInvalidException exception = new OldPasswordInvalidException();
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("api/v1/usuarios");

        ResponseEntity<ProblemDetail> response =
                globalExceptionHandler.handleOldPasswordInvalidException(exception, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("A senha antiga informada é inválida", response.getBody().getDetail());
    }

    @Test
    public void handleNewPasswordRequiredException() {
        NewPasswordRequiredException exception = new NewPasswordRequiredException();
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("api/v1/usuarios");

        ResponseEntity<ProblemDetail> response =
                globalExceptionHandler.handleNewPasswordRequiredException(exception, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("O campo 'nova senha' é obrigatório", response.getBody().getDetail());
    }

    @Test
    public void handleNewPasswordEqualsOldPasswordException() {
        NewPasswordEqualsOldPasswordException exception = new NewPasswordEqualsOldPasswordException();
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("api/v1/usuarios");

        ResponseEntity<ProblemDetail> response =
                globalExceptionHandler.handleNewPasswordEqualsOldPasswordException(exception, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("A nova senha não pode ser igual à senha atual", response.getBody().getDetail());
    }

    @Test
    public void handlePasswordRequiredException() {
        PasswordRequiredException exception = new PasswordRequiredException();
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("api/v1/usuarios");

        ResponseEntity<ProblemDetail> response =
                globalExceptionHandler.handlePasswordRequiredException(exception, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("A senha do usuário é obrigatória", response.getBody().getDetail());
    }

    @Test
    public void handlePasswordUpdateNotAllowedException() {
        PasswordUpdateNotAllowedException exception = new PasswordUpdateNotAllowedException();
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("api/v1/usuarios");

        ResponseEntity<ProblemDetail> response =
                globalExceptionHandler.handlePasswordUpdateNotAllowedException(exception, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("A senha do usuário só é modificada por endpoint próprio", response.getBody().getDetail());
    }

    @Test
    public void handlePasswordNotValidException() {
        PasswordNotValidException exception = new PasswordNotValidException();
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("api/v1/usuarios");

        ResponseEntity<ProblemDetail> response =
                globalExceptionHandler.handlePasswordNotValidException(exception, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("A senha do usuário é inválida", response.getBody().getDetail());
    }

    @Test
    public void handleUserRequiredException() {
        UserRequiredException exception = new UserRequiredException();
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("api/v1/usuarios");

        ResponseEntity<ProblemDetail> response =
                globalExceptionHandler.handleUserRequiredException(exception, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("O usuário é obrigatório", response.getBody().getDetail());
    }

    @Test
    void handleHttpMessageNotReadable_withEnumInvalidValue() {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("/usuarios");

        // Simula InvalidFormatException para enum
        InvalidFormatException cause = mock(InvalidFormatException.class);
        when(cause.getTargetType()).thenReturn((Class) DummyEnum.class);
        when(cause.getPath()).thenReturn(java.util.Collections.emptyList());

        HttpMessageNotReadableException exception = mock(HttpMessageNotReadableException.class);
        when(exception.getCause()).thenReturn(cause);

        // Act
        ResponseEntity<ProblemDetail> response =
                globalExceptionHandler.handleHttpMessageNotReadable(exception, request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getDetail().contains("O campo"));
        assertTrue(response.getBody().getDetail().contains("VALOR1, VALOR2"));
    }

}

