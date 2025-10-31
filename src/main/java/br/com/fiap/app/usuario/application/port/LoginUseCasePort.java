package br.com.fiap.app.usuario.application.port;

import br.com.fiap.app.usuario.application.dto.request.LoginDto;
import br.com.fiap.app.usuario.application.dto.response.LoginResponse;
import br.com.fiap.app.usuario.infrastructure.exception.custom.LoginUserNotFoundException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.LoginPasswordInvalidException;

public interface LoginUseCasePort {
    LoginResponse login(LoginDto dto)
            throws LoginUserNotFoundException, LoginPasswordInvalidException;
}