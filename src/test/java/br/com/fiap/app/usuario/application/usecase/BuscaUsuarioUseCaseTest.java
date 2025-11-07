package br.com.fiap.app.usuario.application.usecase;

import br.com.fiap.app.usuario.application.dto.response.BuscaUsuarioResponse;
import br.com.fiap.app.usuario.application.port.UsuarioRepositoryPort;
import br.com.fiap.app.usuario.domain.Usuario;
import br.com.fiap.app.usuario.infrastructure.exception.custom.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BuscaUsuarioUseCaseTest {

    private UsuarioRepositoryPort usuarioRepositoryPort;
    private ModelMapper modelMapper;
    private BuscaUsuarioUseCase buscaUsuarioUseCase;

    @BeforeEach
    void setUp() {
        usuarioRepositoryPort = mock(UsuarioRepositoryPort.class);
        modelMapper = new ModelMapper();
        buscaUsuarioUseCase = new BuscaUsuarioUseCase(usuarioRepositoryPort, modelMapper);
    }

    @Test
    void deveBuscarTodosUsuarios() {
        // Mock de usuários
        Usuario usuario1 = new Usuario();
        usuario1.setId(1L);
        usuario1.setNome("João");

        Usuario usuario2 = new Usuario();
        usuario2.setId(2L);
        usuario2.setNome("Maria");

        when(usuarioRepositoryPort.findAll()).thenReturn(List.of(usuario1, usuario2));

        List<BuscaUsuarioResponse> resultado = buscaUsuarioUseCase.buscaTodosUsuarios();

        assertEquals(2, resultado.size());
        assertEquals("João", resultado.get(0).getNome());
        assertEquals("Maria", resultado.get(1).getNome());

        verify(usuarioRepositoryPort, times(1)).findAll();
    }

    @Test
    void deveBuscarUsuarioPorNome() throws UserNotFoundException {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("João");

        when(usuarioRepositoryPort.findByNomeLikeIgnoreCaseAndAccent("João"))
                .thenReturn(List.of(usuario));

        List<BuscaUsuarioResponse> resultado = buscaUsuarioUseCase.buscaUsuarioPorNome("João");

        assertEquals(1, resultado.size());
        assertEquals("João", resultado.get(0).getNome());

        verify(usuarioRepositoryPort, times(1)).findByNomeLikeIgnoreCaseAndAccent("João");
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        when(usuarioRepositoryPort.findByNomeLikeIgnoreCaseAndAccent("Carlos"))
                .thenReturn(List.of());

        assertThrows(UserNotFoundException.class, () -> buscaUsuarioUseCase.buscaUsuarioPorNome("Carlos"));

        verify(usuarioRepositoryPort, times(1)).findByNomeLikeIgnoreCaseAndAccent("Carlos");
    }
}
