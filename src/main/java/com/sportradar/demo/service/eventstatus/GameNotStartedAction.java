package com.sportradar.demo.service.eventstatus;

import com.sportradar.demo.resource.GameEventResource;
import com.sportradar.demo.service.CurrentMatchDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameNotStartedAction implements GameStatusAction {

    private final CurrentMatchDataService currentMatchDataService;

    @Override
    public void changeDataAction(GameEventResource eventResource) {
        currentMatchDataService.updateAllGamesCache();
    }
}
