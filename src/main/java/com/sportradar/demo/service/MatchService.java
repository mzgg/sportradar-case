package com.sportradar.demo.service;

import com.sportradar.demo.model.Match;
import com.sportradar.demo.repository.MatchRepository;
import com.sportradar.demo.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;

    public Match save(Match match) {
        return matchRepository.save(match);
    }

    public Optional<Match> findByHomeTeamNameAndAwayTeamNameAndStartDate(String homeTeamName,
                                                                         String awayTeamName,
                                                                         String startDate) {
        LocalDateTime parse = DateUtil.toLocalDateFromString(startDate);

        return matchRepository
                .findByHomeTeamNameAndAwayTeamNameAndStartDate(homeTeamName, awayTeamName, parse);
    }

    public List<Match> findByMatchStatusIn(List<String> statusList) {
       return matchRepository.findByMatchStatusIn(statusList);
    }
}
