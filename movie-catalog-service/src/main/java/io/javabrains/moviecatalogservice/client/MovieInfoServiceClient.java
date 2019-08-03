package io.javabrains.moviecatalogservice.client;

import io.javabrains.moviecatalogservice.model.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("movie-info-service")
@RequestMapping("/movies")
public interface MovieInfoServiceClient {

    @GetMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable String movieId);
}
