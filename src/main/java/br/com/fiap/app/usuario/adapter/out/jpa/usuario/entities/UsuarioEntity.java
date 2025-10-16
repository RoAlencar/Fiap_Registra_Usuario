package br.com.fiap.app.usuario.adapter.out.jpa.usuario.entities;

import br.com.fiap.app.usuario.adapter.out.jpa.converter.TipoUsuarioConverter;
import br.com.fiap.app.usuario.domain.Endereco;
import br.com.fiap.app.usuario.domain.Usuario;
import br.com.fiap.app.usuario.domain.enums.TipoUsuario;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Entity(name = "CORE_USUARIO")
public class UsuarioEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Access(AccessType.FIELD)
    @Column(name = "TIPO")
    @Convert(converter = TipoUsuarioConverter.class)
    private TipoUsuario tipo;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "SENHA")
    private String senha;

    @Column(name = "DATA_ULTIMA_ATUALIZACAO")
    private LocalDateTime dataUltimaAtualizacao;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "logradouro", column = @Column(name = "ENDERECO_LOGRADOURO")),
            @AttributeOverride(name = "numero", column = @Column(name = "ENDERECO_NUMERO")),
            @AttributeOverride(name = "complemento", column = @Column(name = "ENDERECO_COMPLEMENTO")),
            @AttributeOverride(name = "cidade", column = @Column(name = "ENDERECO_CIDADE")),
            @AttributeOverride(name = "cep", column = @Column(name = "ENDERECO_CEP"))
    })
    private Endereco endereco;

    public Usuario toDomain(){
        return Usuario.builder()
                .id(this.id)
                .tipo(this.tipo)
                .nome(this.nome)
                .email(this.email)
                .login(this.login)
                .senha(this.senha)
                .dataUltimaAtualizacao(this.dataUltimaAtualizacao)
                .endereco(this.endereco)
                .build();
    }


}
