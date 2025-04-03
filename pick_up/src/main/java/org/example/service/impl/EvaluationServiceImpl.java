package org.example.service.impl;

import org.example.mapper.EvaluationMapper;
import org.example.pojo.Evaluation;
import org.example.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EvaluationServiceImpl implements EvaluationService {
    @Autowired
    private EvaluationMapper evaluationMapper;

    @Override
    public void submitEvaluation(Evaluation evaluation) {
        evaluationMapper.insertEvaluation(evaluation);
    }

    @Override
    public List<Evaluation> getEvaluationsByUserId(int userId) {
        return evaluationMapper.getEvaluationsByUserId(userId);
    }
}