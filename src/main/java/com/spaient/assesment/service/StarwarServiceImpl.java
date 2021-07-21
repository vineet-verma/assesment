package com.spaient.assesment.service;

import com.spaient.assesment.dao.DaoService;
import com.spaient.assesment.exception.BadRequestException;
import com.spaient.assesment.model.EventType;
import com.spaient.assesment.model.MovieDetail;
import com.spaient.assesment.model.PeopleResponse;
import com.spaient.assesment.model.TAPIResponse;
import com.spaient.assesment.util.Helper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class StarwarServiceImpl implements StarwarService {

    private final DaoService daoService;

    @Override
    public MovieDetail findDetails(String type, String name) {
        switch (type) {
            case "planets":
                return daoService.findPlanetDetails(type, name);
            case "starships":
                return daoService.findSpaceshipsDetails(type, name);
            case "vehicles":
                return daoService.findVehicleDetails(type, name);
            case "people":
                return daoService.findPeopleDetails(type, name);
            case "films":
                return daoService.findFilmDetails(type, name);
            case "species":
                return daoService.findSpeciesDetails(type, name);
            default:
                throw new BadRequestException("No matched typed found, Please try with correct type");
        }

    }

    @Override
    public PeopleResponse findListByType(String type) throws Exception {
        if (!Helper.validateStarWarsType(type)) {
            throw new BadRequestException("type is not valid, Please send valid types e.g: " + EventType.values());
        }
        TAPIResponse tapiResponse = daoService.findListByType(type);
        if (tapiResponse != null) {
            return Helper.convertDTOinResponse(tapiResponse);
        }
        return null;
    }
}
