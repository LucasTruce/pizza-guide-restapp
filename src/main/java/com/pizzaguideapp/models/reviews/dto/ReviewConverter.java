package com.pizzaguideapp.models.reviews.dto;

import com.pizzaguideapp.models.reviews.Review;
import com.pizzaguideapp.models.user.dto.UserConverter;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
public class ReviewConverter {

    public ReviewRequestDto map(Review review) {
        UserConverter userConverter = new UserConverter();
        return new ReviewRequestDto(
                review.getScore(),
                review.getDescription(),
                userConverter.map(review.getUser())
        );
    }

    public Review map(ReviewRequestDto reviewRequestDto) {
        Review review = new Review();
        review.setDescription(reviewRequestDto.getDescription());
        review.setScore(reviewRequestDto.getScore());
        return review;
    }


    public List<ReviewRequestDto> map(List<Review> entityObjects){
        return entityObjects.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
