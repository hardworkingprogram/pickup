package org.example.service.ordinaryUser;

import org.example.pojo.Evaluation;
import java.util.List;

public interface EvaluationService {
    void submitEvaluation(Evaluation evaluation);
    List<Evaluation> getEvaluationsByUserId(int userId);
}