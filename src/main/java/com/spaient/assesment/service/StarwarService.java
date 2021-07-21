package com.spaient.assesment.service;

import com.spaient.assesment.model.MovieDetail;
import com.spaient.assesment.model.PeopleResponse;
import org.springframework.stereotype.Service;

@Service
public interface StarwarService {

    public MovieDetail findDetails(String type, String name);

    public PeopleResponse findListByType(String type) throws Exception;

}
