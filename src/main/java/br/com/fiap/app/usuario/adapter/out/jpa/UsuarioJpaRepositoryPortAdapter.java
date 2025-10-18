package br.com.fiap.app.usuario.adapter.out.jpa;

import br.com.fiap.app.usuario.adapter.out.jpa.usuario.entities.UsuarioEntity;
import br.com.fiap.app.usuario.adapter.out.jpa.usuario.repositories.UsuarioJpaRepository;
import br.com.fiap.app.usuario.application.port.UsuarioRepositoryPort;
import br.com.fiap.app.usuario.domain.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UsuarioJpaRepositoryPortAdapter implements UsuarioRepositoryPort {

    private final UsuarioJpaRepository usuarioJpaRepository;

    @Override
    public List<Usuario> findAll() {
        return usuarioJpaRepository.findAll().stream().map(UsuarioEntity::toDomain).toList();
    }

    @Override
    public List<Usuario> findByNomeLikeIgnoreCaseAndAccent(String nome) {
        return usuarioJpaRepository.findByNomeLikeIgnoreCaseAndAccent(nome).stream()
                .filter(Optional::isPresent)
                .map(optionalEntity -> optionalEntity.get().toDomain())
                .toList();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioJpaRepository.findById(id).map(UsuarioEntity::toDomain);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usuarioJpaRepository.findByEmail(email).map(UsuarioEntity::toDomain);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioJpaRepository.save(usuario.toEntity()).toDomain();
    }

    @Override
    public void deleteById(Long id) {
    }
}
