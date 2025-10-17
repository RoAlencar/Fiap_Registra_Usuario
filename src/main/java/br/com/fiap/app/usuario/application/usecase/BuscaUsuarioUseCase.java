package br.com.fiap.app.usuario.application.usecase;

import br.com.fiap.app.usuario.application.dto.response.BuscaUsuarioResponse;
import br.com.fiap.app.usuario.application.port.BuscaUsuarioUseCasePort;
import br.com.fiap.app.usuario.application.port.UsuarioRepositoryPort;
import br.com.fiap.app.usuario.domain.Usuario;
import br.com.fiap.app.usuario.infrastructure.exception.custom.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuscaUsuarioUseCase implements BuscaUsuarioUseCasePort {

    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final ModelMapper modelMapper;

    @Override
    public List<BuscaUsuarioResponse> buscaTodosUsuarios() {
        return usuarioRepositoryPort.findAll().stream()
                .map(entity -> modelMapper.map(entity, BuscaUsuarioResponse.class)).toList();
    }

    @Override
    public List<BuscaUsuarioResponse> buscaUsuarioPorNome(String nome) throws UserNotFoundException {
        List<Usuario> usuarios = usuarioRepositoryPort.findByNomeLikeIgnoreCaseAndAccent(nome);

        if (usuarios.isEmpty()) {
            throw new UserNotFoundException();
        }

        return usuarios.stream()
                .map(usuario -> modelMapper.map(usuario, BuscaUsuarioResponse.class))
                .toList();
    }

}
