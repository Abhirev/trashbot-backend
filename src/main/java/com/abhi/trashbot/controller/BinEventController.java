package com.abhi.trashbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.abhi.trashbot.model.SmartBin;
import com.abhi.trashbot.model.WasteComposition;
import com.abhi.trashbot.repository.SmartBinRepository;
import com.abhi.trashbot.repository.WasteCompositionRepository;
import com.abhi.trashbot.service.BinEventService;

@RestController
@RequestMapping("/api/bin-event")
@CrossOrigin(origins = "*")
public class BinEventController {

	@Autowired
    private WasteCompositionRepository compositionRepository;
	
	
	@Autowired
	private SmartBinRepository smartBinRepository;
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
                binId, newFill);
    }
    
    
    
    @PostMapping("/update-composition")
    public String updateComposition(
            @RequestParam Long binId,
            @RequestParam int plastic,
            @RequestParam int metal,
            @RequestParam int glass,
            @RequestParam int others) {

        SmartBin smartBin = smartBinRepository.findById(binId)
                .orElseThrow(() -> new RuntimeException("Bin not found"));

        WasteComposition wc =
                compositionRepository.findBySmartBin(smartBin)
                .orElse(new WasteComposition());

        wc.setSmartBin(smartBin);
        wc.setPlastic(plastic);
        wc.setMetal(metal);
        wc.setGlass(glass);
        wc.setOthers(others);

        compositionRepository.save(wc);
        return "Composition updated";
    }

}
