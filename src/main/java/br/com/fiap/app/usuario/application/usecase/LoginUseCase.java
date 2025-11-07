package br.com.fiap.app.usuario.application.usecase;

import br.com.fiap.app.usuario.application.dto.request.LoginDto;
import br.com.fiap.app.usuario.application.dto.response.LoginResponse;
import br.com.fiap.app.usuario.application.port.LoginUseCasePort;
import br.com.fiap.app.usuario.application.port.UsuarioRepositoryPort;
import br.com.fiap.app.usuario.domain.Usuario;
import br.com.fiap.app.usuario.infrastructure.exception.custom.LoginUserNotFoundException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.LoginPasswordInvalidException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUseCase implements LoginUseCasePort {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    @Override
    public LoginResponse login(LoginDto dto)
            throws LoginUserNotFoundException, LoginPasswordInvalidException {

        Usuario usuario = usuarioRepositoryPort.findByLogin(dto.getLogin())
                .orElseThrow(LoginUserNotFoundException::new);

        if (!usuario.getSenha().equals(dto.getSenha())) {
            throw new LoginPasswordInvalidException();
        }
        return LoginResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .tipo(usuario.getTipo().name())
                .mensagem("Login realizado com sucesso")
                .build();
    }
}