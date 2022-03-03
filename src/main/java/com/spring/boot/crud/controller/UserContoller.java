package com.spring.boot.crud.controller;

import com.spring.boot.crud.model.User;
import com.spring.boot.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(
        path = "/api/v1/users"
)
public class UserContoller {
    private UserService userService;

    @Autowired
    public UserContoller(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> fetchUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(
            path = "{UserUuid}"
    )
    public ResponseEntity<?> fetchUserByUuid(@PathVariable("UserUuid")UUID userUuid){
        return userService.getUsers(userUuid).<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("user " + userUuid + "Was Not Found ."));
    }
    @PostMapping
    public ResponseEntity<Integer> insertUser(User user){
        int result = userService.insertUSer(user);
        if(result == 1 ){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}

