package com.sportradar.demo.resource;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameEventResource {

    @NotNull
    private String homeTeam;

    @NotNull
    private String awayTeam;

    @NotNull
    private String matchDate;

    @NotNull
    private String matchStatus;

    private Integer homeTeamScore;

    private Integer awayTeamScore;


    public String getMatchName() {
        return homeTeam + " - " + awayTeam;
    }
}
