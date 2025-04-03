package org.example.mapper;

import org.example.pojo.Evaluation;
import java.util.List;

public interface EvaluationMapper {
    void insertEvaluation(Evaluation evaluation);
    List<Evaluation> getEvaluationsByUserId(int userId);
}