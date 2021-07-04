package com.spaient.assesment;

import com.spaient.assesment.model.MovieDetail;
import com.spaient.assesment.service.StarwarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/starwars")
public class StarwarController {

    @Autowired
    StarwarService starwarService;

    @RequestMapping(value = "/display/{type}/{name}",
            method = RequestMethod.GET)
    public MovieDetail findDetails(@PathVariable("type") String type, @PathVariable("name") String name) throws Exception {
        return starwarService.findDetails(type, name);


    }


}
