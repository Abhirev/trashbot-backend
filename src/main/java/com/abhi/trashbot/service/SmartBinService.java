package com.abhi.trashbot.service;

import org.springframework.stereotype.Service;

import com.abhi.trashbot.dto.BinRegisterRequest;
import com.abhi.trashbot.dto.BinUpdateRequest;
import com.abhi.trashbot.model.BinStatus;
import com.abhi.trashbot.model.SmartBin;
import com.abhi.trashbot.model.User;
import com.abhi.trashbot.repository.BinStatusRepository;
import com.abhi.trashbot.repository.SmartBinRepository;
import com.abhi.trashbot.repository.UserRepository;

@Service
public class SmartBinService {

    private final UserRepository userRepository;
    private final SmartBinRepository smartBinRepository;
    private final BinStatusRepository binStatusRepository;
    private final BinStatusService binStatusService; // Injected new service

    public SmartBinService(UserRepository userRepository, 
                           SmartBinRepository smartBinRepository,
                           BinStatusRepository binStatusRepository, 
                           BinStatusService binStatusService) {
        this.userRepository = userRepository;
        this.smartBinRepository = smartBinRepository;
        this.binStatusRepository = binStatusRepository;
        this.binStatusService = binStatusService;
    }

    public SmartBin registerBin(BinRegisterRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (smartBinRepository.findByUser(user).isPresent()) {
            throw new RuntimeException("User already has a SmartBin");
        }

        SmartBin bin = new SmartBin();
        bin.setUser(user);
        bin.setBinCode(request.getDeviceId());
        bin.setLocation(request.getLocation());
        bin.setStatus("ACTIVE");

        SmartBin savedBin = smartBinRepository.save(bin);

        BinStatus status = new BinStatus(savedBin, 0, 0, 0);
        binStatusRepository.save(status);

        return savedBin;
    }

    public String updateBinStatus(BinUpdateRequest request) {
        SmartBin bin = smartBinRepository.findByBinCode(request.getDeviceId())
                .orElseThrow(() -> new RuntimeException("SmartBin not found"));

        BinStatus status = binStatusRepository.findBySmartBin(bin)
                .orElseThrow(() -> new RuntimeException("BinStatus not found"));

        // Use the centralized logic
        return binStatusService.processStatusUpdate(
            status, 
            request.getMedicalFill(), 
            request.getEwasteFill(), 
            request.getRecyclableFill()
        );
    }
}
