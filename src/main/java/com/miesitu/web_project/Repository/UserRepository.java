package com.miesitu.web_project.Repository;

import java.util.Optional;

import com.miesitu.web_project.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String email);   
    User findByPhone(int phone);   
    User findByUsername(String username);
    Iterable<User> findByArea(String area);
    Optional<User> findByFirstName(String firstname);
    

    public Long countByUserId(long userId);
}
