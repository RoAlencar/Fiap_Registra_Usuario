/*package br.com.fiap.app.usuario.application.usecase;

import br.com.fiap.app.usuario.application.dto.request.AtualizaUsuarioDto;
import br.com.fiap.app.usuario.application.dto.response.AtualizaUsuarioResponse;
import br.com.fiap.app.usuario.application.port.AtualizaUsuarioUseCasePort;
import br.com.fiap.app.usuario.application.port.UsuarioRepositoryPort;
import br.com.fiap.app.usuario.domain.Usuario;
import br.com.fiap.app.usuario.infrastructure.exception.custom.ModificaUsuarioException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.NoChangesDetectedException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.PasswordUpdateNotAllowedException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import br.com.fiap.app.usuario.application.dto.request.EnderecoDto;
import br.com.fiap.app.usuario.domain.Endereco;

import java.time.LocalDateTime;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtualizaUsuarioUseCase implements AtualizaUsuarioUseCasePort {

    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final ModelMapper modelMapper;

    @Override
    public AtualizaUsuarioResponse atualizaUsuario(AtualizaUsuarioDto dto)
            throws ModificaUsuarioException, UserNotFoundException, PasswordUpdateNotAllowedException,
            NoChangesDetectedException {

        Usuario usuarioExistente = usuarioRepositoryPort.findById(dto.getId())
                .orElseThrow(UserNotFoundException::new);


        if (dto.getSenha() != null && !usuarioExistente.getSenha().equals(dto.getSenha())) {
            throw new PasswordUpdateNotAllowedException();
        }

        if (isUnchanged(dto, usuarioExistente)) {
            throw new NoChangesDetectedException();
        }

        try {
            usuarioExistente.setNome(dto.getNome());
            usuarioExistente.setEmail(dto.getEmail());
            usuarioExistente.setLogin(dto.getLogin());
            usuarioExistente.setEndereco(dto.getEndereco());
            usuarioExistente.setDataUltimaAtualizacao(LocalDateTime.now());

            Usuario usuarioAtualizado = usuarioRepositoryPort.save(usuarioExistente);
            return modelMapper.map(usuarioAtualizado, AtualizaUsuarioResponse.class);
        } catch (Exception e) {
            throw new ModificaUsuarioException();
        }
    }

    private boolean isUnchanged(AtualizaUsuarioDto dto, Usuario usuario) {
        return Objects.equals(dto.getNome(), usuario.getNome()) &&
                Objects.equals(dto.getEmail(), usuario.getEmail()) &&
                Objects.equals(dto.getLogin(), usuario.getLogin()) &&
                Objects.equals(dto.getEndereco(), usuario.getEndereco());
    }
}


 */

package br.com.fiap.app.usuario.application.usecase;

import br.com.fiap.app.usuario.application.dto.request.AtualizaUsuarioDto;
import br.com.fiap.app.usuario.application.dto.request.EnderecoDto; // Importe o DTO
import br.com.fiap.app.usuario.application.dto.response.AtualizaUsuarioResponse;
import br.com.fiap.app.usuario.application.port.AtualizaUsuarioUseCasePort;
import br.com.fiap.app.usuario.application.port.UsuarioRepositoryPort;
import br.com.fiap.app.usuario.domain.Endereco; // Importe a entidade
import br.com.fiap.app.usuario.domain.Usuario;
import br.com.fiap.app.usuario.infrastructure.exception.custom.ModificaUsuarioException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.NoChangesDetectedException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.PasswordUpdateNotAllowedException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.UserNotFoundException;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtualizaUsuarioUseCase implements AtualizaUsuarioUseCasePort {

    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final ModelMapper modelMapper;

    @Override
    public AtualizaUsuarioResponse atualizaUsuario(AtualizaUsuarioDto dto)
            throws ModificaUsuarioException, UserNotFoundException, PasswordUpdateNotAllowedException,
            NoChangesDetectedException {

        Usuario usuarioExistente = usuarioRepositoryPort.findById(dto.getId())
                .orElseThrow(UserNotFoundException::new);

        // Verificação de senha removida se não for permitido atualizar via DTO
        // if (dto.getSenha() != null && !usuarioExistente.getSenha().equals(dto.getSenha())) {
        //     throw new PasswordUpdateNotAllowedException();
        // }

        // Conversão de EnderecoDto para Endereco do domínio
        Endereco enderecoAtualizado = convertEnderecoDtoToEndereco(dto.getEndereco());

        if (isUnchanged(dto, usuarioExistente, enderecoAtualizado)) {
            throw new NoChangesDetectedException();
        }

        try {
            usuarioExistente.setNome(dto.getNome());
            usuarioExistente.setEmail(dto.getEmail());
            usuarioExistente.setLogin(dto.getLogin());
            // Atribui o novo objeto Endereco convertido
            usuarioExistente.setEndereco(enderecoAtualizado);
            usuarioExistente.setDataUltimaAtualizacao(LocalDateTime.now());

            Usuario usuarioAtualizado = usuarioRepositoryPort.save(usuarioExistente);
            return modelMapper.map(usuarioAtualizado, AtualizaUsuarioResponse.class);
        } catch (Exception e) {
            throw new ModificaUsuarioException();
        }
    }

    // Método auxiliar para converter DTO para Entidade
    private Endereco convertEnderecoDtoToEndereco(EnderecoDto enderecoDto) {
        if (enderecoDto == null) {
            return null;
        }
        return Endereco.builder()
                .logradouro(enderecoDto.getLogradouro())
                .numero(enderecoDto.getNumero())
                .complemento(enderecoDto.getComplemento())
                .cidade(enderecoDto.getCidade())
                .cep(enderecoDto.getCep())
                .build();
    }

    // Atualize o método isUnchanged para comparar com o novo Endereco
    private boolean isUnchanged(AtualizaUsuarioDto dto, Usuario usuario, Endereco enderecoAtualizado) {
        return Objects.equals(dto.getNome(), usuario.getNome()) &&
                Objects.equals(dto.getEmail(), usuario.getEmail()) &&
                Objects.equals(dto.getLogin(), usuario.getLogin()) &&
                Objects.equals(enderecoAtualizado, usuario.getEndereco());
    }
}