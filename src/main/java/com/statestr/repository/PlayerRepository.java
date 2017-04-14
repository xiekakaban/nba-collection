package com.statestr.repository;

import com.statestr.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ruantianbo on 2017/4/9.
 */
@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity,String> {
    /**通过 detail 查找*/
    @Query(value = "select p from PlayerEntity p where p.detailUrl like %:likeStr%")
    PlayerEntity findByDetailUrlLike(@Param("likeStr") String likeStr);
}
