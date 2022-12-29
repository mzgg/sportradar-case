package com.sportradar.demo.service;

import com.sportradar.demo.model.Match;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(MockitoExtension.class)
public class MatchServiceTest {

    @InjectMocks
    private MatchService matchService;

    @Test
    void whenCalledSaveWithMatchObject_ItShouldReturnMatchEntity() {
        //given
        Match match = Match.builder()
                .matchStatus("NOT_STARTED")
                .homeTeamName("Mexico")
                .awayTeamName("Canada")
                .build();

        //when
        Match savedMatch = matchService.save(match);

        //then
        then(savedMatch.getMatchName()).isEqualTo(match.getMatchName());
    }
}
