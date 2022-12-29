package com.sportradar.demo.service;

import com.sportradar.demo.model.Match;
import com.sportradar.demo.resource.GameEventResource;
import org.springframework.stereotype.Service;

@Service
public class GameEventService {

    public Match createGameEvent(GameEventResource gameEvent) {
        return Match.builder()
                .homeTeamName(gameEvent.getHomeTeam())
                .awayTeamName(gameEvent.getAwayTeam())
                .build();
    }
}
