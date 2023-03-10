package com.sportradar.demo.service;

import com.sportradar.demo.model.Match;
import com.sportradar.demo.resource.GameEventResource;


import com.sportradar.demo.service.eventstatus.GameNotStartedAction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class GameEventServiceTest {

    @InjectMocks
    private GameEventService gameEventService;

    @Mock
    private MatchService matchService;

    @Mock
    private ApplicationContext context;

    @Mock
    private GameNotStartedAction gameNotStartedAction;


    @Test
    void whenCalledCreateGameEventIfNotExistInDb_ItShouldSaveToDb() {
        //given
        GameEventResource gameEvent = newMatchEventResource();

      Match match = newMatch();

        //when


        when(context.getBean(GameNotStartedAction.class)).thenReturn(gameNotStartedAction);
        doNothing().when(gameNotStartedAction).changeDataAction(gameEvent);

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
        GameEventResource gameEvent = newMatchEventResource();

        Optional<Match> match = Optional.of(newMatch());

        Match updatedMatch = newMatch();

        //when
        when(context.getBean(GameNotStartedAction.class)).thenReturn(gameNotStartedAction);
        doNothing().when(gameNotStartedAction).changeDataAction(gameEvent);

        when(matchService.findByHomeTeamNameAndAwayTeamNameAndStartDate(any(), any(), any())).thenReturn(match);
        when(matchService.save(any())).thenReturn(updatedMatch);
        Match savedMatch = gameEventService.createGameEvent(gameEvent);

        //then
        then(savedMatch.getMatchName()).isEqualTo(gameEvent.getMatchName());
        verify(matchService).findByHomeTeamNameAndAwayTeamNameAndStartDate(any(), any(), any());
        verify(matchService).save(any());

    }

    private GameEventResource newMatchEventResource() {
        return GameEventResource.builder()
                .matchDate("01/02/2023 07:00")
                .matchStatus("NOT_STARTED")
                .homeTeam("Mexico")
                .awayTeam("Canada")
                .build();
    }

    private Match newMatch() {
        return Match.builder()
                .matchStatus("NOT_STARTED")
                .homeTeamName("Mexico")
                .awayTeamName("Canada")
                .build();
    }
}
