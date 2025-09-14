package br.com.fiap.app.usuario.application.port;

import br.com.fiap.app.usuario.application.dto.request.CriarUsuarioDto;
import br.com.fiap.app.usuario.application.dto.response.CriarUsuarioResponse;

public interface CriarUsuarioUseCasePort {

    CriarUsuarioResponse criarUsuario(CriarUsuarioDto dto);
}
