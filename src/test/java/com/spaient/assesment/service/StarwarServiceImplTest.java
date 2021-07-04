package com.spaient.assesment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spaient.assesment.dao.DaoService;
import com.spaient.assesment.http.service.HttpProxyInvocationService;
import com.spaient.assesment.model.MovieDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@ComponentScan
class StarwarServiceImplTest {

    @Autowired
    StarwarService starwarService;

    @Autowired
    MockMvc mvc;

    @Autowired
    HttpProxyInvocationService httpProxyInvocationService;

    @Autowired
    DaoService daoService;

    @Autowired
    ObjectMapper objectMapper;

    @Mock
    RestTemplate restTemplate;

    @BeforeEach
    void configureApplication() {
    }

    @Test
    @ExceptionHandler
    void getSpeciesTest() throws Exception {
        starwarService = new StarwarServiceImpl(daoService);
        MovieDetail movieDetail = starwarService.findDetails("species", "Rodian");
        Assertions.assertEquals("species", movieDetail.getType());
    }

    @Test
    @ExceptionHandler
    void getSpeciesNegativeTest() throws Exception {
        starwarService = new StarwarServiceImpl(daoService);
        MovieDetail movieDetail = starwarService.findDetails("species", "abc");
        Assertions.assertNull(movieDetail.getObject());
    }

    @Test
    @ExceptionHandler
    void getStarshipTest() throws Exception {
        starwarService = new StarwarServiceImpl(daoService);
        MovieDetail movieDetail = starwarService.findDetails("starships", "X-wing");
        Assertions.assertEquals("starships", movieDetail.getType());
    }

    @Test
    @ExceptionHandler
    void getStarshipNegativeTest() throws Exception {
        starwarService = new StarwarServiceImpl(daoService);
        MovieDetail movieDetail = starwarService.findDetails("starships", "asd");
        Assertions.assertNull(movieDetail.getObject());
    }

    @Test
    @ExceptionHandler
    void getPeopleTest() throws Exception {
        starwarService = new StarwarServiceImpl(daoService);
        MovieDetail movieDetail = starwarService.findDetails("people", "Luke Skywalker");
        Assertions.assertEquals("people", movieDetail.getType());
    }

}