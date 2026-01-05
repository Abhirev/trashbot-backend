package com.abhi.trashbot.service;

import org.springframework.stereotype.Service;

import com.abhi.trashbot.model.BinStatus;
import com.abhi.trashbot.repository.BinStatusRepository;

import jakarta.transaction.Transactional;

@Service
public class BinStatusService {

    private final BinStatusRepository binStatusRepository;
    private final CouponService couponService;

    public BinStatusService(BinStatusRepository binStatusRepository, CouponService couponService) {
        this.binStatusRepository = binStatusRepository;
        this.couponService = couponService;
    }

    @Transactional
    public String processStatusUpdate(BinStatus status, int mFill, int eFill, int rFill) {
        // 1. Calculate previous total
        int prevTotal = status.getMedicalFill() + status.getEwasteFill() + status.getRecyclableFill();

        // 2. Update status
        status.setMedicalFill(mFill);
        status.setEwasteFill(eFill);
        status.setRecyclableFill(rFill);
        status.setLastUpdated(java.time.LocalDateTime.now());
        binStatusRepository.save(status);

        // 3. Check Reward Logic (One place to maintain!)
        int newTotal = mFill + eFill + rFill;
        if (prevTotal >= 90 && newTotal <= 10) {
            Long userId = status.getSmartBin().getUser().getId();
            couponService.createCoupon(userId, 10.0);
            return "Bin emptied → Coupon rewarded";
        }

        return "Bin updated successfully";
    }
}