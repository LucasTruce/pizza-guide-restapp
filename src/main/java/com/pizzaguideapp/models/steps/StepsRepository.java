package com.pizzaguideapp.models.steps;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StepsRepository extends JpaRepository<Steps, Long> {
    List<Steps> findAllByRecipeId(Long id);
}
