package com.sportradar.demo.repository;

import com.sportradar.demo.model.Match;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {

    Optional<Match> findByHomeTeamNameAndAwayTeamNameAndStartDate(String homeTeamName,
                                                                          String awayTeamName,
                                                                          LocalDateTime startDate);

    List<Match> findByMatchStatusIn(List<String> statusList);
}
