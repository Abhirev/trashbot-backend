package com.abhi.trashbot.service;

import org.springframework.stereotype.Service;

import com.abhi.trashbot.model.User;
import com.abhi.trashbot.model.UserReward;
import com.abhi.trashbot.repository.UserRepository;
import com.abhi.trashbot.repository.UserRewardRepository;

@Service
public class RewardService {

    private UserRepository userRepository;
    private UserRewardRepository rewardRepository;
    private CouponService couponService;

    public RewardService(UserRepository userRepository,
                         UserRewardRepository rewardRepository,
                         CouponService couponService) {
        this.userRepository = userRepository;
        this.rewardRepository = rewardRepository;
        this.couponService = couponService;
    }

    // Core method: called when bin is emptied
    public void onBinEmptied(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserReward reward = rewardRepository.findByUser(user)
                .orElseGet(() -> rewardRepository.save(new UserReward(user)));

        int previousPoints = reward.getPoints();
        int newPoints = previousPoints + 5;

        reward.setPoints(newPoints);
        reward.setLevel(resolveLevel(newPoints));
        rewardRepository.save(reward);

        // Auto reward checkpoints
        if (newPoints == 25) couponService.createCoupon(userId, 10);
        else if (newPoints == 50) couponService.createCoupon(userId, 25);
        else if (newPoints == 100) couponService.createCoupon(userId, 50);
        else if (newPoints == 200) couponService.createCoupon(userId, 100);
    }

    private String resolveLevel(int points) {
        if (points >= 2000) return "Eco Titan";
        if (points >= 500) return "Eco Prime";
        if (points >= 100) return "Eco Digital";
        return "Eco Ninja";
    }
}
