package com.spaient.assesment.service;

import com.spaient.assesment.model.Standing;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LeagueService {

    List<Standing> getStandings(String league_id);
}
