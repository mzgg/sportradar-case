package com.sportradar.demo.enums;


import com.sportradar.demo.service.eventstatus.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum GameStatusEnum {
    NOT_STARTED("NOT_STARTED", GameNotStartedAction.class),
    GAME_STARTED("GAME_STARTED", GameStartedAction.class),
    GAME_FINISHED("GAME_FINISHED", GameFinishedAction.class),
    UPDATE_SCORE("UPDATE_SCORE", GameScoreUpdatedAction.class);


    public final String status;
    public final Class<? extends GameStatusAction> aClass;

    public static GameStatusEnum getStatusEnumByStringValue(String status) {
        return Arrays.stream(GameStatusEnum.values())
                .filter(gameStatusEnum -> gameStatusEnum.getStatus().equals(status))
                .findFirst()
                .orElseThrow();
    }


}
