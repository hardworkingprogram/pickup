package org.example.controller.ordinaryUser;

import org.example.pojo.Evaluation;
import org.example.service.ordinaryUser.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/evaluations")
public class EvaluationController {
    @Autowired
    private EvaluationService evaluationService;

    @PostMapping("/submit")
    public String submitEvaluation(@RequestBody Evaluation evaluation) {
        try {
            evaluationService.submitEvaluation(evaluation);
            return "评价与反馈提交成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "评价与反馈提交失败";
        }
    }

    @GetMapping("/user/{userId}")
    public List<Evaluation> getEvaluationsByUserId(@PathVariable int userId) {
        return evaluationService.getEvaluationsByUserId(userId);
    }
}