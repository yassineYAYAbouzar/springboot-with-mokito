package com.spring.boot.crud.dao;

import com.spring.boot.crud.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.spring.boot.crud.model.User.Gender.MAMLE;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


class FakeDataDaoTest {

    private FakeDataDao fakeDataDao;

    @BeforeEach
    void setUp() {
        fakeDataDao = new FakeDataDao();
    }

    @Test
    void shouldSelectAllUsers() {
        List<User> users = fakeDataDao.selectAllUsers();
        assertThat(users).hasSize(1);
        User user = users.get(0);
        assertThat(user.getFirstName()).isEqualTo("yassine");
        assertThat(user.getLastName()).isEqualTo("bouzar");
        assertThat(user.getAge()).isEqualTo(24);
        assertThat(user.getGender()).isEqualTo(MAMLE);
        assertThat(user.getEmail()).isEqualTo("yassine@gmail.com");
        assertThat(user.getUserUID()).isNotNull();
    }

    @Test
    void shouldSelectUsers() {
        UUID ayoubUuid = UUID.randomUUID();
        User ayoub = new User(ayoubUuid, "ayoub", "bouzar"  ,MAMLE, 20,"ayoub@gmail.com");
        fakeDataDao.insertUSer(ayoubUuid, ayoub);
        assertThat(fakeDataDao.selectAllUsers()).hasSize(2);
        Optional<User> ayoubOptional = fakeDataDao.selectUsers(ayoubUuid);
        assertThat(ayoubOptional.isPresent()).isTrue();
        assertThat(ayoubOptional.get()).isEqualToComparingFieldByField(ayoub);
    }

    @Test
    void shouldUpdateUSer() {
        UUID yassineUuid = fakeDataDao.selectAllUsers().get(0).getUserUID();
        User hamza = new User(yassineUuid , "hamza", "bouzar", MAMLE, 22 , "hamza@gmal.com");
        fakeDataDao.updateUSer(hamza);
        Optional<User> user = fakeDataDao.selectUsers(yassineUuid);
        assertThat(user.isPresent()).isTrue();
        assertThat(fakeDataDao.selectAllUsers()).hasSize(1);
        assertThat(user.get()).isEqualToComparingFieldByField(hamza);
    }

    @Test
    void shouldDeleteUSer() {
        UUID yassineUid = fakeDataDao.selectAllUsers().get(0).getUserUID();
        fakeDataDao.deleteUSer(yassineUid);
        assertThat(fakeDataDao.selectAllUsers()).isEmpty();
        assertThat(fakeDataDao.selectUsers(yassineUid).isPresent()).isFalse();
    }

    @Test
    void insertUSer() {
        UUID userUid = UUID.randomUUID();
        User ayoub = new User(userUid, "ayoub", "bouzar"  ,MAMLE, 20,"ayoub@gmail.com");
        fakeDataDao.insertUSer(userUid, ayoub);
        List<User> users = fakeDataDao.selectAllUsers();
        assertThat(users).hasSize(2);
        assertThat(fakeDataDao.selectUsers(userUid)).get().isEqualToComparingFieldByField(ayoub);

    }
}