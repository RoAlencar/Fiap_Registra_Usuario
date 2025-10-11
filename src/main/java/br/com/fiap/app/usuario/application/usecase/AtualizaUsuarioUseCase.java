package br.com.fiap.app.usuario.application.usecase;

import br.com.fiap.app.usuario.application.dto.request.AtualizaUsuarioDto;
import br.com.fiap.app.usuario.application.dto.response.AtualizaUsuarioResponse;
import br.com.fiap.app.usuario.application.port.AtualizaUsuarioUseCasePort;
import br.com.fiap.app.usuario.application.port.UsuarioRepositoryPort;
import br.com.fiap.app.usuario.domain.Usuario;
import br.com.fiap.app.usuario.infrastructure.exception.custom.ModificaUsuarioException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.UserNotFoundException;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtualizaUsuarioUseCase implements AtualizaUsuarioUseCasePort {

    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final ModelMapper modelMapper;

    @Override
    public AtualizaUsuarioResponse atualizaUsuario(AtualizaUsuarioDto dto)
            throws ModificaUsuarioException, UserNotFoundException {

        Usuario usuarioExistente = usuarioRepositoryPort.findByNome(dto.getNome())
                .orElseThrow(UserNotFoundException::new);

        try {
            usuarioExistente.setNome(dto.getNome());
            usuarioExistente.setEmail(dto.getEmail());
            usuarioExistente.setEndereco(dto.getEndereco());
            usuarioExistente.setDataUltimaAtualizacao(LocalDateTime.now());

            Usuario usuarioAtualizado = usuarioRepositoryPort.save(usuarioExistente);
            return modelMapper.map(usuarioAtualizado, AtualizaUsuarioResponse.class);
        } catch (Exception e) {
            throw new ModificaUsuarioException();
        }
    }
}
