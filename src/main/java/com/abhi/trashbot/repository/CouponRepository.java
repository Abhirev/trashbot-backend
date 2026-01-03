package com.abhi.trashbot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhi.trashbot.model.Coupon;
import com.abhi.trashbot.model.User;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    List<Coupon> findByUserOrderByCreatedAtDesc(User user);
}
