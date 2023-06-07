package com.example.jft_pr16.jmx;

import com.example.jft_pr16.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@ManagedResource
public class MyMbean {
    private final SchedulerService schedulerService;

    @Autowired
    public MyMbean(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @ManagedOperation(description = "Run scheduled task")
    public void run() throws IOException {
        schedulerService.backupDataBase();
    }
}
