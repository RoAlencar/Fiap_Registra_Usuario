package br.com.fiap.app.usuario.application.port;

import br.com.fiap.app.usuario.application.dto.response.BuscaUsuarioResponse;
import br.com.fiap.app.usuario.infrastructure.exception.custom.UserNotFoundException;

import java.util.List;

public interface BuscaUsuarioUseCasePort {

    List<BuscaUsuarioResponse> buscaTodosUsuarios();

    BuscaUsuarioResponse buscaUsuarioPorNome(String nome) throws UserNotFoundException;
}
