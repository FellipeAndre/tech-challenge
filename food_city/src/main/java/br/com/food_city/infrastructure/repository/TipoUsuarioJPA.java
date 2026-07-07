package br.com.food_city.infrastructure.repository;

import br.com.food_city.infrastructure.entity.TipoUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TipoUsuarioJPA extends JpaRepository<TipoUsuarioEntity, UUID> {

    TipoUsuarioEntity findByIdUsuario(UUID identificadorUsuario);
}
