package com.spaient.assesment;

import com.spaient.assesment.model.MovieDetail;
import com.spaient.assesment.model.PeopleResponse;
import com.spaient.assesment.service.StarwarService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@ComponentScan
public class ControllerTests {
    @Mock
    StarwarService starwarService;

    MockMvc mockMvc;

    @InjectMocks
    StarwarController starwarController;

    @BeforeEach
    void configureApplication() throws Exception {
        mockMvc = standaloneSetup(starwarController).build();
        when(starwarService.findDetails(anyString(), anyString()))
                .thenReturn(MovieDetail.builder().build());

        when(starwarService.findListByType(anyString()))
                .thenReturn(PeopleResponse.builder().build());
    }

    @Test
    public void getPlanetDetailsTest() throws Exception {

        mockMvc.perform(get("/starwars/display/planets/Tatooine"))
                .andDo(print())
                .andExpect(status().isOk());


    }

    @Test
    public void getStartshipDetailsTest() throws Exception {

        mockMvc.perform(get("/starwars/display/starships/X-wing"))
                .andDo(print())
                .andExpect(status().isOk());


    }

    @Test
    public void getVehicleDetailsTest() throws Exception {

        mockMvc.perform(get("/starwars/display/vehicle/Snowspeeder"))
                .andDo(print())
                .andExpect(status().isOk());


    }

    @Test
    public void getPeopleDetailsTest() throws Exception {

        mockMvc.perform(get("/starwars/display/people/Luke Skywalker"))
                .andDo(print())
                .andExpect(status().isOk());


    }

    @Test
    public void getFilmsDetailsTest() throws Exception {

        mockMvc.perform(get("/starwars/display/films/A New Hope"))
                .andDo(print())
                .andExpect(status().isOk());


    }

    @Test
    public void getSpeciesDetailsTest() throws Exception {

        mockMvc.perform(get("/starwars/display/species/Rodian"))
                .andDo(print())
                .andExpect(status().isOk());


    }

    @Test
    public void findListTypesTest() throws Exception {

        mockMvc.perform(get("/starwars/display/people"))
                .andDo(print())
                .andExpect(status().isOk());


    }

}
