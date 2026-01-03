package com.abhi.trashbot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhi.trashbot.model.WalletTransaction;

public interface WalletTransactionRepository
        extends JpaRepository<WalletTransaction, Long> {

    // For wallet screen (existing)
    List<WalletTransaction> findByWalletOrderByTimestampDesc(
            com.abhi.trashbot.model.Wallet wallet);

    // ✅ ADD THIS (for Eco Score calculation)
    List<WalletTransaction> findByWallet_User_Id(Long userId);
}
