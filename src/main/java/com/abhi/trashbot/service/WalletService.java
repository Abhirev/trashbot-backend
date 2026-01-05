package com.abhi.trashbot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.abhi.trashbot.model.User;
import com.abhi.trashbot.model.Wallet;
import com.abhi.trashbot.model.WalletTransaction;
import com.abhi.trashbot.repository.UserRepository;
import com.abhi.trashbot.repository.WalletRepository;
import com.abhi.trashbot.repository.WalletTransactionRepository;

@Service
public class WalletService {

    private UserRepository userRepository;
    private WalletRepository walletRepository;
    private WalletTransactionRepository transactionRepository;

    public WalletService(UserRepository userRepository,
                         WalletRepository walletRepository,
                         WalletTransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    // Create wallet if not exists
    public Wallet getOrCreateWallet(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return walletRepository.findByUser(user)
                .orElseGet(() -> walletRepository.save(new Wallet(user, 0.0)));
    }

    // Credit wallet
    public void creditWallet(Wallet wallet, double amount, String description) {
        wallet.setBalance(wallet.getBalance() + amount);
        walletRepository.save(wallet);

        WalletTransaction tx = new WalletTransaction(
                wallet, "CREDIT", amount, description);
        transactionRepository.save(tx);
    }

    // Get transactions
    public List<WalletTransaction> getTransactions(Long userId) {
        Wallet wallet = getOrCreateWallet(userId);
        return transactionRepository.findByWalletOrderByTimestampDesc(wallet);
    }
    
    
    
    public double calculateEcoScore(Long userId) {

        List<WalletTransaction> txs = transactionRepository.findByWallet_User_Id(userId);

        double totalCredits = txs.stream()
                .filter(tx -> "CREDIT".equalsIgnoreCase(tx.getType()))
                .mapToDouble(WalletTransaction::getAmount)
                .sum();

        return (totalCredits + totalCredits * 0.5); 
    }
    
    public String calculateEcoLevel(double ecoScore) {
    	if (ecoScore < 10) return "Eco Beginner";
    	if (ecoScore < 100) return "Eco Ninja";
        if (ecoScore < 500) return "Eco Warrior";
        if (ecoScore < 2000) return "Eco Prime";
        return "Eco Legend";
    }

    public int calculateNumericLevel(double score) {
        if (score < 10) return 1;
        if (score < 100) return 2;
        if (score < 500) return 3;
        if (score < 2000) return 4;
        return 5;
    }


}
