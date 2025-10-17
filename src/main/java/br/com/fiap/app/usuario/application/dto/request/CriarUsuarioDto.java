package br.com.fiap.app.usuario.application.dto.request;

import br.com.fiap.app.usuario.domain.Endereco;
import br.com.fiap.app.usuario.domain.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CriarUsuarioDto {

    private TipoUsuario tipo;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private LocalDateTime dataUltimaAtualizacao;
    private Endereco endereco;

    public CriarUsuarioDto(CriarUsuarioDto criarUsuarioDto) {
        this.tipo = criarUsuarioDto.getTipo();
        this.nome = criarUsuarioDto.getNome();
        this.email = criarUsuarioDto.getEmail();
        this.login = criarUsuarioDto.getLogin();
        this.senha = criarUsuarioDto.getSenha();
        this.dataUltimaAtualizacao = criarUsuarioDto.getDataUltimaAtualizacao();
        this.endereco = criarUsuarioDto.getEndereco();
    }

}
