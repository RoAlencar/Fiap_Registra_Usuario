package br.com.fiap.app.usuario.adapter.out.jpa.usuario.repositories;

import br.com.fiap.app.usuario.adapter.out.jpa.usuario.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {

    @Query(value = "SELECT * FROM core_usuario WHERE unaccent(LOWER(nome)) LIKE unaccent(LOWER(CONCAT('%', :nome, '%')))", nativeQuery = true)
    List<Optional<UsuarioEntity>> findByNomeLikeIgnoreCaseAndAccent(String nome);

    Optional<UsuarioEntity> findByEmail(String email);
}
