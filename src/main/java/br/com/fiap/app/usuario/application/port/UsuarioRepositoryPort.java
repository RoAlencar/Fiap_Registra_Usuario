package br.com.fiap.app.usuario.application.port;

import br.com.fiap.app.usuario.domain.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepositoryPort {

    List<Usuario> findAll();
    Optional<Usuario> findByNome(String name);
    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByEmail(String email);
    Usuario save(Usuario usuario);
    void deleteById(Long id);
}
