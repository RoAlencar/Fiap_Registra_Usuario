/*package br.com.fiap.app.usuario.application.dto.request;

import br.com.fiap.app.usuario.domain.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import jakarta.validation.Valid;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AtualizaUsuarioDto {

    @NotNull
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String login;

    @Schema(hidden = true)
    private String senha;

    private LocalDateTime dataUltimaAtualizacao;


    //@Valid
    //@Notnull
    //private Endereco endereco;


    @Valid
    @NotNull
    private EnderecoDto endereco; // ← agora usa o DTO

    public AtualizaUsuarioDto(AtualizaUsuarioDto atualizaUsuarioDto) {
        this.nome = atualizaUsuarioDto.nome;
        this.email = atualizaUsuarioDto.email;
        this.login = atualizaUsuarioDto.login;
        this.senha = atualizaUsuarioDto.senha;
        this.dataUltimaAtualizacao = atualizaUsuarioDto.dataUltimaAtualizacao;
        this.endereco = atualizaUsuarioDto.endereco;
    }
}


 */

package br.com.fiap.app.usuario.application.dto.request;

// import br.com.fiap.app.usuario.domain.Endereco; // ❌ Remova esta linha
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AtualizaUsuarioDto {

    @NotNull
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String login;

    @Schema(hidden = true)
    private String senha;

    private LocalDateTime dataUltimaAtualizacao;

    @Valid
    @NotNull
    private EnderecoDto endereco; // ✅ Correto

    public AtualizaUsuarioDto(AtualizaUsuarioDto atualizaUsuarioDto) {
        this.nome = atualizaUsuarioDto.nome;
        this.email = atualizaUsuarioDto.email;
        this.login = atualizaUsuarioDto.login;
        this.senha = atualizaUsuarioDto.senha;
        this.dataUltimaAtualizacao = atualizaUsuarioDto.dataUltimaAtualizacao;
        this.endereco = atualizaUsuarioDto.endereco; // ✅ Correto, copia o DTO
    }
}