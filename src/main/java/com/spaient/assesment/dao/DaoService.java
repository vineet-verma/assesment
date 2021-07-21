package com.spaient.assesment.dao;

import com.spaient.assesment.model.MovieDetail;
import com.spaient.assesment.model.TAPIResponse;
import org.springframework.stereotype.Service;

@Service
public interface DaoService {

    public MovieDetail findPlanetDetails(String type, String name);
    public MovieDetail findSpaceshipsDetails(String type, String name);
    public MovieDetail findVehicleDetails(String type, String name);
    public MovieDetail findPeopleDetails(String type, String name);
    public MovieDetail findFilmDetails(String type, String name);
    public MovieDetail findSpeciesDetails(String type, String name);
    public TAPIResponse findListByType(String type);
}
