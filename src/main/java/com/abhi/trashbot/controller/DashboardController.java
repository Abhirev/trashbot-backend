package com.abhi.trashbot.controller;

import org.springframework.web.bind.annotation.*;

import com.abhi.trashbot.dto.DashboardResponse;
import com.abhi.trashbot.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    private DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/{userId}")
    public DashboardResponse getDashboard(@PathVariable Long userId) {
        return dashboardService.getDashboardData(userId);
    }
}
