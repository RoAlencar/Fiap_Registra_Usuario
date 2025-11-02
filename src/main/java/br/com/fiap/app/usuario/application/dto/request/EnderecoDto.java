package br.com.fiap.app.usuario.application.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnderecoDto {

    @NotBlank
    private String logradouro;

    @NotBlank
    private String numero;

    private String complemento;

    @NotBlank
    private String cidade;

    @NotBlank
    private String cep;
}