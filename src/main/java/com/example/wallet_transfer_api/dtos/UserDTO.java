package com.example.wallet_transfer_api.dtos;


import com.example.wallet_transfer_api.domain.user.Usertype;

import java.math.BigDecimal;

public record UserDTO(String fristName, String lastName, String document, BigDecimal balance, String email, String password, Usertype usertype) {
}
