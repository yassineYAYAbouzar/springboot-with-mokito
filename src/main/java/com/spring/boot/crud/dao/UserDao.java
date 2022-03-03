package com.spring.boot.crud.dao;

import com.spring.boot.crud.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {
    List<User> selectAllUsers();
    Optional<User> selectUsers(UUID userUID);
    int updateUSer(User user);
    int deleteUSer(UUID userUID);
    int insertUSer(UUID userUID, User user );
}
