package br.com.fiap.app.usuario.adapter.out.jpa.usuario.repositories;

import br.com.fiap.app.usuario.adapter.out.jpa.usuario.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity,Long> {

    Optional<UsuarioEntity> findByName(String name);
}
