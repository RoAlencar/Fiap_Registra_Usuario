package br.com.fiap.app.usuario.application.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtualizaSenhaDtoTest {

    @Test
    void deveCriarDtoComConstrutorPadraoESetters() {
        // Arrange
        AtualizaSenhaDto dto = new AtualizaSenhaDto();

        // Act
        dto.setUsuarioId(1L);
        dto.setSenhaAtual("senhaAntiga");
        dto.setNovaSenha("senhaNova");

        // Assert
        assertEquals(1L, dto.getUsuarioId());
        assertEquals("senhaAntiga", dto.getSenhaAtual());
        assertEquals("senhaNova", dto.getNovaSenha());
    }

    @Test
    void deveCriarDtoComConstrutorDeCopia() {
        // Arrange
        AtualizaSenhaDto original = AtualizaSenhaDto.builder()
                .usuarioId(2L)
                .senhaAtual("senha123")
                .novaSenha("senha456")
                .build();

        // Act
        AtualizaSenhaDto copia = new AtualizaSenhaDto(original);

        // Assert
        assertEquals(original.getUsuarioId(), copia.getUsuarioId());
        assertEquals(original.getSenhaAtual(), copia.getSenhaAtual());
        assertEquals(original.getNovaSenha(), copia.getNovaSenha());
    }
}
