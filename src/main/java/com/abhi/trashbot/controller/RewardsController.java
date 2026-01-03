package com.abhi.trashbot.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.abhi.trashbot.model.Coupon;
import com.abhi.trashbot.model.User;
import com.abhi.trashbot.repository.CouponRepository;
import com.abhi.trashbot.repository.UserRepository;
import com.abhi.trashbot.service.CouponService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RewardsController {

    private final CouponRepository couponRepository;
    private final UserRepository userRepository;
    private final CouponService couponService;

    public RewardsController(CouponRepository couponRepository,
                             UserRepository userRepository,
                             CouponService couponService) {
        this.couponRepository = couponRepository;
        this.userRepository = userRepository;
        this.couponService = couponService;
    }

    // -------------------------------
    // CREATE COUPON (ADMIN / SYSTEM)
    // -------------------------------
    @PostMapping("/rewards/create")
    public Coupon createCoupon(@RequestParam Long userId,
                               @RequestParam double value) {
        return couponService.createCoupon(userId, value);
    }

    // ----------------------------------------
    // ✅ GET COUPONS FOR USER (ANDROID CALLS THIS)
    // ----------------------------------------
    @GetMapping("/users/{userId}/coupons")
    public List<Coupon> getCouponsForUser(@PathVariable Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return couponRepository.findByUserOrderByCreatedAtDesc(user);
    }

    // ----------------------------------------
    // REDEEM COUPON (ANDROID CALLS THIS)
    // ----------------------------------------
    @PostMapping("/coupons/redeem")
    public Map<String, Object> redeemCoupon(@RequestBody Map<String, Object> body) {

        Long couponId = ((Number) body.get("couponId")).longValue();
        Long userId = ((Number) body.get("userId")).longValue();

        return couponService.redeemCoupon(couponId, userId);
    }
    
    
    
    
    
 // ----------------------------------------
 // DEV ONLY: Generate test coupon for user
 // ----------------------------------------
 @PostMapping("/dev/create-test-coupon")
 public Coupon createTestCoupon(@RequestParam Long userId,
                                @RequestParam double value) {

     return couponService.createCoupon(userId, value);
 }

}
