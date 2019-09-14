package io.javabrains.moviecatalogservice.resource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javabrains.moviecatalogservice.model.CatalogItem;
import io.javabrains.moviecatalogservice.service.MovieCatalogService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.File;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieCatalogResource.class)
class MovieCatalogResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieCatalogService movieCatalogService;

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void getCatalogs() throws Exception {

        doReturn(getCatalogsData()).when(movieCatalogService).getCatalogs(anyString());

        ResultActions result = mockMvc.perform(get("/catalog/rashid"));

        result.andExpect(status().isOk());
    }

    private List<CatalogItem> getCatalogsData() throws Exception {
         return objectMapper.readValue(
                 new File("src/test/resources/catalogsData.json"), new TypeReference<List<CatalogItem>>(){});
    }
}