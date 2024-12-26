package com.example.expensetrackerapp.repositories;

import com.example.expensetrackerapp.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income,Long> {
    List<Income> findByWorkspaceId(Long workspaceId);
}
