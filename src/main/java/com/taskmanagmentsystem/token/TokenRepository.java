package com.taskmanagmentsystem.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<Token, UUID> {

    List<Token> findAllValidTokenByEmployee(UUID id);

    Optional<Token> findByToken(String token);
}
