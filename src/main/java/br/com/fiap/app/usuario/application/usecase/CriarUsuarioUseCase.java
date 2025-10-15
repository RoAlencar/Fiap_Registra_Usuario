package br.com.fiap.app.usuario.application.usecase;

import br.com.fiap.app.usuario.application.dto.request.CriarUsuarioDto;
import br.com.fiap.app.usuario.application.dto.response.CriarUsuarioResponse;
import br.com.fiap.app.usuario.application.port.CriarUsuarioUseCasePort;
import br.com.fiap.app.usuario.application.port.UsuarioRepositoryPort;
import br.com.fiap.app.usuario.domain.Endereco;
import br.com.fiap.app.usuario.domain.Usuario;
import br.com.fiap.app.usuario.infrastructure.exception.custom.AddressRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.DuplicateEmailException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.EmailRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.NameRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.PasswordRequiredException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.UserRequiredException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarUsuarioUseCase implements CriarUsuarioUseCasePort {

    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final ModelMapper modelMapper;

    @Override
    public CriarUsuarioResponse criarUsuario(CriarUsuarioDto dto) throws AddressRequiredException, DuplicateEmailException, EmailRequiredException,
            PasswordRequiredException, UserRequiredException, NameRequiredException {

        validarUsuario(dto);

        Endereco endereco = new Endereco();
        endereco.setLogradouro(dto.getEndereco().getLogradouro());
        endereco.setNumero(dto.getEndereco().getNumero());
        endereco.setComplemento(dto.getEndereco().getComplemento());
        endereco.setCidade(dto.getEndereco().getCidade());
        endereco.setCep(dto.getEndereco().getCep());

        Usuario usuario = Usuario.builder()
                .tipoUsuario(dto.getTipoUsuario())
                .nome(dto.getNome())
                .email(dto.getEmail())
                .login(dto.getLogin())
                .senha(dto.getSenha())
                .dataUltimaAtualizacao(dto.getDataUltimaAtualizacao())
                .endereco(endereco)
                .build();

        return modelMapper.map(usuarioRepositoryPort.save(usuario), CriarUsuarioResponse.class);
    }

    private void validarUsuario(CriarUsuarioDto dto)
            throws AddressRequiredException, DuplicateEmailException, EmailRequiredException,
            NameRequiredException, PasswordRequiredException, UserRequiredException {

        if(dto == null){
            throw new UserRequiredException();
        }

        if(dto.getNome() == null || dto.getNome().isEmpty()){
            throw new NameRequiredException();
        }

        if(dto.getEmail() == null || dto.getEmail().isEmpty()){
            throw new EmailRequiredException();
        }

        if(usuarioRepositoryPort.findByEmail(dto.getEmail()).isPresent()){
            throw new DuplicateEmailException();
        }

        if(dto.getSenha() == null || dto.getSenha().isEmpty()){
            throw new PasswordRequiredException();
        }

        if (dto.getEndereco() == null){
            throw new AddressRequiredException();
        }
    }

}
