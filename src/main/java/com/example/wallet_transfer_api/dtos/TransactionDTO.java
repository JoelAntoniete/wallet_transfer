package com.example.wallet_transfer_api.dtos;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, Long senderId, Long reciverId) {
}
