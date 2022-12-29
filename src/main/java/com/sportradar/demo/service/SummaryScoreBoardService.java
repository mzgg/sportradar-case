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
public class SummaryScoreBoardService {

    private final MatchService matchService;

    @Cacheable(value = "summary_games", unless = "#result.isEmpty()")
    public List<ScoreBoardResource> retrieveSummaryBoardByOrderedTotalScore() {
        return matchService
                .findByMatchStatusOrderByTotalScoreAndUpdatedDate().stream()
                .map(match -> ScoreBoardResource.builder()
                        .matchName(match.getMatchName())
                        .awayTeamScore(match.getAwayTeamScore())
                        .homeTeamScore(match.getHomeTeamScore())
                        .build())
                .peek(r -> log.info("Summary scoreBoard is cached"))
                .toList();
    }

    @CachePut(value = "summary_games")
    public List<ScoreBoardResource> updateSummaryOfFinishedGameCache() {
        return retrieveSummaryBoardByOrderedTotalScore();
    }
}
