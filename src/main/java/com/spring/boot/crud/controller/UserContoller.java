package com.spring.boot.crud.controller;

import com.spring.boot.crud.model.User;
import com.spring.boot.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
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
    public List<User> fetchUsers(@QueryParam("gender") String gender){

        return userService.getAllUsers(Optional.ofNullable(gender));
    }

    @GetMapping(
            path = "{UserUuid}"
    )
    public ResponseEntity<?> fetchUserByUuid(@PathVariable("UserUuid")UUID userUuid){
        return userService.getUsers(userUuid).<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("user " + userUuid + " Was Not Found ."));
    }
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Integer> insertUser(@RequestBody User user){
        int result = userService.insertUSer(user);
        return getIntegerResponseEntity(result);
    }
    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Integer> updateUser(@RequestBody User user){
        int result = userService.updateUSer(user);
        return getIntegerResponseEntity(result);
    }

    @DeleteMapping(
            path = "{userUid}"
    )
    public  ResponseEntity<Integer> deleteUser(@PathVariable("userUid") UUID userUid){
        int result = userService.removeUSer(userUid);
        return getIntegerResponseEntity(result);
    }


    private ResponseEntity<Integer> getIntegerResponseEntity(int result) {
        if(result == 1 ){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}

