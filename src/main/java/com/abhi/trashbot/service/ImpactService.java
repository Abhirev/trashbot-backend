package com.abhi.trashbot.service;

import org.springframework.stereotype.Service;

import com.abhi.trashbot.dto.ImpactResponse;
import com.abhi.trashbot.model.BinStatus;
import com.abhi.trashbot.model.SmartBin;
import com.abhi.trashbot.model.User;
import com.abhi.trashbot.repository.BinStatusRepository;
import com.abhi.trashbot.repository.SmartBinRepository;
import com.abhi.trashbot.repository.UserRepository;

@Service
public class ImpactService {

    private UserRepository userRepository;
    private SmartBinRepository smartBinRepository;
    private BinStatusRepository binStatusRepository;

    // Assumption: full bin ≈ 10 kg (document this in report)
    private static final double BIN_CAPACITY_KG = 10.0;
    private static final double CO2_FACTOR = 1.3;

    public ImpactService(UserRepository userRepository,
                         SmartBinRepository smartBinRepository,
                         BinStatusRepository binStatusRepository) {
        this.userRepository = userRepository;
        this.smartBinRepository = smartBinRepository;
        this.binStatusRepository = binStatusRepository;
    }

    public ImpactResponse getImpactForUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        SmartBin bin = smartBinRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("SmartBin not linked"));

        BinStatus status = binStatusRepository.findBySmartBin(bin)
                .orElseThrow(() -> new RuntimeException("Bin status not found"));

        // Convert % → kg
        double medicalKg =
                (status.getMedicalFill() / 100.0) * BIN_CAPACITY_KG;
        double ewasteKg =
                (status.getEwasteFill() / 100.0) * BIN_CAPACITY_KG;
        double recyclableKg =
                (status.getRecyclableFill() / 100.0) * BIN_CAPACITY_KG;

        double totalKg = medicalKg + ewasteKg + recyclableKg;
        double co2Reduction = totalKg * CO2_FACTOR;

        return new ImpactResponse(
                round(totalKg),
                round(medicalKg),
                round(ewasteKg),
                round(co2Reduction)
        );
    }

    private double round(double val) {
        return Math.round(val * 10.0) / 10.0;
    }
}
