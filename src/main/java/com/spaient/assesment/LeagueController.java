package com.spaient.assesment;

import com.spaient.assesment.model.Standing;
import com.spaient.assesment.service.LeagueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/league")
public class LeagueController {

    @Autowired
    LeagueService leagueService;


    @RequestMapping(value = "/standings/{leagueId}",
            method = RequestMethod.GET)
    public List<Standing> listFootballStandings(@PathVariable("leagueId") String leagueId) {
        return leagueService.getStandings(leagueId);
    }
}
