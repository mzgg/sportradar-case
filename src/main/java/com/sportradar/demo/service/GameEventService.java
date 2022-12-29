package com.sportradar.demo.service;

import com.sportradar.demo.model.Match;
import com.sportradar.demo.resource.GameEventResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameEventService {

    private final MatchService matchService;

    public Match createGameEvent(GameEventResource gameEvent) {
        Optional<Match> matchOptional = matchService
                .findByHomeTeamNameAndAwayTeamNameAndStartDate(gameEvent.getHomeTeam(),
                        gameEvent.getAwayTeam(), gameEvent.getMatchDate());

        if (matchOptional.isPresent()){
           return matchService.save(matchOptional.get());
        }
        Match match = Match.builder()
                .homeTeamName(gameEvent.getHomeTeam())
                .awayTeamName(gameEvent.getAwayTeam())
                .build();

        return matchService.save(match);
    }
}
