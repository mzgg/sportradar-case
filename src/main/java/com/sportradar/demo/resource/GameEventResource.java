package com.sportradar.demo.resource;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameEventResource {

    private String homeTeam;

    private String awayTeam;

    private String matchDate;

    private String matchStatus;

    private Integer homeTeamScore;

    private Integer awayTeamScore;


    public String getMatchName() {
        return homeTeam + " - " + awayTeam;
    }
}
