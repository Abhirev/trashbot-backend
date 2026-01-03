package com.abhi.trashbot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhi.trashbot.model.SmartBin;
import com.abhi.trashbot.model.User;

public interface SmartBinRepository extends JpaRepository<SmartBin, Long> {

    Optional<SmartBin> findByUser(User user);

    Optional<SmartBin> findByBinCode(String binCode);
}
