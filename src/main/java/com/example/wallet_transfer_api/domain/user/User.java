package com.example.wallet_transfer_api.domain.user;

import com.example.wallet_transfer_api.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fristName;
    private String lastName;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;
    private String passwrd;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private Usertype userType;

    public User(UserDTO data ){
        this.fristName = data.fristName();
        this.lastName = data.lastName();
        this.document = data.document();
        this.userType = data.usertype();
        this.email = data.email();
        this.passwrd = data.password();
    }


}
