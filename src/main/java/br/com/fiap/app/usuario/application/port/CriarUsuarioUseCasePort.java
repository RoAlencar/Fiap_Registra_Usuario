package br.com.fiap.app.usuario.application.port;

import br.com.fiap.app.usuario.application.dto.request.CriarUsuarioDto;
import br.com.fiap.app.usuario.application.dto.response.CriarUsuarioResponse;
import br.com.fiap.app.usuario.infrastructure.exception.custom.AddressRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.DuplicateEmailException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.EmailRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.NameRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.PasswordRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.UserRequiredException;

public interface CriarUsuarioUseCasePort {

    CriarUsuarioResponse criarUsuario(CriarUsuarioDto dto) throws AddressRequiredException, DuplicateEmailException, EmailRequiredException, PasswordRequiredException, UserRequiredException, NameRequiredException;
}
