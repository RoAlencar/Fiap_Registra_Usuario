package br.com.fiap.app.usuario.application.port;

import br.com.fiap.app.usuario.application.dto.request.AtualizaUsuarioDto;
import br.com.fiap.app.usuario.application.dto.response.AtualizaUsuarioResponse;
import br.com.fiap.app.usuario.infrastructure.exception.custom.ModificaUsuarioException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.UserNotFoundException;

public interface AtualizaUsuarioUseCasePort {

    AtualizaUsuarioResponse atualizaUsuario(AtualizaUsuarioDto dto) throws ModificaUsuarioException, UserNotFoundException;
}
