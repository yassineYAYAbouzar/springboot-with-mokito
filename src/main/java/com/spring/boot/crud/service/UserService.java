package com.spring.boot.crud.service;

import com.spring.boot.crud.dao.FakeDataDao;
import com.spring.boot.crud.dao.UserDao;
import com.spring.boot.crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class UserService {
    private UserDao userDao;
    @Autowired
    public UserService(FakeDataDao userDao){
        this.userDao =userDao;
    }

    public List<User> getAllUsers() {
        return userDao.selectAllUsers();
    }

    public Optional<User> getUsers(UUID userUID) {
        return  userDao.selectUsers(userUID);
    }

    public int updateUSer(User user) {
        Optional<User> optionalUser = getUsers(user.getUserUid());
        if(optionalUser.isPresent()){
            userDao.updateUSer(user);
            return 1;
        }
        return -1;
    }

    public int removeUSer(UUID userUID) {
        Optional<User> optionalUser = getUsers(userUID);
        if(optionalUser.isPresent()){
            userDao.deleteUSer(userUID);
            return 1;
        }
        return -1;
    }

    public int insertUSer(User user) {
        return userDao.insertUSer(UUID.randomUUID() , user);
    }
}
