package com.sportradar.demo.service;

import com.sportradar.demo.resource.ScoreBoardResource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(MockitoExtension.class)
public class CurrentMatchDataServiceTest {

    @InjectMocks
    private CurrentMatchDataService currentMatchDataService;

    @Test
    void whenCalledRetrieveCurrentMatches_ItShouldReturnScoreBoardResource() {
        //when
        List<ScoreBoardResource> scoreBoardResources = currentMatchDataService.retrieveCurrentMatches();

        //then
        then(scoreBoardResources).hasSize(1);

    }
}
