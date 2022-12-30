package com.sportradar.demo.service.eventstatus;

import com.sportradar.demo.resource.GameEventResource;
import com.sportradar.demo.service.CurrentMatchDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameStartedAction implements GameStatusAction {

    private final CurrentMatchDataService currentMatchDataService;

    @Override
    public void changeDataAction(GameEventResource eventResource) {
        currentMatchDataService.updateAllGamesCache();
    }
}
