package com.sample.numbergenerator.repository;

import com.sample.numbergenerator.entity.GoalEntity;
import com.sample.numbergenerator.entity.TaskEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GoalRepository extends JpaRepository<GoalEntity, UUID> {
    List<GoalEntity> findAllByTaskId(UUID uuid);
}
