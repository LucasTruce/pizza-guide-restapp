package com.pizzaguideapp.models.steps;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StepsRepository extends JpaRepository<Steps, Long> {
}
