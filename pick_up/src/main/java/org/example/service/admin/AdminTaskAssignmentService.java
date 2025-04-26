package org.example.service.admin;

import org.example.pojo.PickupApplication;

import java.util.List;

public interface AdminTaskAssignmentService {
    boolean assignTask(int applicationId, int courierId);
    List<PickupApplication> getUnassignedTasks();
}
