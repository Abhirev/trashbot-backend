package com.abhi.trashbot.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhi.trashbot.dto.DashboardResponse;
import com.abhi.trashbot.model.BinStatus;
import com.abhi.trashbot.model.SmartBin;
import com.abhi.trashbot.model.User;
import com.abhi.trashbot.model.WasteComposition;
import com.abhi.trashbot.repository.BinStatusRepository;
import com.abhi.trashbot.repository.SmartBinRepository;
import com.abhi.trashbot.repository.UserRepository;
import com.abhi.trashbot.repository.WasteCompositionRepository;


@Service
public class DashboardService {

    private final UserRepository userRepository;
    private final WalletService walletService;
    private final SmartBinRepository smartBinRepository;
    private final BinStatusRepository binStatusRepository;
    @Autowired
    private WasteCompositionRepository compositionRepository;


    public DashboardService(UserRepository userRepository,
            SmartBinRepository smartBinRepository,
            BinStatusRepository binStatusRepository,
            WalletService walletService) {
		this.userRepository = userRepository;
		this.smartBinRepository = smartBinRepository;
		this.binStatusRepository = binStatusRepository;
		this.walletService = walletService;
    }



    public DashboardResponse getDashboardData(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔹 If SmartBin is not linked, return DEFAULT dashboard
        SmartBin bin = smartBinRepository.findByUser(user).orElse(null);

        if (bin == null) {
            return createDefaultDashboard();
        }

        BinStatus status = binStatusRepository.findBySmartBin(bin).orElse(null);

        if (status == null) {
            return createDefaultDashboard();
        }

        int ecoScore = (int) walletService.calculateEcoScore(userId);
        String ecoLevel = walletService.calculateEcoLevel(ecoScore);
        int level = walletService.calculateNumericLevel(ecoScore);

        
        

        double totalKg = (ecoScore / 10.0); // dummy logic for now

        Map<String, Integer> bins = Map.of(
                "medical", status.getMedicalFill(),
                "ewaste", status.getEwasteFill(),
                "recyclable", status.getRecyclableFill()
        );

        WasteComposition wc =
                compositionRepository.findBySmartBin(bin)
                .orElse(null);

        Map<String, Integer> composition;

        if (wc == null) {
            composition = Map.of(
                "plastic", 0,
                "metal", 0,
                "glass", 0,
                "others", 0
            );
        } else {
            composition = Map.of(
                "plastic", wc.getPlastic(),
                "metal", wc.getMetal(),
                "glass", wc.getGlass(),
                "others", wc.getOthers()
            );
        }

        return new DashboardResponse(
                ecoScore,
                ecoLevel,
                level,
                totalKg,
                bins,
                composition,
                status.getLastUpdated()
        );
    }

    
    private DashboardResponse createDefaultDashboard() {

        return new DashboardResponse(
                0,
                "Eco Beginner",
                1,
                0.0,
                Map.of(
                        "medical", 0,
                        "ewaste", 0,
                        "recyclable", 0
                ),
                Map.of(
                        "plastic", 0,
                        "metal", 0,
                        "glass", 0,
                        "others", 0
                ),
                null
        );
    }

    

}
