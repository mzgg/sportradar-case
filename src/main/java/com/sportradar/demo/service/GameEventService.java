package com.sportradar.demo.service;

import com.sportradar.demo.model.Match;
import com.sportradar.demo.resource.GameEventResource;
import com.sportradar.demo.util.DateUtil;
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
        match.setUpdatedDate(LocalDateTime.now());
        match = matchService.save(match);
        log.info("match updated, matchName:{}, status:{}", match.getMatchName(), match.getMatchStatus());

        return match;
    }


    private Match createNewMatchObject(GameEventResource gameEvent) {
        Match match = Match.builder()
                .homeTeamName(gameEvent.getHomeTeam())
                .awayTeamName(gameEvent.getAwayTeam())
                .awayTeamScore(gameEvent.getAwayTeamScore())
                .homeTeamScore(gameEvent.getHomeTeamScore())
                .matchStatus(gameEvent.getMatchStatus())
                .startDate(DateUtil.toLocalDateFromString(gameEvent.getMatchDate()))
                .createdDate(LocalDateTime.now())
                .build();

        match = matchService.save(match);
        log.info("new match created, matchName:{}, status:{}", match.getMatchName(), match.getMatchStatus());
        return match;
    }

    private Optional<Match> getMatchDataIfExistInDatabase(GameEventResource gameEvent) {
        return matchService.findByHomeTeamNameAndAwayTeamNameAndStartDate(gameEvent.getHomeTeam(),
                gameEvent.getAwayTeam(), gameEvent.getMatchDate());
    }
}
