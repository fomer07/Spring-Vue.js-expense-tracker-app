package com.example.expensetrackerapp.repositories;

import com.example.expensetrackerapp.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByWorkspaceId(Long workspaceId);
}
