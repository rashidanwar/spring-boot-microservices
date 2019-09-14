package io.javabrains.moviecatalogservice.resource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javabrains.moviecatalogservice.client.MovieInfoServiceClient;
import io.javabrains.moviecatalogservice.client.RatingsDataServiceClient;
import io.javabrains.moviecatalogservice.model.CatalogItem;
import io.javabrains.moviecatalogservice.model.Movie;
import io.javabrains.moviecatalogservice.model.UserRating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieCatalogIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private RatingsDataServiceClient ratingsDataServiceClient;

    @MockBean
    private MovieInfoServiceClient movieInfoServiceClient;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void getCatalogs() throws Exception {

        doReturn(getUserRatings()).when(ratingsDataServiceClient).getUserRatings(anyString());
        doReturn(getMovieInfo("550"), getMovieInfo("551")).when(movieInfoServiceClient).getMovieInfo(anyString());

        String catalogDataAsString = restTemplate.getForObject("/catalog/rashid", String.class);

        List<CatalogItem> catalogDatas = objectMapper.readValue(catalogDataAsString, new TypeReference<List<CatalogItem>>() {});
        assertThat(getCatalogsData()).isEqualTo(catalogDatas);
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

    private List<CatalogItem> getCatalogsData() throws Exception {
        return objectMapper.readValue(
                    new File("src/test/resources/catalogsData.json"), new TypeReference<List<CatalogItem>>() {});
    }
}
