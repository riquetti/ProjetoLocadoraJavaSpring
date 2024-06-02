package com.riquetti.ProjetoLocadora.repository;

import com.riquetti.ProjetoLocadora.entity.FabricanteEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FabricanteRepository extends ListCrudRepository<FabricanteEntity,Long> {
}
