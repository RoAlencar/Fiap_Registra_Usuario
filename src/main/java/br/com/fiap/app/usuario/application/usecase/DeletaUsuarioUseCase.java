package br.com.fiap.app.usuario.application.usecase;


import br.com.fiap.app.usuario.application.port.DeletaUsuarioUseCasePort;
import br.com.fiap.app.usuario.application.port.UsuarioRepositoryPort;
import br.com.fiap.app.usuario.domain.Usuario;
import br.com.fiap.app.usuario.infrastructure.exception.custom.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletaUsuarioUseCase implements DeletaUsuarioUseCasePort {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    @Override
    public void deletaUsuario(Long id) throws UserNotFoundException {
        Usuario usuario = usuarioRepositoryPort.findById(id).orElseThrow(UserNotFoundException::new);
        usuarioRepositoryPort.deleteById(usuario.getId());
    }

}
