package br.com.food_city.infrastructure.repository;

import br.com.food_city.infrastructure.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepositoryJpa extends JpaRepository<UsuarioEntity, UUID> {
}
