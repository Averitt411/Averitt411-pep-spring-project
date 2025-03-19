package com.example.repository;

import com.example.entity.Message;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer>{
    
    Optional<List<Message>> findAllByPostedBy(Integer postedBy);

    Integer countByMessageId(Integer messageId);
}
