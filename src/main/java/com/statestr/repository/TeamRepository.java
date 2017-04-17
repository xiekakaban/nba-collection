package com.statestr.repository;

import com.statestr.entity.TeamEntity;
import com.statestr.entity.TeamInMatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;
import java.util.List;

/**
 * Created by ruantianbo on 2017/4/9.
 */
@Repository
public interface TeamRepository extends JpaRepository<TeamEntity,String>{
    @Query(value = "select t from TeamEntity t where t.detailUrl like %:likeStr%")
    TeamEntity findByDetailUrlLike(@Param("likeStr") String likeStr);

//    @Query(value = "select tm from TeamInMatchEntity where tm.")
//    List<TeamInMatchEntity> getTeamInMatch(@Param("awayTeam"));

    List<TeamInMatchEntity> getTeamInMatchIgnore();
}
