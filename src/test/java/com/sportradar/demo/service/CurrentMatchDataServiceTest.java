package com.sportradar.demo.service;

import com.sportradar.demo.model.Match;
import com.sportradar.demo.resource.ScoreBoardResource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurrentMatchDataServiceTest {

    @InjectMocks
    private CurrentMatchDataService currentMatchDataService;

    @Mock
    private MatchService matchService;

    @Test
    void whenCalledRetrieveCurrentMatches_ItShouldReturnScoreBoardResource() {
        //given
        List<Match> matches=List.of(Match.builder().homeTeamName("Test-team").build());
        List<String> filters = List.of("GAME_STARTED", "UPDATE_SCORE");


        //when
        when(matchService.findByMatchStatusIn(filters)).thenReturn(matches);
        List<ScoreBoardResource> scoreBoardResources = currentMatchDataService.retrieveAllGamesDataInSystemSortedByUpdatedDate();

        //then
        verify(matchService).findByMatchStatusIn(filters);
        then(scoreBoardResources).hasSize(1);

    }
}
