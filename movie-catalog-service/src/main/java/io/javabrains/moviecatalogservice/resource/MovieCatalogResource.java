package io.javabrains.moviecatalogservice.resource;

import io.javabrains.moviecatalogservice.model.CatalogItem;
import io.javabrains.moviecatalogservice.service.MovieCatalogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    private MovieCatalogService movieCatalogService;

    public MovieCatalogResource(MovieCatalogService movieCatalogService) {
        this.movieCatalogService = movieCatalogService;
    }

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalogs(@PathVariable String userId) {
        return movieCatalogService.getCatalogs(userId);
    }
}
