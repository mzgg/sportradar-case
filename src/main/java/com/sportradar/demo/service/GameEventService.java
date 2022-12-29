package com.sportradar.demo.service;

import com.sportradar.demo.model.Match;
import com.sportradar.demo.resource.GameEventResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameEventService {

    private final MatchService matchService;

    public Match createGameEvent(GameEventResource gameEvent) {
        return getMatchDataIfExistInDatabase(gameEvent)
                .map(match -> updateMatchDataByNewEvent(gameEvent, match))
                .orElseGet(() -> createNewMatchObject(gameEvent));


    }

    private Match updateMatchDataByNewEvent(GameEventResource gameEvent, Match match) {
        match.setMatchStatus(gameEvent.getMatchStatus());
        match.setHomeTeamScore(gameEvent.getHomeTeamScore());
        match.setAwayTeamScore(gameEvent.getAwayTeamScore());
        match.setUpdatedDate(LocalDateTime.now());
        match = matchService.save(match);
        return match;
    }


    private Match createNewMatchObject(GameEventResource gameEvent) {
        Match match = Match.builder()
                .homeTeamName(gameEvent.getHomeTeam())
                .awayTeamName(gameEvent.getAwayTeam())
                .build();

        return matchService.save(match);
    }

    private Optional<Match> getMatchDataIfExistInDatabase(GameEventResource gameEvent) {
        return matchService.findByHomeTeamNameAndAwayTeamNameAndStartDate(gameEvent.getHomeTeam(),
                gameEvent.getAwayTeam(), gameEvent.getMatchDate());
    }
}
