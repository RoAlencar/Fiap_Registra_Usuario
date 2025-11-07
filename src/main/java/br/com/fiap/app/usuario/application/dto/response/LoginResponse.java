package br.com.fiap.app.usuario.application.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private Long id;
    private String nome;
    private String tipo;
    private String mensagem;
}