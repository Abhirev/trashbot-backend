package com.abhi.trashbot.controller;

import org.springframework.web.bind.annotation.*;

import com.abhi.trashbot.dto.BinRegisterRequest;
import com.abhi.trashbot.model.SmartBin;
import com.abhi.trashbot.service.SmartBinService;

@RestController
@RequestMapping("/api/bins")
@CrossOrigin(origins = "*")
public class SmartBinController {

    private SmartBinService smartBinService;

    public SmartBinController(SmartBinService smartBinService) {
        this.smartBinService = smartBinService;
    }

    // 🔹 REGISTER BIN (called once by Pi / admin)
    @PostMapping("/register")
    public SmartBin registerBin(@RequestBody BinRegisterRequest request) {
        return smartBinService.registerBin(request);
    }
}
