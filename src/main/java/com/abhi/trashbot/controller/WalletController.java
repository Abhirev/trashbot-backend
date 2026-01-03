package com.abhi.trashbot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.abhi.trashbot.model.Wallet;
import com.abhi.trashbot.model.WalletTransaction;
import com.abhi.trashbot.service.WalletService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class WalletController {

    private WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    // ---------------------------
    // GET WALLET SUMMARY
    // ---------------------------
    @GetMapping("/{userId}/wallet")
    public Map<String, Object> getWallet(@PathVariable Long userId) {
        Wallet wallet = walletService.getOrCreateWallet(userId);
        Map<String, Object> resp = new HashMap<>();
        resp.put("balance", wallet.getBalance());
        return resp;
    }

    // ---------------------------
    // GET WALLET TRANSACTIONS
    // ---------------------------
    @GetMapping("/{userId}/transactions")
    public List<WalletTransaction> getTransactions(@PathVariable Long userId) {
        return walletService.getTransactions(userId);
    }
}
