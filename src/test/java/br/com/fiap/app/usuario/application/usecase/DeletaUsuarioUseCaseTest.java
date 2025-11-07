package br.com.fiap.app.usuario.application.usecase;

import br.com.fiap.app.usuario.application.port.UsuarioRepositoryPort;
import br.com.fiap.app.usuario.domain.Usuario;
import br.com.fiap.app.usuario.infrastructure.exception.custom.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DeletaUsuarioUseCaseTest {
    private UsuarioRepositoryPort usuarioRepositoryPort;
    private DeletaUsuarioUseCase deletaUsuarioUseCase;

    @BeforeEach
    void setUp() {
        usuarioRepositoryPort = mock(UsuarioRepositoryPort.class);
        deletaUsuarioUseCase = new DeletaUsuarioUseCase(usuarioRepositoryPort);
    }

    @Test
    void deveDeletarUsuarioComSucesso() throws UserNotFoundException {
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        when(usuarioRepositoryPort.findById(1L)).thenReturn(Optional.of(usuario));

        deletaUsuarioUseCase.deletaUsuario(1L);

        verify(usuarioRepositoryPort, times(1)).findById(1L);
        verify(usuarioRepositoryPort, times(1)).deleteById(1L);
    }

    @Test
    void deveLancarUserNotFoundExceptionQuandoUsuarioNaoExiste() {
        when(usuarioRepositoryPort.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> deletaUsuarioUseCase.deletaUsuario(1L));

        verify(usuarioRepositoryPort, times(1)).findById(1L);
        verify(usuarioRepositoryPort, never()).deleteById(anyLong());
    }
}
