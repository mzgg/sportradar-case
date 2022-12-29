package com.sportradar.demo.service;

import com.sportradar.demo.resource.ScoreBoardResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SummaryScoreBoardService {


    public List<ScoreBoardResource> retrieveSummaryBoardByOrderedTotalScore() {
        return List.of(ScoreBoardResource.builder().build());
    }
}
