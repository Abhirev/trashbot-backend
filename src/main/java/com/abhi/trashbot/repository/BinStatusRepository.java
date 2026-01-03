package com.abhi.trashbot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhi.trashbot.model.BinStatus;
import com.abhi.trashbot.model.SmartBin;

public interface BinStatusRepository extends JpaRepository<BinStatus, Long> {

    Optional<BinStatus> findBySmartBin(SmartBin smartBin);
    
    Optional<BinStatus> findBySmartBin_Id(Long binId);

}
