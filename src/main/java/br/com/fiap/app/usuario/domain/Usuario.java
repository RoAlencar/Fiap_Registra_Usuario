package br.com.fiap.app.usuario.domain;

import br.com.fiap.app.usuario.adapter.out.jpa.usuario.entities.UsuarioEntity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
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
public class Usuario {

    private Long id;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private LocalDateTime dataUltimaAtualizacao;
    private Endereco endereco;

    public UsuarioEntity toEntity() {
        return UsuarioEntity.builder()
                .id(this.id)
                .nome(this.nome)
                .email(this.email)
                .login(this.login)
                .senha(this.senha)
                .dataUltimaAtualizacao(this.dataUltimaAtualizacao)
                .endereco(this.endereco)
                .build();
    }
}
