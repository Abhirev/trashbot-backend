package com.abhi.trashbot.service;

import org.springframework.stereotype.Service;

import com.abhi.trashbot.model.BinStatus;
import com.abhi.trashbot.repository.BinStatusRepository;

@Service
public class BinEventService {

    private BinStatusRepository binStatusRepository;
    private CouponService couponService;

    public BinEventService(BinStatusRepository binStatusRepository,
                           CouponService couponService) {
        this.binStatusRepository = binStatusRepository;
        this.couponService = couponService;
    }

    public String handleBinUpdate(Long binId,
            double prevFill,
            double newFill) {

		BinStatus status = binStatusRepository.findBySmartBin_Id(binId)
		.orElseThrow(() -> new RuntimeException("Bin status not found"));
		
		// Update recyclable bin fill %
		status.setRecyclableFill((int) newFill);
		status.setLastUpdated(java.time.LocalDateTime.now());
		binStatusRepository.save(status);
		
		// 🔥 REWARD LOGIC
		if (prevFill >= 90 && newFill <= 10) {
		
			Long userId = status.getSmartBin().getUser().getId();
			
			// Create coupon reward
			couponService.createCoupon(userId, 5.0);
			
			return "Bin emptied → Coupon rewarded";
		}
		
		return "Bin updated (no reward)";
}

}
