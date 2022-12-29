package com.sportradar.demo.service;

import com.sportradar.demo.model.Match;
import com.sportradar.demo.resource.GameEventResource;

import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class GameEventServiceTest {

    @InjectMocks
    private GameEventService gameEventService;

    @Mock
    private MatchService matchService;


    @Test
    void whenCalledCreateGameEventIfNotExistInDb_ItShouldSaveToDb() {
        //given
        GameEventResource gameEvent = GameEventResource.builder()
                .matchDate("1/2/2023 07:00")
                .matchStatus("NOT_STARTED")
                .homeTeam("Mexico")
                .awayTeam("Canada")
                .build();

      Match match = Match.builder()
                .matchStatus("NOT_STARTED")
                .homeTeamName("Mexico")
                .awayTeamName("Canada")
                .build();

        //when
        when(matchService.findByHomeTeamNameAndAwayTeamNameAndStartDate(any(), any(), any())).thenReturn(Optional.empty());
        when(matchService.save(any())).thenReturn(match);
        Match savedMatch = gameEventService.createGameEvent(gameEvent);

        //then
        then(savedMatch.getMatchName()).isEqualTo(gameEvent.getMatchName());
        verify(matchService).findByHomeTeamNameAndAwayTeamNameAndStartDate(any(), any(), any());
        verify(matchService).save(any());

    }

    @Test
    void whenCalledCreateGameEventIfExistInDb_ItShouldUpdateToDb() {
        //given
        GameEventResource gameEvent = GameEventResource.builder()
                .matchDate("1/2/2023 07:00")
                .matchStatus("NOT_STARTED")
                .homeTeam("Mexico")
                .awayTeam("Canada")
                .build();

        Optional<Match> match = Optional.of(Match.builder()
                .matchStatus("NOT_STARTED")
                .homeTeamName("Mexico")
                .awayTeamName("Canada")
                .build());

        Match updatedMatch = Match.builder()
                .matchStatus("NOT_STARTED")
                .homeTeamName("Mexico")
                .awayTeamName("Canada")
                .build();


        //when
        when(matchService.findByHomeTeamNameAndAwayTeamNameAndStartDate(any(), any(), any())).thenReturn(match);
        when(matchService.save(any())).thenReturn(updatedMatch);
        Match savedMatch = gameEventService.createGameEvent(gameEvent);

        //then
        then(savedMatch.getMatchName()).isEqualTo(gameEvent.getMatchName());
        verify(matchService).findByHomeTeamNameAndAwayTeamNameAndStartDate(any(), any(), any());
        verify(matchService).save(any());

    }
}
