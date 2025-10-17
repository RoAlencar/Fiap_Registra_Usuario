package br.com.fiap.app.usuario.application.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AtualizaSenhaResponse {
    private String message;

    public AtualizaSenhaResponse(String message) {
        this.message = message;
    }

}
