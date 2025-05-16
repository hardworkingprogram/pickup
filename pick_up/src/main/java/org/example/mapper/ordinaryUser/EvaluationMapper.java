package org.example.mapper.ordinaryUser;

import org.example.pojo.Evaluation;
import java.util.List;

public interface EvaluationMapper {
    void insertEvaluation(Evaluation evaluation);

    List<Evaluation> getEvaluationsByUserId(int userId);

    void updatePickupUserScore(int packageId);
}