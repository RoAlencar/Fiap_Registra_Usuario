package br.com.fiap.app.usuario.application.usecase;

import br.com.fiap.app.usuario.application.dto.request.AtualizaUsuarioDto;
import br.com.fiap.app.usuario.application.dto.response.AtualizaUsuarioResponse;
import br.com.fiap.app.usuario.application.port.AtualizaUsuarioUseCasePort;
import br.com.fiap.app.usuario.application.port.UsuarioRepositoryPort;
import br.com.fiap.app.usuario.domain.Usuario;
import br.com.fiap.app.usuario.infrastructure.exception.custom.ModificaUsuarioException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtualizaUsuarioUseCase implements AtualizaUsuarioUseCasePort {

    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final ModelMapper modelMapper;

    @Override
    public AtualizaUsuarioResponse atualizaUsuario(AtualizaUsuarioDto dto) throws ModificaUsuarioException {
        Usuario usuarioExistente = usuarioRepositoryPort.findByName(dto.getNome())
                .orElseThrow(ModificaUsuarioException::new);

        modelMapper.map(dto, usuarioExistente);

        Usuario atualizaUsuario = usuarioRepositoryPort.save(usuarioExistente);
        return modelMapper.map(atualizaUsuario, AtualizaUsuarioResponse.class);
    }
}
