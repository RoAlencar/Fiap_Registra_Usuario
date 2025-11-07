package br.com.fiap.app.usuario.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AtualizaSenhaDto {
    @NotNull
    private Long usuarioId;

    @NotBlank
    private String senhaAtual;

    @NotBlank
    private String novaSenha;

    public AtualizaSenhaDto(AtualizaSenhaDto atualizaSenhaDto) {
        this.usuarioId = atualizaSenhaDto.usuarioId;
        this.senhaAtual = atualizaSenhaDto.senhaAtual;
        this.novaSenha = atualizaSenhaDto.novaSenha;
    }
}