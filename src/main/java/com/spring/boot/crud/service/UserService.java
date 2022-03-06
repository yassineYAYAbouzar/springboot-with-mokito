package com.spring.boot.crud.service;

import com.spring.boot.crud.dao.FakeDataDao;
import com.spring.boot.crud.dao.UserDao;
import com.spring.boot.crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserDao userDao;
    @Autowired
    public UserService(FakeDataDao userDao){
        this.userDao =userDao;
    }

    public List<User> getAllUsers(Optional<String> gender) {
        List<User> users = userDao.selectAllUsers();
        if(!gender.isPresent()){
            return users;
        }
        try {
            User.Gender theGender = User.Gender.valueOf(gender.get());
           return users.stream()
                    .filter(user -> user.getGender().equals(theGender))
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new IllegalStateException("invalid gender" , e);
        }
    }

    public Optional<User> getUsers(UUID userUID) {
        return  userDao.selectUsers(userUID);
    }

    public int updateUSer(User user) {
        Optional<User> optionalUser = getUsers(user.getUserUID());
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
        UUID userUID = UUID.randomUUID();
        user.setUserUID(userUID);
        return userDao.insertUSer(userUID, user);
    }
}
