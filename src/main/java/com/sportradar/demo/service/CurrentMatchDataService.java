package com.sportradar.demo.service;

import com.sportradar.demo.resource.ScoreBoardResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrentMatchDataService {

    private final MatchService matchService;

    public List<ScoreBoardResource> retrieveAllGamesDataInSystemSortedByUpdatedDate() {
        return matchService
                .findByMatchStatusIn(List.of("GAME_STARTED", "UPDATE_SCORE")).stream()
                .map(match -> ScoreBoardResource.builder()
                        .matchName(match.getMatchName())
                        .awayTeamScore(match.getAwayTeamScore())
                        .homeTeamScore(match.getHomeTeamScore())
                        .build())
                .toList();
    }
}
