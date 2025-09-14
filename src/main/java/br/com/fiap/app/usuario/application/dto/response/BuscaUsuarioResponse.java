package br.com.fiap.app.usuario.application.dto.response;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BuscaUsuarioResponse extends UsuarioBaseResponse {

    public BuscaUsuarioResponse(UsuarioBaseResponse usuarioBaseResponse) {
        super(usuarioBaseResponse);
    }
}
