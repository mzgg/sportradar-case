package com.sportradar.demo.service;

import com.sportradar.demo.resource.ScoreBoardResource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrentMatchDataService {
    public List<ScoreBoardResource> retrieveCurrentMatches() {
        return List.of(ScoreBoardResource.builder().build());
    }
}
