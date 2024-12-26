package com.example.expensetrackerapp.service;

import com.example.expensetrackerapp.entity.Income;
import com.example.expensetrackerapp.entity.Workspace;
import com.example.expensetrackerapp.repositories.IncomeRepository;
import com.example.expensetrackerapp.repositories.WorkspaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeService {

    private final IncomeRepository incomeRepository;
    private final WorkspaceRepository workspaceRepository;

    public IncomeService(IncomeRepository incomeRepository,
                         WorkspaceRepository workspaceRepository) {
        this.incomeRepository = incomeRepository;
        this.workspaceRepository = workspaceRepository;
    }

    public List<Income> getAllIncomesByWorkspace(Long workspaceId) {
        return incomeRepository.findByWorkspaceId(workspaceId);
    }

    public Income addIncome(Long workspaceId, Income income) {
        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(()-> new IllegalArgumentException("Workspace not found."));

        income.setWorkspace(workspace);
        return incomeRepository.save(income);
    }

    public Income updateIncome(Long incomeId, Income incomeDetails) {
        Income income = incomeRepository.findById(incomeId)
                .orElseThrow(()-> new IllegalArgumentException("Income not found."));

        income.setSource(incomeDetails.getSource());
        income.setAmount(incomeDetails.getAmount());
        income.setDate(incomeDetails.getDate());

        return incomeRepository.save(income);
    }

    public void deleteIncome(Long incomeId) {
        if (!incomeRepository.existsById(incomeId)) {
            throw new IllegalArgumentException("Income not found.");
        }
        incomeRepository.deleteById(incomeId);
    }
}
