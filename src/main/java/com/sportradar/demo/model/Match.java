package com.sportradar.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sys_match")
@SequenceGenerator(name= "sys_match_seq",allocationSize = 1)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Match {

    @Id
    @GeneratedValue(strategy =GenerationType.SEQUENCE ,generator = "sys_match_seq")
    private Long id;

    private String homeTeamName;

    private String awayTeamName;

    private LocalDateTime startDate;

    private String matchStatus;

    private Integer homeTeamScore;

    private Integer awayTeamScore;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;


    @Transient
    public String getMatchName() {
        return homeTeamName + " - " + awayTeamName;
    }
}
