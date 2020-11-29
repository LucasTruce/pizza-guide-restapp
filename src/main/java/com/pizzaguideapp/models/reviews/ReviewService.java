package com.pizzaguideapp.models.reviews;

import com.pizzaguideapp.exception.EntityNotFoundException;
import com.pizzaguideapp.models.recipes.Recipe;
import com.pizzaguideapp.models.recipes.RecipeRepository;
import com.pizzaguideapp.models.reviews.dto.ReviewConverter;
import com.pizzaguideapp.models.reviews.dto.ReviewRequestDto;
import com.pizzaguideapp.models.user.User;
import com.pizzaguideapp.models.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewConverter reviewConverter;
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, RecipeRepository recipeRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.reviewConverter = new ReviewConverter();
    }

    public ReviewRequestDto saveReview(ReviewRequestDto requestDto, String login, Long recipeId){
        Review review = reviewConverter.map(requestDto);
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new EntityNotFoundException("Recipe not found!"));
        User user = userRepository.findUserByUsername(login).orElseThrow(() -> new EntityNotFoundException("User not found!"));

        review.setRecipe(recipe);
        review.setUser(user);

        return reviewConverter.map(reviewRepository.save(review));
    }

    public List<ReviewRequestDto> getReviewsForRecipe(Long recipeId) {
        return reviewConverter.map(reviewRepository.findAllByRecipeId(recipeId));
    }

    public List<ReviewRequestDto> getReviewsForUser(String username){
        return reviewConverter.map(reviewRepository.findAllByUserUsername(username));
    }
}
