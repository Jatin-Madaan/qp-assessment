package com.questionpro.grocery.repository;

import com.questionpro.grocery.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.questionpro.grocery.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    @Query(value = "SELECT usr.user_type FROM user usr WHERE usr.user_id = :id", nativeQuery = true)
    Type findUserTypeById(@Param("id") Long id);
}
