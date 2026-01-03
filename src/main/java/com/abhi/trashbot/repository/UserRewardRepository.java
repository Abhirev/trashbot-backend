package com.abhi.trashbot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhi.trashbot.model.User;
import com.abhi.trashbot.model.UserReward;

public interface UserRewardRepository extends JpaRepository<UserReward, Long> {

    Optional<UserReward> findByUser(User user);
}
