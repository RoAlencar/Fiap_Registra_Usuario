package br.com.fiap.app.usuario.domain;

import br.com.fiap.app.usuario.adapter.out.jpa.usuario.entities.UsuarioEntity;
import br.com.fiap.app.usuario.domain.enums.TipoUsuario;
import br.com.fiap.app.usuario.utils.UsuarioTestUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UsuarioTest {

    @Test
    void validarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setTipo(TipoUsuario.PROPRIETARIO);
        usuario.setNome("Usuario de testes");
        usuario.setEmail("usuarioteste@gmail.com");
        usuario.setSenha("password");
        usuario.setDataUltimaAtualizacao(LocalDateTime.of(2025, 10, 20, 15, 30));

        assertThat(usuario.getId()).isEqualTo(1L);
        assertThat(usuario.getTipo()).isEqualTo(TipoUsuario.PROPRIETARIO);
        assertThat(usuario.getNome()).isEqualTo("Usuario de testes");
        assertThat(usuario.getEmail()).isEqualTo("usuarioteste@gmail.com");
        assertThat(usuario.getSenha()).isEqualTo("password");
        assertThat(usuario.getDataUltimaAtualizacao()).isNotNull();
    }

    @Test
    void usuarioBuilder() {
        Long id = 1L;
        String nome = "Usuario de testes";
        String email = "usuarioteste@gmail.com";
        String senha = "password";
        TipoUsuario tipo = TipoUsuario.PROPRIETARIO;
        LocalDateTime dataUltimaAtualizacao = LocalDateTime.of(2025, 10, 20, 15, 30);

        Usuario usuario = UsuarioTestUtils.gerarUsuario();

        assertThat(usuario.getId()).isEqualTo(id);
        assertThat(usuario.getTipo()).isEqualTo(tipo);
        assertThat(usuario.getNome()).isEqualTo(nome);
        assertThat(usuario.getEmail()).isEqualTo(email);
        assertThat(usuario.getSenha()).isEqualTo(senha);
        assertThat(usuario.getDataUltimaAtualizacao()).isEqualTo(dataUltimaAtualizacao);
    }

    @Test
    void setEntityBuilder() {

        Long id = 1L;
        TipoUsuario tipoUsuario = TipoUsuario.PROPRIETARIO;
        String nome = "Usuario de testes";
        String email = "usuarioteste@gmail.com";
        String login = "user";
        String senha = "password";
        LocalDateTime dataUltimaAtualizacao = LocalDateTime.of(2025, 10, 20, 15, 30);

        UsuarioEntity usuarioEntity = UsuarioTestUtils.gerarUsuarioEntity();

        assertThat(usuarioEntity.getId()).isEqualTo(id);
        assertThat(usuarioEntity.getTipo()).isEqualTo(tipoUsuario);
        assertThat(usuarioEntity.getNome()).isEqualTo(nome);
        assertThat(usuarioEntity.getEmail()).isEqualTo(email);
        assertThat(usuarioEntity.getLogin()).isEqualTo(login);
        assertThat(usuarioEntity.getSenha()).isEqualTo(senha);
        assertThat(usuarioEntity.getDataUltimaAtualizacao()).isEqualTo(dataUltimaAtualizacao);
    }

    @Test
    void deveConverterParaEntityCorretamente() {
        // Arrange
        Endereco endereco = Endereco.builder()
                .logradouro("Rua das Flores")
                .numero("123")
                .complemento("Apto 45")
                .cidade("São Paulo")
                .cep("01000-000")
                .build();

        LocalDateTime dataAtualizacao = LocalDateTime.now();

        Usuario usuario = Usuario.builder()
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
        UsuarioEntity entity = usuario.toEntity();

        // Assert
        assertNotNull(entity);
        assertEquals(usuario.getId(), entity.getId());
        assertEquals(usuario.getTipo(), entity.getTipo());
        assertEquals(usuario.getNome(), entity.getNome());
        assertEquals(usuario.getEmail(), entity.getEmail());
        assertEquals(usuario.getLogin(), entity.getLogin());
        assertEquals(usuario.getSenha(), entity.getSenha());
        assertEquals(usuario.getDataUltimaAtualizacao(), entity.getDataUltimaAtualizacao());
        assertEquals(usuario.getEndereco(), entity.getEndereco());
    }

    @Test
    void deveRetornarEntityComCamposNulosQuandoUsuarioNulo() {
        // Arrange
        Usuario usuario = new Usuario();

        // Act
        UsuarioEntity entity = usuario.toEntity();

        // Assert
        assertNotNull(entity);
        assertNull(entity.getId());
        assertNull(entity.getTipo());
        assertNull(entity.getNome());
        assertNull(entity.getEmail());
        assertNull(entity.getLogin());
        assertNull(entity.getSenha());
        assertNull(entity.getDataUltimaAtualizacao());
        assertNull(entity.getEndereco());
    }
}
