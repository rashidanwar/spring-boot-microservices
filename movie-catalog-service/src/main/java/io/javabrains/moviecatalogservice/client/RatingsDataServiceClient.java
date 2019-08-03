package io.javabrains.moviecatalogservice.client;

import io.javabrains.moviecatalogservice.model.UserRating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("ratings-data-service")
@RequestMapping("/ratingsdata")
public interface RatingsDataServiceClient {

    @GetMapping("/users/{userId}")
    public UserRating getUserRatings(@PathVariable String userId);
}
