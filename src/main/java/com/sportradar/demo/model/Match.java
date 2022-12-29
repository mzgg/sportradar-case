package com.sportradar.demo.model;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Match {


    private String homeTeamName;

    private String awayTeamName;

    private LocalDateTime matchStartDate;

    private String matchStatus;

    private Integer homeTeamScore;

    private Integer awayTeamScore;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;


    public String getMatchName() {
        return homeTeamName + " - " + awayTeamName;
    }
}
