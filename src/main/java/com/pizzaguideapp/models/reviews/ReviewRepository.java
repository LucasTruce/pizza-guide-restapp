package com.pizzaguideapp.models.reviews;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByRecipeId(Long id);
    List<Review> findAllByUserUsername(String username);
}
