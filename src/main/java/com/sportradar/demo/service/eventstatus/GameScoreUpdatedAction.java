package com.sportradar.demo.service.eventstatus;

import com.sportradar.demo.resource.GameEventResource;
import com.sportradar.demo.service.CurrentMatchDataService;
import com.sportradar.demo.service.SummaryScoreBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameScoreUpdatedAction implements GameStatusAction {

    private final SummaryScoreBoardService summaryScoreBoardService;

    private final CurrentMatchDataService currentMatchDataService;

    @Override
    public void changeDataAction(GameEventResource eventResource) {
        currentMatchDataService.updateAllGamesCache();
        summaryScoreBoardService.updateSummaryOfFinishedGameCache();
    }
}
