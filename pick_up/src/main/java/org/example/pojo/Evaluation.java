package org.example.pojo;

import java.util.Date;

public class Evaluation {
    private int evaluation_id;
    private int user_id;
    private int package_id;
    private int satisfaction;
    private String feedback;
    private boolean is_complaint;

    // 构造函数
    public Evaluation() {}

    public Evaluation(int user_id, int package_id, int satisfaction, String feedback, boolean is_complaint) {
        this.user_id = user_id;
        this.package_id = package_id;
        this.satisfaction = satisfaction;
        this.feedback = feedback;
        this.is_complaint = is_complaint;
    }

    // Getters 和 Setters
    public int getEvaluation_id() {
        return evaluation_id;
    }

    public void setEvaluation_id(int evaluation_id) {
        this.evaluation_id = evaluation_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPackage_id() {
        return package_id;
    }

    public void setPackage_id(int package_id) {
        this.package_id = package_id;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(int satisfaction) {
        this.satisfaction = satisfaction;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public boolean isIs_complaint() {
        return is_complaint;
    }

    public void setIs_complaint(boolean is_complaint) {
        this.is_complaint = is_complaint;
    }
}