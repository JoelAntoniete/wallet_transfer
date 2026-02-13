package com.example.wallet_transfer_api.services;

import com.example.wallet_transfer_api.domain.user.User;
import com.example.wallet_transfer_api.domain.user.Usertype;
import com.example.wallet_transfer_api.domain.user.Usertype;
import com.example.wallet_transfer_api.dtos.UserDTO;
import com.example.wallet_transfer_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() == Usertype.MERCHANT){
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

    public User createUser(UserDTO data){
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers(){
        return this.repository.findAll();
    }

    public void saveUser(User user){
        repository.save(user);
    }
}
