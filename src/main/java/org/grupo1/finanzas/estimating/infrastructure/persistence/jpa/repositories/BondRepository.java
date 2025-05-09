package org.grupo1.finanzas.estimating.infrastructure.persistence.jpa.repositories;

import org.grupo1.finanzas.estimating.domain.model.aggregates.Bond;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BondRepository extends JpaRepository<Bond, Long> {
    List<Bond> findByUserId(Long userId);

}
