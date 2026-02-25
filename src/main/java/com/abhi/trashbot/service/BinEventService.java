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

    public String handleHardwareUpdate(Long binId, double mFill, double eFill, double rFill) {
    	BinStatus status = binStatusRepository.findBySmartBin_Id(binId)
                .orElseThrow(() -> new RuntimeException("Bin status not found"));

        // We pass all three fresh values to the centralized status service
        return binStatusService.processStatusUpdate(
            status, 
            (int) mFill, 
            (int) eFill, 
            (int) rFill
        );
    }
}