package com.sportradar.demo.service;

import com.sportradar.demo.model.Match;
import com.sportradar.demo.repository.MatchRepository;

import com.sportradar.demo.resource.ScoreBoardResource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SummaryScoreBoardServiceTest {

    @InjectMocks
    private SummaryScoreBoardService summaryScoreBoardService;

    @Mock
    private MatchService matchService;

    @Test
    void whenCalledRetrieveSummaryBoardByOrdered_ItShouldReturnScoreBoardResource() {
        //given
        List<Match> matches=List.of(Match.builder().homeTeamName("Test-team").build());

        //when

        List<ScoreBoardResource> scoreBoardResources =
                summaryScoreBoardService.retrieveSummaryBoardByOrderedTotalScore();

        //then
        then(scoreBoardResources).hasSize(1);
    }
}
