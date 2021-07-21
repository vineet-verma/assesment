package com.spaient.assesment;

import com.spaient.assesment.exception.BadRequestException;
import com.spaient.assesment.exception.NoRecordFoundException;
import com.spaient.assesment.model.MovieDetail;
import com.spaient.assesment.model.PeopleResponse;
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
        MovieDetail result = null;
        try {
            result = starwarService.findDetails(type, name);
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        }
        if (result == null) {
            throw new NoRecordFoundException("No Record found with type : " + type + " and name :" + name);
        }
        return result;
    }

    @RequestMapping(value = "/display/{type}",
            method = RequestMethod.GET)
    public PeopleResponse findListByType(@PathVariable("type") String type) throws Exception {
        PeopleResponse result = null;
        try {
            result = starwarService.findListByType(type);
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        }
        if (result == null) {
            throw new NoRecordFoundException("No Record found with type : " + type);
        }
        return result;

    }


}
