package br.com.fiap.app.usuario.application.dto.response;

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
public class UsuarioBaseResponse {

    private Long id;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private LocalDateTime dataUltimaAtualizacao;
    private Endereco endereco;

    public UsuarioBaseResponse(UsuarioBaseResponse response) {
        this.id = response.getId();
        this.nome = response.getNome();
        this.email = response.getEmail();
        this.login = response.getLogin();
        this.senha = response.getSenha();
        this.dataUltimaAtualizacao = response.getDataUltimaAtualizacao();
        this.endereco = response.getEndereco();
    }
}
