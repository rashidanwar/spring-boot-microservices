package io.javabrains.ratingsdataservice.service;

import io.javabrains.ratingsdataservice.model.Rating;
import io.javabrains.ratingsdataservice.model.UserRating;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RatingsDataService {

    public UserRating getUserRatings() {
        List<Rating> ratings =
                Arrays.asList(
                        new Rating("550",4),
                        new Rating("551",5)
                );

        UserRating userRating = new UserRating();
        userRating.setUserRating(ratings);

        return userRating;
    }
}
