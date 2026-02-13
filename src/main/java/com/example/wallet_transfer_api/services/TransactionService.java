package com.example.wallet_transfer_api.services;

import com.example.wallet_transfer_api.domain.transaction.Transaction;
import com.example.wallet_transfer_api.domain.user.User;
import com.example.wallet_transfer_api.dtos.TransactionDTO;
import com.example.wallet_transfer_api.repositories.TransactionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private TransactionalRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;

    public Transaction createTransaction(TransactionDTO trasaction) throws Exception {
        User sender = this.userService.findById(trasaction.senderId());
        User receiver = this.userService.findById(trasaction.reciverId());

        //valida se o cliente pode fazer a transação
        userService.validateTransaction(sender, trasaction.value());

        //consulta o serviço autorizador externo
        boolean isAuthorized = this.authorizeTransaction(sender, trasaction.value());
        if(!isAuthorized){
            throw new Exception("Transação não autorizada!");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(trasaction.value());
        newTransaction.setReciver(receiver);
        newTransaction.setSender(sender);
        newTransaction.setTimestamp(LocalDateTime.now());

        //atualizar o saldo dos usuarios
        sender.setBalance(sender.getBalance().subtract(trasaction.value()));
        receiver.setBalance(receiver.getBalance().add(trasaction.value()));

        this.repository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);
        this.notificationService.sendNotification(sender, "transação realizada com sucessso");
        this.notificationService.sendNotification(receiver, "transação recebida com sucessso");

        return newTransaction;
    }

    public boolean authorizeTransaction(User user, BigDecimal value){
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize ", Map.class);
        if(authorizationResponse.getStatusCode() == HttpStatus.OK){
            String message = (String) authorizationResponse.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        }else{
            return false;
        }
    }
}
