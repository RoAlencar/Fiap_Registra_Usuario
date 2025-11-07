package br.com.fiap.app.usuario.application.port;

import br.com.fiap.app.usuario.domain.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepositoryPort {

    List<Usuario> findAll();

    List<Usuario> findByNomeLikeIgnoreCaseAndAccent(String name);

    Optional<Usuario> findById(Long id);

    Optional<Usuario> findByEmail(String email);

    Usuario save(Usuario usuario);

    void deleteById(Long id);

    Optional<Usuario> findByLogin(String login);
}
