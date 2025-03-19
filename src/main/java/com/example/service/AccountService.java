package com.example.service;

import com.example.entity.Message;
import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account registerAccount(Account account){
        if (account.getPassword().length()<4 
        || account.getUsername().isEmpty() 
        || account.getUsername()==null
        || UsernameAlreadyExists(account.getUsername())==true){
            return null;
        }else{
            return accountRepository.save(account);
        }
    }
    
    public Account loginUser(String username, String password){
        Optional<Account> accountOptional = accountRepository.findByUsernameAndPassword(username, password);
        if (accountOptional.isPresent()){
            Account account1 = accountOptional.get();
            return account1;
        }else{
            return null;
        }
    }

    public Boolean UsernameAlreadyExists(String username){
        Boolean userExists = false;
        if(accountRepository.findByUsername(username).isPresent()){
            userExists = true;
        }
        return userExists;
    }
}
