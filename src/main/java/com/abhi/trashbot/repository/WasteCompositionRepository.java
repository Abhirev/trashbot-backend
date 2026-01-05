package com.abhi.trashbot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhi.trashbot.model.SmartBin;
import com.abhi.trashbot.model.WasteComposition;

public interface WasteCompositionRepository
extends JpaRepository<WasteComposition, Long> {

Optional<WasteComposition> findBySmartBin(SmartBin smartBin);
}
