package com.sportradar.demo.service;

import com.sportradar.demo.enums.GameStatusEnum;
import com.sportradar.demo.model.Match;
import com.sportradar.demo.resource.GameEventResource;
import com.sportradar.demo.util.DateUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
@Validated
public class GameEventService {

    private final MatchService matchService;

    private final ApplicationContext context;

    public Match createGameEvent(@Valid GameEventResource gameEvent) {
        return getMatchDataIfExistInDatabase(gameEvent)
                .map(match1 -> updateMatchDataByNewEvent(gameEvent, match1))
                .orElseGet(() -> createNewMatchObject(gameEvent));
    }

    private Match updateMatchDataByNewEvent(GameEventResource gameEvent, Match match) {
        match.setMatchStatus(gameEvent.getMatchStatus());
        match.setHomeTeamScore(gameEvent.getHomeTeamScore());
        match.setAwayTeamScore(gameEvent.getAwayTeamScore());
        match.setUpdatedDate(LocalDateTime.now());
        match = matchService.save(match);
        executeActionByEventStatus(gameEvent);
        log.info("match updated, matchName:{}, status:{}", match.getMatchName(), match.getMatchStatus());
        return match;
    }


    private Match createNewMatchObject(GameEventResource gameEvent) {
        Match match = newMatchObjectByIncomingGameEvent(gameEvent);
        match = matchService.save(match);
        executeActionByEventStatus(gameEvent);
        log.info("new match created, matchName:{}, status:{}", match.getMatchName(), match.getMatchStatus());
        return match;
    }

    private Match newMatchObjectByIncomingGameEvent(GameEventResource gameEvent) {
        return Match.builder()
                .homeTeamName(gameEvent.getHomeTeam())
                .awayTeamName(gameEvent.getAwayTeam())
                .awayTeamScore(gameEvent.getAwayTeamScore())
                .homeTeamScore(gameEvent.getHomeTeamScore())
                .matchStatus(gameEvent.getMatchStatus())
                .startDate(DateUtil.toLocalDateFromString(gameEvent.getMatchDate()))
                .createdDate(LocalDateTime.now())
                .build();
    }

    private void executeActionByEventStatus(GameEventResource gameEvent) {
        GameStatusEnum gameStatusEnum = GameStatusEnum.getStatusEnumByStringValue(gameEvent.getMatchStatus());
        context.getBean(gameStatusEnum.getAClass()).changeDataAction(gameEvent);
    }

    private Optional<Match> getMatchDataIfExistInDatabase(GameEventResource gameEvent) {
        return matchService.findByHomeTeamNameAndAwayTeamNameAndStartDate(gameEvent.getHomeTeam(),
                gameEvent.getAwayTeam(), gameEvent.getMatchDate());
    }
}
