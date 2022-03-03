package com.spring.boot.crud.dao;

import com.spring.boot.crud.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class FakeDataDao implements UserDao{

    private  Map<UUID, User> database;

    public FakeDataDao() {
        database = new HashMap<>();
        UUID yassineUserUid = UUID.randomUUID();
        database.put(yassineUserUid, new User(yassineUserUid,
                "yassine", "bouzar", User.Gender.MAMLE,
                24, "yassine@gmail.com"));
    }

    @Override
    public List<User> selectAllUsers() {
        return new ArrayList<>(database.values());
    }

    @Override
    public Optional<User> selectUsers(UUID userUID) {
        return Optional.ofNullable(database.get(userUID));
    }

    @Override
    public int updateUSer(User user) {
        database.put(user.getUserUid() , user);
        return 1;
    }

    @Override
    public int deleteUSer(UUID userUID) {
        database.remove(userUID);
        return 1;
    }

    @Override
    public int insertUSer( UUID userUID , User user) {
        database.put(userUID , user);
        return 1;
    }
}
