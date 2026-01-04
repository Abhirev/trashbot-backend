package com.abhi.trashbot.service;

import org.springframework.stereotype.Service;

import com.abhi.trashbot.dto.BinRegisterRequest;
import com.abhi.trashbot.model.BinStatus;
import com.abhi.trashbot.model.SmartBin;
import com.abhi.trashbot.model.User;
import com.abhi.trashbot.repository.BinStatusRepository;
import com.abhi.trashbot.repository.SmartBinRepository;
import com.abhi.trashbot.repository.UserRepository;

@Service
public class SmartBinService {

    private UserRepository userRepository;
    private SmartBinRepository smartBinRepository;
    private BinStatusRepository binStatusRepository;

    public SmartBinService(UserRepository userRepository,
                           SmartBinRepository smartBinRepository,
                           BinStatusRepository binStatusRepository) {
        this.userRepository = userRepository;
        this.smartBinRepository = smartBinRepository;
        this.binStatusRepository = binStatusRepository;
    }

    // 🔹 Register bin to user (ONE TIME)
    public SmartBin registerBin(BinRegisterRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ❌ Prevent multiple bins per user
        if (smartBinRepository.findByUser(user).isPresent()) {
            throw new RuntimeException("User already has a SmartBin");
        }

        // ❌ Prevent duplicate hardware ID
        if (smartBinRepository.findByBinCode(request.getDeviceId()).isPresent()) {
            throw new RuntimeException("Device already registered");
        }

        SmartBin bin = new SmartBin();
        bin.setUser(user);
        bin.setBinCode(request.getDeviceId());   // ✅ FIXED
        bin.setLocation(request.getLocation());
        bin.setStatus("ACTIVE");

        SmartBin savedBin = smartBinRepository.save(bin);

        // 🔹 Create initial BinStatus (0%)
        BinStatus status = new BinStatus(
                savedBin,
                0, // medical
                0, // ewaste
                0  // recyclable
        );
        binStatusRepository.save(status);

        return savedBin;
    }

}
