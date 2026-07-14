package br.com.food_city.infrastructure.repository;

import br.com.food_city.infrastructure.entity.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RestauranteRepositoryJPA extends JpaRepository<RestauranteEntity, UUID> {

   // List<RestauranteEntity> findByDonoId(UUID donoId);
}
