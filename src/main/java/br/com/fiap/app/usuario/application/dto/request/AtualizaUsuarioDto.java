package br.com.fiap.app.usuario.application.dto.request;

import br.com.fiap.app.usuario.domain.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(hidden = true)
    private String senha;

    private LocalDateTime dataUltimaAtualizacao;

    private Endereco endereco;

    public AtualizaUsuarioDto(AtualizaUsuarioDto atualizaUsuarioDto) {
        this.nome = atualizaUsuarioDto.nome;
        this.email = atualizaUsuarioDto.email;
        this.login = atualizaUsuarioDto.login;
        this.senha = atualizaUsuarioDto.senha;
        this.dataUltimaAtualizacao = atualizaUsuarioDto.dataUltimaAtualizacao;
        this.endereco = atualizaUsuarioDto.endereco;
    }
}
