package com.abhi.trashbot.service;

import org.springframework.stereotype.Service;

import com.abhi.trashbot.dto.EcoProfileResponse;

@Service
public class EcoProfileService {

	
    private WalletService walletService;

    public EcoProfileService(WalletService walletService) {
        this.walletService = walletService;
    }

    public EcoProfileResponse getEcoProfile(Long userId) {

        double score = walletService.calculateEcoScore(userId);
        String level = walletService.calculateEcoLevel(score);

        return new EcoProfileResponse(score, level);
    }

    
}
