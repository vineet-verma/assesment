package com.spaient.assesment.service;

import com.spaient.assesment.model.Standing;

import java.util.List;

public interface LeagueService {

    List<Standing> getStandings(String league_id);
}
