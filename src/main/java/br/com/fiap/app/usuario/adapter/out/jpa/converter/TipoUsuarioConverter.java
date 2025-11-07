package br.com.fiap.app.usuario.adapter.out.jpa.converter;

import br.com.fiap.app.usuario.domain.enums.TipoUsuario;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoUsuarioConverter implements AttributeConverter<TipoUsuario, String> {

    @Override
    public String convertToDatabaseColumn(TipoUsuario tipo) {
        return tipo == null ? null : tipo.name();
    }

    @Override
    public TipoUsuario convertToEntityAttribute(String dbData) {
        System.out.println("Convertendo tipo do usuário do banco: " + dbData);
        try {
            return dbData == null ? null : TipoUsuario.valueOf(dbData);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
