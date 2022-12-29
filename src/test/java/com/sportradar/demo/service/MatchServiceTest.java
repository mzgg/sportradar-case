package com.sportradar.demo.service;

import com.sportradar.demo.model.Match;
import com.sportradar.demo.repository.MatchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MatchServiceTest {

    @InjectMocks
    private MatchService matchService;

    @Mock
    private MatchRepository matchRepository;

    @Test
    void whenCalledSaveWithMatchObject_ItShouldReturnMatchEntity() {
        //given
        Match match = Match.builder()
                .matchStatus("NOT_STARTED")
                .homeTeamName("Mexico")
                .awayTeamName("Canada")
                .build();

        //when
        when(matchRepository.save(any())).thenReturn(match);
        Match savedMatch = matchService.save(match);

        //then
        verify(matchRepository).save(any());
        then(savedMatch.getMatchName()).isEqualTo(match.getMatchName());
    }
}
