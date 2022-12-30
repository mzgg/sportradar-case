package com.sportradar.demo.service.eventstatus;

import com.sportradar.demo.resource.GameEventResource;
import com.sportradar.demo.service.CurrentMatchDataService;
import com.sportradar.demo.service.SummaryScoreBoardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GameFinishedActionTest {

    @InjectMocks
    private GameFinishedAction gameFinishedAction;

    @Mock
    private  SummaryScoreBoardService summaryScoreBoardService;

    @Mock
    private  CurrentMatchDataService currentMatchDataService;

    @Test
    void whenCalledChangeDataAction_ItShouldMakeGameFinishedImpl() {
        //given
        GameEventResource event = GameEventResource.builder()
                .matchDate("1/2/2023 07:00")
                .matchStatus("NOT_STARTED")
                .homeTeam("Mexico")
                .awayTeam("Canada")
                .build();

        //when
        gameFinishedAction.changeDataAction(event);

        //then
        verify(summaryScoreBoardService).updateSummaryOfFinishedGameCache();
        verify(currentMatchDataService).updateAllGamesCache();



    }


}