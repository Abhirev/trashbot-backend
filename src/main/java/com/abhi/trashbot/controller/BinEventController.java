package com.abhi.trashbot.controller;

import java.util.Map;

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

 // 🚀 PERMANENT RASPBERRY PI ENDPOINT
    @PostMapping("/update-emptied")
    public String updateBinLevel(@RequestBody Map<String, Object> payload) {

    	Long binId = Long.valueOf(payload.get("binId").toString());
        double mFill = Double.parseDouble(payload.get("medicalFill").toString());
        double eFill = Double.parseDouble(payload.get("ewasteFill").toString());
        double rFill = Double.parseDouble(payload.get("recyclableFill").toString());

        // We pass all three to the service now
        return binEventService.handleHardwareUpdate(binId, mFill, eFill, rFill);
    }
    
    
    
    ////Updates PIECHART COMPOSITION 
    @PostMapping("/update-piechart")
    public String updateComposition(
            @RequestBody Map<String, Object> payload) { // Use @RequestBody for JSON

        Long binId = Long.valueOf(payload.get("binId").toString());
        
        // Parse values as double to handle 1.2, 0.5, etc.
        double plastic = Double.parseDouble(payload.get("plastic").toString());
        double metal = Double.parseDouble(payload.get("metal").toString());
        double glass = Double.parseDouble(payload.get("glass").toString());
        double others = Double.parseDouble(payload.get("others").toString());

        SmartBin smartBin = smartBinRepository.findById(binId)
                .orElseThrow(() -> new RuntimeException("Bin not found"));

        WasteComposition wc = compositionRepository.findBySmartBin(smartBin)
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
