package br.com.fiap.app.usuario.application.dto.response;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AtualizaUsuarioResponse extends UsuarioBaseResponse {
    public AtualizaUsuarioResponse(UsuarioBaseResponse usuarioBaseResponse) {
        super(usuarioBaseResponse);
    }
}
