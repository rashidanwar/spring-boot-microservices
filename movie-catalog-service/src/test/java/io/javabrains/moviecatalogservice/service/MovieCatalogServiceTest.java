package io.javabrains.moviecatalogservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javabrains.moviecatalogservice.client.MovieInfoServiceClient;
import io.javabrains.moviecatalogservice.client.RatingsDataServiceClient;
import io.javabrains.moviecatalogservice.model.CatalogItem;
import io.javabrains.moviecatalogservice.model.Movie;
import io.javabrains.moviecatalogservice.model.UserRating;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

class MovieCatalogServiceTest {

    private MovieCatalogService movieCatalogService;
    private ObjectMapper objectMapper;

    @Mock
    private RatingsDataServiceClient ratingsDataServiceClient;

    @Mock
    private MovieInfoServiceClient movieInfoServiceClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        objectMapper = new ObjectMapper();
        movieCatalogService = new MovieCatalogService(this.ratingsDataServiceClient, this.movieInfoServiceClient);
    }

    @Test
    void getCatalogs() throws IOException {
        doReturn(getUserRatings()).when(ratingsDataServiceClient).getUserRatings(anyString());
        doReturn(getMovieInfo("550"), getMovieInfo("551")).when(movieInfoServiceClient).getMovieInfo(anyString());

        List<CatalogItem> catalogs = movieCatalogService.getCatalogs("rashid");
        assertThat(catalogs.get(0).getName()).isEqualTo("Fight Club");
    }

    private Movie getMovieInfo(String movieId) throws IOException {
        if ("550".equals(movieId)) {
            return objectMapper.readValue(new File("src/test/resources/movieInfo_550.json"), Movie.class);
        } else {
            return objectMapper.readValue(new File("src/test/resources/movieInfo_551.json"), Movie.class);
        }
    }

    private UserRating getUserRatings() throws IOException {
        return objectMapper.readValue(new File("src/test/resources/userRatings.json"), UserRating.class);
    }

    @AfterEach
    void tearDown() {
    }
}