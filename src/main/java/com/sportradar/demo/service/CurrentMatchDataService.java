package com.sportradar.demo.service;

import com.sportradar.demo.resource.ScoreBoardResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class CurrentMatchDataService {

    private final MatchService matchService;

    @Cacheable(value = "current_games", unless = "#result.isEmpty()")
    public List<ScoreBoardResource> retrieveAllGamesDataInSystemSortedByUpdatedDate() {
        return matchService
                .findByMatchStatusIn(List.of("NOT_STARTED","GAME_STARTED", "UPDATE_SCORE")).stream()
                .map(match -> ScoreBoardResource.builder()
                        .matchName(match.getMatchName())
                        .awayTeamScore(match.getAwayTeamScore())
                        .homeTeamScore(match.getHomeTeamScore())
                        .build())
                .peek(r -> log.info("Current games is cached"))
                .toList();
    }

    @CachePut(value = "current_games")
    public List<ScoreBoardResource> updateAllGamesCache() {
        return retrieveAllGamesDataInSystemSortedByUpdatedDate();
    }
}
