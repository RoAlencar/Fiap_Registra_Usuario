package br.com.fiap.app.usuario.application.usecase;

import br.com.fiap.app.usuario.application.dto.request.CriarUsuarioDto;
import br.com.fiap.app.usuario.application.dto.response.CriarUsuarioResponse;
import br.com.fiap.app.usuario.application.port.CriarUsuarioUseCasePort;
import br.com.fiap.app.usuario.application.port.UsuarioRepositoryPort;
import br.com.fiap.app.usuario.domain.Endereco;
import br.com.fiap.app.usuario.domain.Usuario;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarUsuarioUseCase implements CriarUsuarioUseCasePort {

    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final ModelMapper modelMapper;

    @Override
    public CriarUsuarioResponse criarUsuario(CriarUsuarioDto dto) {

        Endereco endereco = new Endereco();
        endereco.setLogradouro(dto.getEndereco().getLogradouro());
        endereco.setNumero(dto.getEndereco().getNumero());
        endereco.setComplemento(dto.getEndereco().getComplemento());
        endereco.setCidade(dto.getEndereco().getCidade());
        endereco.setCep(dto.getEndereco().getCep());

        Usuario usuario = Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .login(dto.getLogin())
                .senha(dto.getSenha())
                .dataUltimaAtualizacao(dto.getDataUltimaAtualizacao())
                .endereco(endereco)
                .build();

        return modelMapper.map(usuarioRepositoryPort.save(usuario), CriarUsuarioResponse.class);
    }
}
