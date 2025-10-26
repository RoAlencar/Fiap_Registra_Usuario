package br.com.fiap.app.usuario.application.usecase;

import br.com.fiap.app.usuario.application.dto.request.AtualizaUsuarioDto;
import br.com.fiap.app.usuario.application.dto.response.AtualizaUsuarioResponse;
import br.com.fiap.app.usuario.application.port.UsuarioRepositoryPort;
import br.com.fiap.app.usuario.domain.Endereco;
import br.com.fiap.app.usuario.domain.Usuario;
import br.com.fiap.app.usuario.infrastructure.exception.custom.ModificaUsuarioException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.PasswordUpdateNotAllowedException;
import br.com.fiap.app.usuario.infrastructure.exception.custom.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AtualizaUsuarioUseCaseTest {

    private UsuarioRepositoryPort usuarioRepositoryPort;
    private ModelMapper modelMapper;
    private AtualizaUsuarioUseCase atualizaUsuarioUseCase;

    @BeforeEach
    void setup() {
        usuarioRepositoryPort = mock(UsuarioRepositoryPort.class);
        modelMapper = new ModelMapper();
        atualizaUsuarioUseCase = new AtualizaUsuarioUseCase(usuarioRepositoryPort, modelMapper);
    }

    @Test
    void deveAtualizarUsuarioComSucesso() throws Exception {
        // Arrange
        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setId(1L);
        usuarioExistente.setNome("Antigo Nome");
        usuarioExistente.setEmail("antigo@email.com");
        usuarioExistente.setSenha("123");
        usuarioExistente.setEndereco(Endereco.builder()
                .logradouro("Av. Paulista")
                .numero("1000")
                .complemento("N/A")
                .cidade("São Paulo")
                .cep("01310-100")
                .build());

        AtualizaUsuarioDto dto = new AtualizaUsuarioDto();
        dto.setId(1L);
        dto.setNome("Novo Nome");
        dto.setEmail("novo@email.com");
        dto.setEndereco(Endereco.builder()
                .logradouro("Av. Paulista")
                .numero("1001")
                .complemento("N/A")
                .cidade("São Paulo")
                .cep("01310-100")
                .build());
        dto.setSenha("123"); // mesma senha -> permitido

        when(usuarioRepositoryPort.findById(1L)).thenReturn(Optional.of(usuarioExistente));
        when(usuarioRepositoryPort.save(any(Usuario.class))).thenAnswer(inv -> inv.getArgument(0));

        // Act
        AtualizaUsuarioResponse response = atualizaUsuarioUseCase.atualizaUsuario(dto);

        // Assert
        assertNotNull(response);
        assertEquals(dto.getNome(), response.getNome());
        assertEquals(dto.getEmail(), response.getEmail());
        verify(usuarioRepositoryPort, times(1)).save(any(Usuario.class));
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        // Arrange
        AtualizaUsuarioDto dto = new AtualizaUsuarioDto();
        dto.setId(999L);

        when(usuarioRepositoryPort.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> atualizaUsuarioUseCase.atualizaUsuario(dto));
        verify(usuarioRepositoryPort, never()).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoTentarAlterarSenha() {
        // Arrange
        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setId(1L);
        usuarioExistente.setSenha("senhaAntiga");

        AtualizaUsuarioDto dto = new AtualizaUsuarioDto();
        dto.setId(1L);
        dto.setSenha("novaSenha"); // senha diferente -> proibido

        when(usuarioRepositoryPort.findById(1L)).thenReturn(Optional.of(usuarioExistente));

        // Act & Assert
        assertThrows(PasswordUpdateNotAllowedException.class, () -> atualizaUsuarioUseCase.atualizaUsuario(dto));
        verify(usuarioRepositoryPort, never()).save(any());
    }

    @Test
    void deveLancarModificaUsuarioExceptionQuandoSaveFalhar() {
        // Arrange
        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setId(1L);
        usuarioExistente.setSenha("123");

        AtualizaUsuarioDto dto = new AtualizaUsuarioDto();
        dto.setId(1L);
        dto.setSenha("123");
        dto.setNome("Teste");

        when(usuarioRepositoryPort.findById(1L)).thenReturn(Optional.of(usuarioExistente));
        when(usuarioRepositoryPort.save(any(Usuario.class))).thenThrow(new RuntimeException("Erro ao salvar"));

        // Act & Assert
        assertThrows(ModificaUsuarioException.class, () -> atualizaUsuarioUseCase.atualizaUsuario(dto));
    }

    @Test
    void devePermitirSenhaNulaSemExcecao() throws Exception {
        // Arrange
        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setId(1L);
        usuarioExistente.setSenha(null);

        AtualizaUsuarioDto dto = new AtualizaUsuarioDto();
        dto.setId(1L);
        dto.setSenha(null); // sem senha no DTO também

        when(usuarioRepositoryPort.findById(1L)).thenReturn(Optional.of(usuarioExistente));
        when(usuarioRepositoryPort.save(any(Usuario.class))).thenAnswer(inv -> inv.getArgument(0));

        // Act
        AtualizaUsuarioResponse response = atualizaUsuarioUseCase.atualizaUsuario(dto);

        // Assert
        assertNotNull(response);
        verify(usuarioRepositoryPort, times(1)).save(any(Usuario.class));
    }
}