package com.spaient.assesment;

import com.spaient.assesment.service.StarwarService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@ComponentScan
public class ControllerIntegrationTests {
    @Autowired
    StarwarService starwarService;

    @Autowired
    MockMvc mvc;


    @BeforeEach
    void configureApplication() {

    }

    @Test
    public void getPlanetDetailsTest() throws Exception {

        mvc.perform(get("/starwars/display/planets/Tatooine"))
                .andDo(print())
                .andExpect(status().isOk());


    }

    @Test
    public void getStartshipDetailsTest() throws Exception {

        mvc.perform(get("/starwars/display/starships/X-wing"))
                .andDo(print())
                .andExpect(status().isOk());


    }

    @Test
    public void getVehicleDetailsTest() throws Exception {

        mvc.perform(get("/starwars/display/vehicles/Snowspeeder"))
                .andDo(print())
                .andExpect(status().isOk());


    }

    @Test
    public void getPeopleDetailsTest() throws Exception {

        mvc.perform(get("/starwars/display/people/Luke Skywalker"))
                .andDo(print())
                .andExpect(status().isOk());


    }

    @Test
    public void getFilmsDetailsTest() throws Exception {

        mvc.perform(get("/starwars/display/films/A New Hope"))
                .andDo(print())
                .andExpect(status().isOk());


    }

    @Test
    public void getSpeciesDetailsTest() throws Exception {

        mvc.perform(get("/starwars/display/species/Rodian"))
                .andDo(print())
                .andExpect(status().isOk());


    }

    @Test
    public void findListTypesTest() throws Exception {

        mvc.perform(get("/starwars/display/people"))
                .andDo(print())
                .andExpect(status().isOk());


    }

}
