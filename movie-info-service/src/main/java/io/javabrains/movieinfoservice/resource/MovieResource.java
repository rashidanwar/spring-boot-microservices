package io.javabrains.movieinfoservice.resource;

import io.javabrains.movieinfoservice.model.Movie;
import io.javabrains.movieinfoservice.service.MovieInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    private MovieInfoService movieInfoService;

    public MovieResource(MovieInfoService movieInfoService) {
        this.movieInfoService = movieInfoService;
    }

    @GetMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable String movieId) {
        return movieInfoService.getMovieInfo(movieId);
    }
}
