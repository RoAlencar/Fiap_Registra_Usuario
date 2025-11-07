/*package br.com.fiap.app.usuario.application.dto.request;

import br.com.fiap.app.usuario.domain.Endereco;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AtualizaUsuarioDtoTest {

    @Test
    void deveCriarDtoComConstrutorPadraoESetters() {
        // Arrange
        AtualizaUsuarioDto dto = new AtualizaUsuarioDto();
        Endereco endereco = Endereco.builder()
                .logradouro("Rua A")
                .numero("123")
                .cidade("São Paulo")
                .cep("01000-000")
                .build();

        // Act
        dto.setId(1L);
        dto.setNome("João");
        dto.setEmail("joao@email.com");
        dto.setLogin("joaos");
        dto.setSenha("123456");
        dto.setDataUltimaAtualizacao(LocalDateTime.now());
        dto.setEndereco(endereco);

        // Assert
        assertEquals(1L, dto.getId());
        assertEquals("João", dto.getNome());
        assertEquals("joao@email.com", dto.getEmail());
        assertEquals("joaos", dto.getLogin());
        assertEquals("123456", dto.getSenha());
        assertNotNull(dto.getDataUltimaAtualizacao());
        assertEquals(endereco, dto.getEndereco());
    }

    @Test
    void deveCriarDtoComConstrutorDeCopia() {
        // Arrange
        Endereco endereco = Endereco.builder()
                .logradouro("Rua B")
                .numero("456")
                .cidade("Rio de Janeiro")
                .cep("20000-000")
                .build();

        AtualizaUsuarioDto original = AtualizaUsuarioDto.builder()
                .id(null)
                .nome("Maria")
                .email("maria@email.com")
                .login("marias")
                .senha("senha123")
                .dataUltimaAtualizacao(LocalDateTime.now())
                .endereco(endereco)
                .build();

        // Act
        AtualizaUsuarioDto copia = new AtualizaUsuarioDto(original);

        // Assert
        assertEquals(original.getId(), copia.getId());
        assertEquals(original.getNome(), copia.getNome());
        assertEquals(original.getEmail(), copia.getEmail());
        assertEquals(original.getLogin(), copia.getLogin());
        assertEquals(original.getSenha(), copia.getSenha());
        assertEquals(original.getDataUltimaAtualizacao(), copia.getDataUltimaAtualizacao());
        assertEquals(original.getEndereco(), copia.getEndereco());
    }
}


 */

package br.com.fiap.app.usuario.application.dto.request;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AtualizaUsuarioDtoTest {

    @Test
    void deveCriarDtoComConstrutorPadraoESetters() {
        // Arrange
        AtualizaUsuarioDto dto = new AtualizaUsuarioDto();

        // ✅ Crie um EnderecoDto, não um Endereco!
        EnderecoDto enderecoDto = EnderecoDto.builder()
                .logradouro("Rua A")
                .numero("123")
                .cidade("São Paulo")
                .cep("01000-000")
                .build();

        // Act
        dto.setId(1L);
        dto.setNome("João");
        dto.setEmail("joao@email.com");
        dto.setLogin("joaos");
        dto.setSenha("123456");
        dto.setDataUltimaAtualizacao(LocalDateTime.now());
        dto.setEndereco(enderecoDto); // ✅ Agora está correto!

        // Assert
        assertEquals(1L, dto.getId());
        assertEquals("João", dto.getNome());
        assertEquals("joao@email.com", dto.getEmail());
        assertEquals("joaos", dto.getLogin());
        assertEquals("123456", dto.getSenha());
        assertNotNull(dto.getDataUltimaAtualizacao());
        assertEquals(enderecoDto, dto.getEndereco()); // ✅ Comparação com o DTO
    }

    @Test
    void deveCriarDtoComConstrutorDeCopia() {
        // Arrange
        EnderecoDto enderecoDto = EnderecoDto.builder() // ✅ Use EnderecoDto aqui também!
                .logradouro("Rua B")
                .numero("456")
                .cidade("Rio de Janeiro")
                .cep("20000-000")
                .build();

        AtualizaUsuarioDto original = AtualizaUsuarioDto.builder()
                .id(null)
                .nome("Maria")
                .email("maria@email.com")
                .login("marias")
                .senha("senha123")
                .dataUltimaAtualizacao(LocalDateTime.now())
                .endereco(enderecoDto) // ✅ Aqui também!
                .build();

        // Act
        AtualizaUsuarioDto copia = new AtualizaUsuarioDto(original);

        // Assert
        assertEquals(original.getId(), copia.getId());
        assertEquals(original.getNome(), copia.getNome());
        assertEquals(original.getEmail(), copia.getEmail());
        assertEquals(original.getLogin(), copia.getLogin());
        assertEquals(original.getSenha(), copia.getSenha());
        assertEquals(original.getDataUltimaAtualizacao(), copia.getDataUltimaAtualizacao());
        assertEquals(original.getEndereco(), copia.getEndereco());
    }
}