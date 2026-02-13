package com.example.wallet_transfer_api.services;

import com.example.wallet_transfer_api.domain.user.User;
import com.example.wallet_transfer_api.domain.user.Usetype;
import com.example.wallet_transfer_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUsertype() == Usetype.MERCHANT){
            throw new Exception("Usuario logistas nao estao autorizados a realizar transações");
        }
        if(sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Saldo insuficiente");
        }

    }

    public User findById(Long id) throws Exception {

        //ira buscar um usuario e caso nao encontre ele lança uma exceção
        return repository.findById(id).orElseThrow(() -> new Exception("Usuario nao encontrado"));
    }

    public void saveUser(User user){
        repository.save(user);
    }
}
