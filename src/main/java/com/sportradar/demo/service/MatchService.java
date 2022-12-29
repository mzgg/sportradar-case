package com.sportradar.demo.service;

import com.sportradar.demo.model.Match;
import com.sportradar.demo.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;

    public Match save(Match match) {
        return matchRepository.save(match);
    }
}
