package com.abhi.trashbot.controller;

import org.springframework.web.bind.annotation.*;

import com.abhi.trashbot.service.BinEventService;

@RestController
@RequestMapping("/api/bin-event")
@CrossOrigin(origins = "*")
public class BinEventController {

    private BinEventService binEventService;

    public BinEventController(BinEventService binEventService) {
        this.binEventService = binEventService;
    }

    // 🔧 TEMP TEST ENDPOINT
    @PostMapping("/simulate-empty")
    public String simulateBinEmpty(
            @RequestParam Long binId,
            @RequestParam double previousFill,
            @RequestParam double newFill) {

        return binEventService.handleBinUpdate(
                binId, previousFill, newFill);
    }
}
