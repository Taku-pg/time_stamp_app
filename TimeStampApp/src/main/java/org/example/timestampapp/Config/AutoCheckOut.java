package org.example.timestampapp.Config;

import org.example.timestampapp.Model.Entity.Status;
import org.example.timestampapp.Service.StatusService;
import org.example.timestampapp.Service.WorkingHourService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AutoCheckOut {
    private final WorkingHourService workingHourService;
    private final StatusService statusService;

    public AutoCheckOut(WorkingHourService workingHourService, StatusService statusService) {
        this.workingHourService = workingHourService;
        this.statusService = statusService;
    }

    @Scheduled(cron = "0 0 5 * * *", zone = "CET")
    public void autoCheckOut() {
        Status leaveStatus=statusService.getStatus("Leave");
        workingHourService.autoCheckOut(leaveStatus);
    }

}
