package com.abhi.trashbot.service;

import org.springframework.stereotype.Service;

import com.abhi.trashbot.model.BinStatus;
import com.abhi.trashbot.repository.BinStatusRepository;

@Service
public class BinEventService {

    private final BinStatusRepository binStatusRepository;
    private final BinStatusService binStatusService;

    public BinEventService(BinStatusRepository binStatusRepository, BinStatusService binStatusService) {
        this.binStatusRepository = binStatusRepository;
        this.binStatusService = binStatusService;
    }

    public String handleBinUpdate(Long binId, double newFill) {
        BinStatus status = binStatusRepository.findBySmartBin_Id(binId)
                .orElseThrow(() -> new RuntimeException("Bin status not found"));

        // Even if we only have 'newFill', we call the central logic
        // This keeps the reward check consistent
        return binStatusService.processStatusUpdate(
            status, 
            status.getMedicalFill(), 
            status.getEwasteFill(), 
            (int) newFill
        );
    }
}