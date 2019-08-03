package io.javabrains.ratingsdataservice.resource;

import io.javabrains.ratingsdataservice.model.Rating;
import io.javabrains.ratingsdataservice.model.UserRating;
import io.javabrains.ratingsdataservice.service.RatingsDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

    private RatingsDataService ratingsDataService;

    public RatingsResource(RatingsDataService ratingsDataService) {
        this.ratingsDataService = ratingsDataService;
    }

    @GetMapping("/{movieId}")
    public Rating getRating(@PathVariable String movieId) {
        return new Rating(movieId,4);
    }

    @GetMapping("/users/{userId}")
    public UserRating getUserRatings(@PathVariable String userId) {
        return ratingsDataService.getUserRatings();
    }
}
