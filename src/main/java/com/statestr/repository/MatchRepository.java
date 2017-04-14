package com.statestr.repository;

import com.statestr.entity.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ruantianbo on 2017/4/10.
 */
@Repository
public interface MatchRepository extends JpaRepository<MatchEntity,String> {
}
