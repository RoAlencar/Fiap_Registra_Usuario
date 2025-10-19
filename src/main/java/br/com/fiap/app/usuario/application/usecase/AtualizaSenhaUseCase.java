package br.com.fiap.app.usuario.application.usecase;

import java.time.LocalDateTime;

import br.com.fiap.app.usuario.infrastructure.exception.custom.NewPasswordEqualsOldPasswordException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.NewPasswordRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.OldPasswordInvalidException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.OldPasswordRequiredException;
import org.springframework.stereotype.Service;

import br.com.fiap.app.usuario.application.dto.request.AtualizaSenhaDto;
import br.com.fiap.app.usuario.application.dto.response.AtualizaSenhaResponse;
import br.com.fiap.app.usuario.application.port.AtualizaSenhaUseCasePort;
import br.com.fiap.app.usuario.application.port.UsuarioRepositoryPort;
import br.com.fiap.app.usuario.domain.Usuario;
import br.com.fiap.app.usuario.infrastructure.exception.custom.PasswordNotValidException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.UserNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AtualizaSenhaUseCase implements AtualizaSenhaUseCasePort {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    @Override
    public AtualizaSenhaResponse atualizaSenha(AtualizaSenhaDto dto)
            throws UserNotFoundException, PasswordNotValidException, OldPasswordRequiredException, OldPasswordInvalidException, NewPasswordRequiredException, NewPasswordEqualsOldPasswordException {

        Usuario usuarioExistente = usuarioRepositoryPort.findById(dto.getUsuarioId())
                .orElseThrow(UserNotFoundException::new);

        validarSenha(dto, usuarioExistente);

        try {
            usuarioExistente.setSenha(dto.getNovaSenha());
            usuarioExistente.setDataUltimaAtualizacao(LocalDateTime.now());

            usuarioRepositoryPort.save(usuarioExistente);
            return new AtualizaSenhaResponse("Senha atualizada com sucesso.");
        } catch (Exception e) {
            throw new PasswordNotValidException();
        }
    }

    private static void validarSenha(AtualizaSenhaDto dto, Usuario usuarioExistente) throws OldPasswordRequiredException, OldPasswordInvalidException, NewPasswordRequiredException, NewPasswordEqualsOldPasswordException {
        if (dto.getSenhaAtual() == null || dto.getSenhaAtual().isBlank()){
            throw new OldPasswordRequiredException();
        }

        if(!usuarioExistente.getSenha().equals(dto.getSenhaAtual())){
            throw new OldPasswordInvalidException();
        }

        if(dto.getNovaSenha() == null || dto.getNovaSenha().isBlank()){
            throw new NewPasswordRequiredException();
        }

        if(usuarioExistente.getSenha().equals(dto.getNovaSenha())){
            throw new NewPasswordEqualsOldPasswordException();
        }
    }
}
