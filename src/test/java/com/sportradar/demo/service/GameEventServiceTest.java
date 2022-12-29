package com.sportradar.demo.service;

import com.sportradar.demo.model.Match;
import com.sportradar.demo.resource.GameEventResource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.BDDAssertions.then;


@ExtendWith(MockitoExtension.class)
public class GameEventServiceTest {

    @InjectMocks
    private GameEventService gameEventService;


    @Test
    void whenCalledCreateGameEvent_ItShouldSaveOrUpdateToDb() {
        //given
        GameEventResource gameEvent = new GameEventResource();

        //when
        Match savedMatch = gameEventService.createGameEvent(gameEvent);

        //then
        then(savedMatch.getMatchName()).isEqualTo(gameEvent.getMatchName());
    }
}
