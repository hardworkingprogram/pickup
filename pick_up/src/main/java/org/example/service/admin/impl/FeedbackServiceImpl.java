package org.example.service.admin.impl;


import org.example.mapper.admin.FeedbackMapper;
import org.example.pojo.Evaluation;
import org.example.service.admin.FeedbackService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Resource
    private FeedbackMapper feedbackMapper;

    @Override
    public List<Evaluation> getComplaints() {
        return feedbackMapper.selectComplaints();
    }

    @Override
    @Transactional // 保证通知发送与状态更新的原子性
    public boolean handleComplaint(Integer evaluationId) {
        int rows = feedbackMapper.updateComplaintHandling(
                evaluationId
        );
        return rows > 0;
    }
}