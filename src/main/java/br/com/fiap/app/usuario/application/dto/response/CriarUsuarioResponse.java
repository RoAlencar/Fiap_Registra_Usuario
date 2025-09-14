package br.com.fiap.app.usuario.application.dto.response;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CriarUsuarioResponse extends UsuarioBaseResponse {

    public CriarUsuarioResponse(UsuarioBaseResponse usuarioBaseResponse) {
        super(usuarioBaseResponse);
    }
}
