package com.abhi.trashbot.controller;

import org.springframework.web.bind.annotation.*;

import com.abhi.trashbot.dto.EcoProfileResponse;
import com.abhi.trashbot.service.EcoProfileService;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = "*")
public class EcoProfileController {

    private EcoProfileService ecoProfileService;

    public EcoProfileController(EcoProfileService ecoProfileService) {
        this.ecoProfileService = ecoProfileService;
    }

    @GetMapping("/{userId}/eco")
    public EcoProfileResponse getEcoProfile(@PathVariable Long userId) {
        return ecoProfileService.getEcoProfile(userId);
    }
}
