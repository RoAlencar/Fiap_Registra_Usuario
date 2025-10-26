package br.com.fiap.app.usuario.adapter.out.jpa;

import br.com.fiap.app.usuario.adapter.out.jpa.usuario.entities.UsuarioEntity;
import br.com.fiap.app.usuario.adapter.out.jpa.usuario.repositories.UsuarioJpaRepository;
import br.com.fiap.app.usuario.domain.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class UsuarioJpaRepositoryAdapterTest {

    @Mock
    private UsuarioJpaRepository usuarioJpaRepository;

    @InjectMocks
    private UsuarioJpaRepositoryPortAdapter usuarioJpaRepositoryPortAdapter;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void buscaTodosUsuarios(){
        UsuarioEntity usuarioEntity1 = mock(UsuarioEntity.class);
        UsuarioEntity usuarioEntity2 = mock(UsuarioEntity.class);
        when(usuarioJpaRepository.findAll()).thenReturn(Arrays.asList(usuarioEntity1,usuarioEntity2));
        when(usuarioEntity1.toDomain()).thenReturn(new Usuario());
        when(usuarioEntity2.toDomain()).thenReturn(new Usuario());

        List<Usuario> usuarios = usuarioJpaRepositoryPortAdapter.findAll();

        assertEquals(2, usuarios.size());
    }

    @Test
    void buscaUsuarioPorId(){
        Long idUsuario = 1L;
        UsuarioEntity usuarioEntity = mock(UsuarioEntity.class);
        when(usuarioJpaRepository.findById(idUsuario)).thenReturn(Optional.of(usuarioEntity));
        when(usuarioEntity.toDomain()).thenReturn(new Usuario());

        Optional<Usuario> usuario = usuarioJpaRepositoryPortAdapter.findById(idUsuario);
        assertTrue(usuario.isPresent());
    }

    @Test
    void buscaUsuarioPorIdInvalido() {
        Long idUsuario = 999L;
        when(usuarioJpaRepository.findById(idUsuario)).thenReturn(Optional.empty());
        Optional<Usuario> usuario = usuarioJpaRepositoryPortAdapter.findById(idUsuario);

        assertTrue(usuario.isEmpty());
    }

    @Test
    void salvaUsuario() {
        Usuario usuario = mock(Usuario.class);
        UsuarioEntity usuarioEntity = mock(UsuarioEntity.class);
        when(usuario.toEntity()).thenReturn(usuarioEntity);
        when(usuarioJpaRepository.save(usuarioEntity)).thenReturn(usuarioEntity);
        when(usuarioEntity.toDomain()).thenReturn(usuario);

        Usuario usuarioSalvo = usuarioJpaRepositoryPortAdapter.save(usuario);

        assertEquals(usuario, usuarioSalvo);
    }
}
