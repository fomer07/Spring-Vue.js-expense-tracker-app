package com.example.expensetrackerapp.service;

import com.example.expensetrackerapp.entity.Expense;
import com.example.expensetrackerapp.entity.Workspace;
import com.example.expensetrackerapp.repositories.ExpenseRepository;
import com.example.expensetrackerapp.repositories.WorkspaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final WorkspaceRepository workspaceRepository;


    public ExpenseService(ExpenseRepository expenseRepository,
                          WorkspaceRepository workspaceRepository) {
        this.expenseRepository = expenseRepository;
        this.workspaceRepository = workspaceRepository;
    }

    public List<Expense> getAllExpensesByWorkspace(Long workspaceId) {
        return expenseRepository.findByWorkspaceId(workspaceId);
    }

    public Expense addExpense(Long workspaceId, Expense expense) {
        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(()-> new IllegalArgumentException("Workspace not found."));

        expense.setWorkspace(workspace);
        return expenseRepository.save(expense);
    }

    public Expense updateExpense(Long expenseId, Expense expenseDetails) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(()-> new IllegalArgumentException("Expense not found."));

        expense.setCategory(expenseDetails.getCategory());
        expense.setAmount(expenseDetails.getAmount());
        expense.setDate(expenseDetails.getDate());

        return expenseRepository.save(expense);
    }

    public void deleteExpense(Long expenseId) {
        if (!expenseRepository.existsById(expenseId)) {
            throw new IllegalArgumentException("Expense not found.");
        }
        expenseRepository.deleteById(expenseId);
    }
}
