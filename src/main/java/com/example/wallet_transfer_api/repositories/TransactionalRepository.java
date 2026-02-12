package com.example.wallet_transfer_api.repositories;

import com.example.wallet_transfer_api.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionalRepository extends JpaRepository<Transaction, Long> {
}
