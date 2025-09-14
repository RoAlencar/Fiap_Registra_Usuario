package br.com.fiap.app.usuario.application.dto.request;

import br.com.fiap.app.usuario.domain.Endereco;
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
public class AtualizaUsuarioDto {

    private Long id;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private LocalDateTime dataUltimaAtualizacao;
    private Endereco endereco;

    public AtualizaUsuarioDto(AtualizaUsuarioDto atualizaUsuarioDto) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
        this.endereco = endereco;
    }
}
