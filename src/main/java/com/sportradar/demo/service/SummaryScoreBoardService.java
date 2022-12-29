package com.sportradar.demo.service;

import com.sportradar.demo.resource.ScoreBoardResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SummaryScoreBoardService {

    private final MatchService matchService;

    public List<ScoreBoardResource> retrieveSummaryBoardByOrderedTotalScore() {
        return matchService
                .findByMatchStatusOrderByTotalScoreAndUpdatedDate().stream()
                .map(match -> ScoreBoardResource.builder()
                        .matchName(match.getMatchName())
                        .awayTeamScore(match.getAwayTeamScore())
                        .homeTeamScore(match.getHomeTeamScore())
                        .build())
                .toList();
    }
}
