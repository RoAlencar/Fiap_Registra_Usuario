package br.com.fiap.app.usuario.adapter.out.jpa.entities;

import br.com.fiap.app.usuario.adapter.out.jpa.usuario.entities.UsuarioEntity;
import br.com.fiap.app.usuario.domain.Endereco;
import br.com.fiap.app.usuario.domain.Usuario;
import br.com.fiap.app.usuario.domain.enums.TipoUsuario;
import br.com.fiap.app.usuario.utils.UsuarioTestUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UsuarioEntityTest {

    @Test
    void setUsuarioEntity() {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setId(1L);
        usuarioEntity.setTipo(TipoUsuario.PROPRIETARIO);
        usuarioEntity.setNome("Usuario de testes");
        usuarioEntity.setEmail("usuarioteste@gmail.com");
        usuarioEntity.setSenha("password");
        usuarioEntity.setDataUltimaAtualizacao(LocalDateTime.of(2025, 10, 20, 15, 30));

        assertThat(usuarioEntity.getId()).isEqualTo(1L);
        assertThat(usuarioEntity.getTipo()).isEqualTo(TipoUsuario.PROPRIETARIO);
        assertThat(usuarioEntity.getNome()).isEqualTo("Usuario de testes");
        assertThat(usuarioEntity.getEmail()).isEqualTo("usuarioteste@gmail.com");
        assertThat(usuarioEntity.getSenha()).isEqualTo("password");
        assertThat(usuarioEntity.getDataUltimaAtualizacao()).isNotNull();
    }

    @Test
    void usuarioEntityBuilder() {
        Long id = 1L;
        String nome = "Usuario de testes";
        String email = "usuarioteste@gmail.com";
        String senha = "password";
        TipoUsuario tipo = TipoUsuario.PROPRIETARIO;
        LocalDateTime dataUltimaAtualizacao = LocalDateTime.of(2025, 10, 20, 15, 30);

        UsuarioEntity usuario = UsuarioTestUtils.gerarUsuarioEntity();

        assertThat(usuario.getId()).isEqualTo(id);
        assertThat(usuario.getTipo()).isEqualTo(tipo);
        assertThat(usuario.getNome()).isEqualTo(nome);
        assertThat(usuario.getEmail()).isEqualTo(email);
        assertThat(usuario.getSenha()).isEqualTo(senha);
        assertThat(usuario.getDataUltimaAtualizacao()).isEqualTo(dataUltimaAtualizacao);
    }

    @Test
    void testToDomain() {
        UsuarioEntity usuarioEntity = UsuarioEntity.builder()
                .id(1L)
                .tipo(TipoUsuario.PROPRIETARIO)
                .nome("Usuario de testes")
                .email("usuarioteste@gmail.com")
                .login("user")
                .senha("password")
                .dataUltimaAtualizacao(LocalDateTime.of(2025, 10, 20, 15, 30))
                .endereco(Endereco.builder()
                        .logradouro("Av. Paulista")
                        .numero("1000")
                        .complemento("N/A")
                        .cidade("São Paulo")
                        .cep("01310-100")
                        .build()).build();

        Usuario usuario = UsuarioTestUtils.gerarUsuario();

        assertThat(usuario.getId()).isEqualTo(usuarioEntity.getId());
        assertThat(usuario.getTipo()).isEqualTo(usuarioEntity.getTipo());
        assertThat(usuario.getNome()).isEqualTo(usuarioEntity.getNome());
        assertThat(usuario.getEmail()).isEqualTo(usuarioEntity.getEmail());
        assertThat(usuario.getLogin()).isEqualTo(usuarioEntity.getLogin());
        assertThat(usuario.getSenha()).isEqualTo(usuarioEntity.getSenha());

    }

    @Test
    void deveConverterParaDomainCorretamente() {
        // Arrange
        Endereco endereco = Endereco.builder()
                .logradouro("Rua das Flores")
                .numero("123")
                .complemento("Apto 45")
                .cidade("São Paulo")
                .cep("01000-000")
                .build();

        LocalDateTime dataAtualizacao = LocalDateTime.now();

        UsuarioEntity entity = UsuarioEntity.builder()
                .id(1L)
                .tipo(TipoUsuario.USUARIO)
                .nome("João da Silva")
                .email("joao.silva@email.com")
                .login("joaos")
                .senha("123456")
                .dataUltimaAtualizacao(dataAtualizacao)
                .endereco(endereco)
                .build();

        // Act
        Usuario domain = entity.toDomain();

        // Assert
        assertNotNull(domain);
        assertEquals(entity.getId(), domain.getId());
        assertEquals(entity.getTipo(), domain.getTipo());
        assertEquals(entity.getNome(), domain.getNome());
        assertEquals(entity.getEmail(), domain.getEmail());
        assertEquals(entity.getLogin(), domain.getLogin());
        assertEquals(entity.getSenha(), domain.getSenha());
        assertEquals(entity.getDataUltimaAtualizacao(), domain.getDataUltimaAtualizacao());
        assertEquals(entity.getEndereco(), domain.getEndereco());
    }

    @Test
    void deveRetornarDomainComCamposNulosQuandoEntityNula() {
        // Arrange
        UsuarioEntity entity = new UsuarioEntity();

        // Act
        Usuario domain = entity.toDomain();

        // Assert
        assertNotNull(domain);
        assertNull(domain.getId());
        assertNull(domain.getTipo());
        assertNull(domain.getNome());
        assertNull(domain.getEmail());
        assertNull(domain.getLogin());
        assertNull(domain.getSenha());
        assertNull(domain.getDataUltimaAtualizacao());
        assertNull(domain.getEndereco());
    }
}
