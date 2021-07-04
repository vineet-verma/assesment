package com.spaient.assesment.service;

import com.spaient.assesment.model.MovieDetail;
import org.springframework.stereotype.Service;

@Service
public interface StarwarService {

    public MovieDetail findDetails(String type, String name) throws Exception;

}
