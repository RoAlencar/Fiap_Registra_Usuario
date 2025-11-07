package br.com.fiap.app.usuario.application.usecase;

import br.com.fiap.app.usuario.application.dto.request.AtualizaSenhaDto;
import br.com.fiap.app.usuario.application.dto.response.AtualizaSenhaResponse;
import br.com.fiap.app.usuario.application.port.UsuarioRepositoryPort;
import br.com.fiap.app.usuario.domain.Usuario;
import br.com.fiap.app.usuario.infrastructure.exception.custom.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizaSenhaUseCaseTest {

    @Mock
    private UsuarioRepositoryPort usuarioRepositoryPort;

    @InjectMocks
    private AtualizaSenhaUseCase useCase;

    private Usuario usuarioExistente;

    @BeforeEach
    void setup() {
        usuarioExistente = Usuario.builder()
                .id(1L)
                .senha("senhaAntiga")
                .nome("João")
                .email("joao@email.com")
                .login("joaos")
                .dataUltimaAtualizacao(LocalDateTime.now())
                .build();
    }

    @Test
    void deveAtualizarSenhaComSucesso() throws Exception {
        // Arrange
        AtualizaSenhaDto dto = new AtualizaSenhaDto();
        dto.setUsuarioId(1L);
        dto.setSenhaAtual("senhaAntiga");
        dto.setNovaSenha("senhaNova123");

        when(usuarioRepositoryPort.findById(1L)).thenReturn(Optional.of(usuarioExistente));
        when(usuarioRepositoryPort.save(any())).thenAnswer(inv -> inv.getArgument(0));

        // Act
        AtualizaSenhaResponse response = useCase.atualizaSenha(dto);

        // Assert
        assertNotNull(response);
        assertEquals("Senha atualizada com sucesso.", response.getMessage());
        assertEquals("senhaNova123", usuarioExistente.getSenha());
        verify(usuarioRepositoryPort).save(usuarioExistente);
    }

    @Test
    void deveLancarOldPasswordRequiredExceptionQuandoSenhaAtualNula() {
        AtualizaSenhaDto dto = new AtualizaSenhaDto();
        dto.setUsuarioId(1L);
        dto.setSenhaAtual(null);
        dto.setNovaSenha("novaSenha");

        when(usuarioRepositoryPort.findById(1L)).thenReturn(Optional.of(usuarioExistente));

        assertThrows(OldPasswordRequiredException.class, () -> useCase.atualizaSenha(dto));
    }

    @Test
    void deveLancarOldPasswordInvalidExceptionQuandoSenhaAtualInvalida() {
        AtualizaSenhaDto dto = new AtualizaSenhaDto();
        dto.setUsuarioId(1L);
        dto.setSenhaAtual("senhaErrada");
        dto.setNovaSenha("novaSenha");

        when(usuarioRepositoryPort.findById(1L)).thenReturn(Optional.of(usuarioExistente));

        assertThrows(OldPasswordInvalidException.class, () -> useCase.atualizaSenha(dto));
    }

    @Test
    void deveLancarNewPasswordRequiredExceptionQuandoNovaSenhaNula() {
        AtualizaSenhaDto dto = new AtualizaSenhaDto();
        dto.setUsuarioId(1L);
        dto.setSenhaAtual("senhaAntiga");
        dto.setNovaSenha(null);

        when(usuarioRepositoryPort.findById(1L)).thenReturn(Optional.of(usuarioExistente));

        assertThrows(NewPasswordRequiredException.class, () -> useCase.atualizaSenha(dto));
    }

    @Test
    void deveLancarNewPasswordEqualsOldPasswordExceptionQuandoNovaSenhaIgualAntiga() {
        AtualizaSenhaDto dto = new AtualizaSenhaDto();
        dto.setUsuarioId(1L);
        dto.setSenhaAtual("senhaAntiga");
        dto.setNovaSenha("senhaAntiga");

        when(usuarioRepositoryPort.findById(1L)).thenReturn(Optional.of(usuarioExistente));

        assertThrows(NewPasswordEqualsOldPasswordException.class, () -> useCase.atualizaSenha(dto));
    }

    @Test
    void deveLancarUserNotFoundExceptionQuandoUsuarioNaoExiste() {
        AtualizaSenhaDto dto = new AtualizaSenhaDto();
        dto.setUsuarioId(99L);
        dto.setSenhaAtual("senhaAntiga");
        dto.setNovaSenha("novaSenha");

        when(usuarioRepositoryPort.findById(99L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> useCase.atualizaSenha(dto));
    }
}
