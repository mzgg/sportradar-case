package com.sportradar.demo.service.eventstatus;

import com.sportradar.demo.resource.GameEventResource;
import com.sportradar.demo.service.CurrentMatchDataService;
import com.sportradar.demo.service.SummaryScoreBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameFinishedAction implements GameStatusAction {

    private final SummaryScoreBoardService summaryScoreBoardService;

    private final CurrentMatchDataService currentMatchDataService;

    @Override
    public void changeDataAction(GameEventResource eventResource) {
        currentMatchDataService.updateAllGamesCache();
        summaryScoreBoardService.updateSummaryOfFinishedGameCache();
        log.info("game finished:{}", eventResource.getMatchName());
    }
}
