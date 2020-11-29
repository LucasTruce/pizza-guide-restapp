package com.pizzaguideapp.models.reviews;

import com.pizzaguideapp.models.reviews.dto.ReviewRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/recipes/{id}/reviews")
    public ResponseEntity<List<ReviewRequestDto>> getReviewsForRecipe(@PathVariable Long id){
        return new ResponseEntity<>(reviewService.getReviewsForRecipe(id), HttpStatus.OK);
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewRequestDto>> getReviewsForUser(Principal principal){
        return new ResponseEntity<>(reviewService.getReviewsForUser(principal.getName()), HttpStatus.OK);
    }

    @PostMapping("/recipes/{id}/reviews")
    public ResponseEntity<ReviewRequestDto> saveReview(@PathVariable(name = "id") Long id,
                                                       @RequestBody @Valid ReviewRequestDto requestDto,
                                                       Principal principal){
        return new ResponseEntity<>(reviewService.saveReview(requestDto, principal.getName(), id), HttpStatus.OK);

    }



}
