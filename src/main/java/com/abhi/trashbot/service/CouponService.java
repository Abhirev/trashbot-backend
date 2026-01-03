package com.abhi.trashbot.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.abhi.trashbot.model.Coupon;
import com.abhi.trashbot.model.User;
import com.abhi.trashbot.model.Wallet;
import com.abhi.trashbot.repository.CouponRepository;
import com.abhi.trashbot.repository.UserRepository;

@Service
public class CouponService {

    private final CouponRepository couponRepository;
    private final UserRepository userRepository;
    private final WalletService walletService;

    public CouponService(CouponRepository couponRepository,
                         UserRepository userRepository,
                         WalletService walletService) {
        this.couponRepository = couponRepository;
        this.userRepository = userRepository;
        this.walletService = walletService;
    }

    // ----------------------
    // CREATE COUPON
    // ----------------------
    public Coupon createCoupon(Long userId, double value) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Coupon coupon = new Coupon(user, value);
        return couponRepository.save(coupon);
    }

    // ----------------------
    // REDEEM COUPON (FINAL)
    // ----------------------
    public Map<String, Object> redeemCoupon(Long couponId, Long userId) {

        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));

        if (!coupon.getUser().getId().equals(userId)) {
            throw new RuntimeException("Coupon does not belong to user");
        }

        if (!"ISSUED".equalsIgnoreCase(coupon.getStatus())) {
            throw new RuntimeException("Coupon already redeemed");
        }

        // 1️⃣ Mark coupon as redeemed
        coupon.setStatus("REDEEMED");
        couponRepository.save(coupon);

        // 2️⃣ Credit wallet
        Wallet wallet = walletService.getOrCreateWallet(userId);
        walletService.creditWallet(
                wallet,
                coupon.getValue(),
                "Coupon Redeem (₹" + coupon.getValue() + ")"
        );

        // 3️⃣ Prepare response for Android
        Map<String, Object> response = new HashMap<>();
        response.put("couponId", coupon.getId());
        response.put("status", "REDEEMED");
        response.put("creditedAmount", coupon.getValue());
        response.put("walletBalance", wallet.getBalance());

        return response;
    }
}
