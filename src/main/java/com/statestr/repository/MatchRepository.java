package com.statestr.repository;

import com.statestr.entity.MatchEntity;
import com.statestr.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ruantianbo on 2017/4/10.
 */
@Repository
public interface MatchRepository extends JpaRepository<MatchEntity,String> {

    @Query(value = "select match from MatchEntity match where match.homeTeam=:homeTeam and match.awayTeam=:awayTeam")
    List<MatchEntity> getTeamsMatch(@Param("homeTeam") TeamEntity homeTeam, @Param("awayTeam") TeamEntity awayTeam);

    @Query(value = "select match from MatchEntity match where (match.homeTeam=:firTeam and match.awayTeam=:secTeam) or (match.homeTeam=:secTeam and match.awayTeam=:firTeam)")
    List<MatchEntity> getTeamsMatchIgnore(@Param("firTeam") TeamEntity firTeam, @Param("secTeam") TeamEntity secTeam);

    @Query(value = "select COUNT(match) from MatchEntity match where match.homeTeam.shortNameCh=:homeTeamShortNameCh and match.awayTeam.shortNameCh=:awayTeamShortNameCh")
    Integer getTeamMatchCountByShortNameCh(@Param("homeTeamShortNameCh") String homeTeamShortNameCh,@Param("awayTeamShortNameCh") String awayTeamShortNameCh);

    @Query(value = "select match from MatchEntity match where match.homeTeam.shortNameCh=:homeTeamShortNameCh and match.awayTeam.shortNameCh=:awayTeamShortNameCh")
    List<MatchEntity> getTeamMatchByShortNameCh(@Param("homeTeamShortNameCh") String homeTeamShortNameCh,@Param("awayTeamShortNameCh") String awayTeamShortNameCh);
}
