package org.example.service.ordinaryUser.impl;

import org.example.mapper.ordinaryUser.EvaluationMapper;
import org.example.pojo.Evaluation;
import org.example.service.ordinaryUser.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class EvaluationServiceImpl implements EvaluationService {
    @Autowired
    private EvaluationMapper evaluationMapper;

    @Override
    @Transactional
    public void submitEvaluation(Evaluation evaluation) {
        // 插入评价
        evaluationMapper.insertEvaluation(evaluation);
        // 更新代取用户评分
        evaluationMapper.updatePickupUserScore(evaluation.getPackage_id());
    }

    @Override
    public List<Evaluation> getEvaluationsByUserId(int userId) {
        return evaluationMapper.getEvaluationsByUserId(userId);
    }

    @Override
    public void updatePickupUserScore(int packageId) {
        evaluationMapper.updatePickupUserScore(packageId);
    }
}