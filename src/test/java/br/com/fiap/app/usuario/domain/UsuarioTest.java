package br.com.fiap.app.usuario.domain;

import br.com.fiap.app.usuario.adapter.out.jpa.usuario.entities.UsuarioEntity;
import br.com.fiap.app.usuario.domain.enums.TipoUsuario;
import br.com.fiap.app.usuario.utils.UsuarioTestUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

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
}
