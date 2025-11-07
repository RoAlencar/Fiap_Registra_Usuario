package br.com.fiap.app.usuario.application.port;

import br.com.fiap.app.usuario.infrastructure.exception.custom.UserNotFoundException;

public interface DeletaUsuarioUseCasePort {

    void deletaUsuario(Long id) throws UserNotFoundException;
}
