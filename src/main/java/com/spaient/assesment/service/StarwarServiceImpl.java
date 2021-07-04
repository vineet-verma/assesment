package com.spaient.assesment.service;

import com.spaient.assesment.dao.DaoService;
import com.spaient.assesment.model.MovieDetail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class StarwarServiceImpl implements StarwarService {

    private final DaoService daoService;

    @Override
    public MovieDetail findDetails(String type, String name) throws Exception {
        switch (type) {
            case "planets":
                return daoService.findPlanetDetails(type, name);
            case "starships":
                return daoService.findSpaceshipsDetails(type, name);
            case "vehicle":
                return daoService.findVehicleDetails(type, name);
            case "people":
                return daoService.findPeopleDetails(type, name);
            case "films":
                return daoService.findFilmDetails(type, name);
            case "species":
                return daoService.findSpeciesDetails(type, name);
            default:
                throw new Exception("No macthed typed found, Please try with correct type");
        }

    }
}
