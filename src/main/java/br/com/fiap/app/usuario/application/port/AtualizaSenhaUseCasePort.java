package br.com.fiap.app.usuario.application.port;

import br.com.fiap.app.usuario.application.dto.request.AtualizaSenhaDto;
import br.com.fiap.app.usuario.application.dto.response.AtualizaSenhaResponse;
import br.com.fiap.app.usuario.infrastructure.exception.custom.NewPasswordEqualsOldPasswordException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.NewPasswordRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.OldPasswordInvalidException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.OldPasswordRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.PasswordNotValidException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.UserNotFoundException;

public interface AtualizaSenhaUseCasePort {
    AtualizaSenhaResponse atualizaSenha(AtualizaSenhaDto dto)
            throws UserNotFoundException, PasswordNotValidException, OldPasswordRequiredException, OldPasswordInvalidException, NewPasswordRequiredException, NewPasswordEqualsOldPasswordException;
}
