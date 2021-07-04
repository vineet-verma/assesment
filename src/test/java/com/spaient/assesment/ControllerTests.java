package com.spaient.assesment;

import com.spaient.assesment.service.LeagueService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@ComponentScan
public class ControllerTests {
    @Autowired
    LeagueService leagueService;

    @Autowired
    MockMvc mvc;


    @BeforeEach
    void configureApplication() {

    }

    @Test
    public void listFootballStandingsTest() throws Exception{

        mvc.perform(get("/league/standings/148"))
                .andDo(print())
                .andExpect(status().isOk());


    }
    @Test
    public void listFootballCountries() throws Exception{

        mvc.perform(get("/league/standings/country"))
                .andDo(print())
                .andExpect(status().isOk());


    }
}
