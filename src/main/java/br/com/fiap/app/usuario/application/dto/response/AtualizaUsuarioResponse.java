package br.com.fiap.app.usuario.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;

@Schema(description = "O campo 'senha' é exibido apenas para fins ilustrativos nesta entrega acadêmica.")
@NoArgsConstructor
public class AtualizaUsuarioResponse extends UsuarioBaseResponse {
    public AtualizaUsuarioResponse(UsuarioBaseResponse usuarioBaseResponse) {
        super(usuarioBaseResponse);
    }
}
