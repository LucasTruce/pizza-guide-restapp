package com.pizzaguideapp.models.chat;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    Page<Chat> findAllBySenderUsernameOrReceiverUsername(String username, String receiverUsername, Pageable pageable);
}
