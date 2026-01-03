package com.abhi.trashbot.controller;

import org.springframework.web.bind.annotation.*;

import com.abhi.trashbot.service.RewardService;

@RestController
@RequestMapping("/api/reward-trigger")
@CrossOrigin(origins = "*")
public class RewardTriggerController {

    private RewardService rewardService;

    public RewardTriggerController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    // Called when bin empty detected
    @PostMapping("/bin-emptied/{userId}")
    public String binEmptied(@PathVariable Long userId) {
        rewardService.onBinEmptied(userId);
        return "Reward processed successfully";
    }
}
