package io.javabrains.moviecatalogservice.service;

import io.javabrains.moviecatalogservice.client.MovieInfoServiceClient;
import io.javabrains.moviecatalogservice.client.RatingsDataServiceClient;
import io.javabrains.moviecatalogservice.model.CatalogItem;
import io.javabrains.moviecatalogservice.model.Movie;
import io.javabrains.moviecatalogservice.model.Rating;
import io.javabrains.moviecatalogservice.model.UserRating;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class MovieCatalogService {

    private RatingsDataServiceClient ratingsDataServiceClient;
    private MovieInfoServiceClient movieInfoServiceClient;

    public MovieCatalogService(RatingsDataServiceClient ratingsDataServiceClient, MovieInfoServiceClient movieInfoServiceClient) {
        this.ratingsDataServiceClient = ratingsDataServiceClient;
        this.movieInfoServiceClient = movieInfoServiceClient;
    }

    public List<CatalogItem> getCatalogs(@PathVariable String userId) {
        UserRating userRating = ratingsDataServiceClient.getUserRatings(userId);

        return userRating.getUserRating().stream()
                    .map(this::getCatalogItem)
                    .collect(toList());
    }

    private CatalogItem getCatalogItem(Rating rating) {
        Movie movie = movieInfoServiceClient.getMovieInfo(rating.getMovieId());
        return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
    }
}
