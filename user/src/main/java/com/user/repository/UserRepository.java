package com.user.repository;

import com.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmailOrCpf(String email, String cpf);

  Page<User> findByIdOrCpfOrEmail(Long id, String cpf, String email, Pageable pageable);

}

