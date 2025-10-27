package br.com.fiap.app.usuario.application.dto.response;

import br.com.fiap.app.usuario.domain.Endereco;
import br.com.fiap.app.usuario.domain.enums.TipoUsuario;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UsuarioBaseResponseTest {

    @Test
    void deveCriarResponseComConstrutorPadraoESetters() {
        // Arrange
        UsuarioBaseResponse response = new UsuarioBaseResponse();
        Endereco endereco = Endereco.builder()
                .logradouro("Rua A")
                .numero("123")
                .cidade("São Paulo")
                .cep("01000-000")
                .build();

        // Act
        response.setId(1L);
        response.setTipo(TipoUsuario.USUARIO);
        response.setNome("João");
        response.setEmail("joao@email.com");
        response.setLogin("joaos");
        response.setSenha("123456");
        response.setDataUltimaAtualizacao(LocalDateTime.now());
        response.setEndereco(endereco);

        // Assert
        assertEquals(1L, response.getId());
        assertEquals(TipoUsuario.USUARIO, response.getTipo());
        assertEquals("João", response.getNome());
        assertEquals("joao@email.com", response.getEmail());
        assertEquals("joaos", response.getLogin());
        assertEquals("123456", response.getSenha());
        assertNotNull(response.getDataUltimaAtualizacao());
        assertEquals(endereco, response.getEndereco());
    }

    @Test
    void deveCriarResponseComConstrutorDeCopia() {
        // Arrange
        Endereco endereco = Endereco.builder()
                .logradouro("Rua B")
                .numero("456")
                .cidade("Rio de Janeiro")
                .cep("20000-000")
                .build();

        UsuarioBaseResponse original = UsuarioBaseResponse.builder()
                .id(2L)
                .tipo(TipoUsuario.PROPRIETARIO)
                .nome("Maria")
                .email("maria@email.com")
                .login("marias")
                .senha("senha123")
                .dataUltimaAtualizacao(LocalDateTime.now())
                .endereco(endereco)
                .build();

        // Act
        UsuarioBaseResponse copia = new UsuarioBaseResponse(original);

        // Assert
        assertEquals(original.getId(), copia.getId());
        assertEquals(original.getTipo(), copia.getTipo());
        assertEquals(original.getNome(), copia.getNome());
        assertEquals(original.getEmail(), copia.getEmail());
        assertEquals(original.getLogin(), copia.getLogin());
        assertEquals(original.getSenha(), copia.getSenha());
        assertEquals(original.getDataUltimaAtualizacao(), copia.getDataUltimaAtualizacao());
        assertEquals(original.getEndereco(), copia.getEndereco());
    }
}
