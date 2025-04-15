package org.example.mapper.admin;


import org.apache.ibatis.annotations.Param;
import org.example.pojo.Evaluation;

import java.util.Date;
import java.util.List;

public interface FeedbackMapper {
    // 查询所有投诉（is_complaint=1）
    List<Evaluation> selectComplaints();

    // 处理投诉更新
    int updateComplaintHandling(@Param("evaluationId") Integer evaluationId);
}