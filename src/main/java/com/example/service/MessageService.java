package com.example.service;

import com.example.entity.Message;
import com.example.entity.Account;
import com.example.repository.MessageRepository;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    MessageRepository messageRepository;
    AccountRepository accountRepository;
    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message createMessage(Message message){
        if(message.getMessageText()==null 
        || message.getMessageText().isEmpty() 
        || message.getMessageText().length()>255 
        || !accountRepository.findById(message.getPostedBy()).isPresent()){
            return null;
        }else{
            return messageRepository.save(message);
        }
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message getMessageById(int messageId){
        Optional<Message> messageOptional = messageRepository.findById(messageId);
        if(messageOptional.isPresent()){
            Message message1 = messageOptional.get();
            return message1;
        }
        return null;
    }

    public Message deleteMessageById(int messageId){
        Optional<Message> messageOptional = messageRepository.findById(messageId);
        if(messageOptional.isPresent()){
            Message message1 = messageOptional.get();
            messageRepository.deleteById(messageId);
            return message1;
        }else{
            return null;
        }
    }

    public Message updateMessage(int messageId, String newMessageText){
        if(newMessageText==null 
        || newMessageText.isEmpty()
        || newMessageText.length()>255 
        || !messageRepository.findById(messageId).isPresent()){
            return null;
        }else{
            Message message = messageRepository.findById(messageId).get();
            message.setMessageText(newMessageText);
            return messageRepository.save(message);
        }
    }

    public List<Message> getAllMessagesByUser(int postedBy){
        return messageRepository.findAllByPostedBy(postedBy).get();
    }

}
