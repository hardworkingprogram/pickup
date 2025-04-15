package org.example.service.admin;


import org.example.pojo.Evaluation;

import java.util.List;

public interface FeedbackService {
    // 获取所有投诉
    List<Evaluation> getComplaints();

    // 处理投诉
    boolean handleComplaint(Integer evaluationId);
}