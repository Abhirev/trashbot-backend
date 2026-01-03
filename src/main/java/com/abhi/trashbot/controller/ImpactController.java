package com.abhi.trashbot.controller;

import org.springframework.web.bind.annotation.*;

import com.abhi.trashbot.dto.ImpactResponse;
import com.abhi.trashbot.service.ImpactService;

@RestController
@RequestMapping("/api/impact")
@CrossOrigin(origins = "*")
public class ImpactController {

    private ImpactService impactService;

    public ImpactController(ImpactService impactService) {
        this.impactService = impactService;
    }

    @GetMapping("/{userId}")
    public ImpactResponse getImpact(@PathVariable Long userId) {
        return impactService.getImpactForUser(userId);
    }
}
