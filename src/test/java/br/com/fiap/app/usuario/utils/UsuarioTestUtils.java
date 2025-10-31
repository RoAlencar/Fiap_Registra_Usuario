package br.com.fiap.app.usuario.utils;

import br.com.fiap.app.usuario.application.dto.request.EnderecoDto;
import br.com.fiap.app.usuario.adapter.out.jpa.usuario.entities.UsuarioEntity;
import br.com.fiap.app.usuario.application.dto.request.AtualizaUsuarioDto;
import br.com.fiap.app.usuario.application.dto.request.CriarUsuarioDto;
import br.com.fiap.app.usuario.application.dto.response.AtualizaUsuarioResponse;
import br.com.fiap.app.usuario.application.dto.response.BuscaUsuarioResponse;
import br.com.fiap.app.usuario.application.dto.response.CriarUsuarioResponse;
import br.com.fiap.app.usuario.application.dto.response.UsuarioBaseResponse;
import br.com.fiap.app.usuario.domain.Endereco;
import br.com.fiap.app.usuario.domain.Usuario;
import br.com.fiap.app.usuario.domain.enums.TipoUsuario;


import java.time.LocalDateTime;

public class UsuarioTestUtils {

    private UsuarioTestUtils() {
    }

    public static Usuario gerarUsuario() {
        return Usuario.builder()
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
                        .build())
                .build();
    }

    public static Usuario gerarUsuarioAtualizado(){
        return Usuario.builder()
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
                        .build())
                .build();
    }

    public static CriarUsuarioDto gerarCriarUsuarioDto() {
        return CriarUsuarioDto.builder()
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
                        .build())
                .build();
    }

    public static UsuarioEntity gerarUsuarioEntity() {
        return UsuarioEntity.builder()
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
                        .build())
                .build();
    }

    public static CriarUsuarioResponse gerarCriarUsuarioResponse() {
        return new CriarUsuarioResponse(UsuarioBaseResponse.builder()
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
                        .build())
                .build());
    }

    public static AtualizaUsuarioDto gerarAtualizaUsuarioDto() {
        return  AtualizaUsuarioDto.builder()
                .id(1L)
                .nome("Usuario de testes")
                .email("usuarioteste@gmail.com")
                .login("user")
                .senha("password")
                .dataUltimaAtualizacao(LocalDateTime.of(2025, 10, 20, 15, 30))
                .endereco(EnderecoDto.builder()
                          .logradouro("Av. Paulista")
                          .numero("1000")
                          .complemento("N/A")
                          .cidade("São Paulo")
                          .cep("01310-100")
                          .build())
                .build();
    }

    public static AtualizaUsuarioResponse gerarAtualizaUsuarioResponse() {
        return new AtualizaUsuarioResponse(UsuarioBaseResponse.builder()
                .id(1L)
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
                        .build())
                .build());
    }

    public static BuscaUsuarioResponse gerarBuscaUsuarioResponse() {
        return new BuscaUsuarioResponse(UsuarioBaseResponse.builder()
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
                        .build())
                .build());
    }
}
