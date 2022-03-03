package com.spring.boot.crud.service;

import com.spring.boot.crud.dao.FakeDataDao;
import com.spring.boot.crud.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.spring.boot.crud.model.User.Gender.MAMLE;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;


class UserServiceTest {

    @Mock
    private FakeDataDao fakeDataDao;
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(fakeDataDao);
    }

    @Test
    void getAllUsers() {
        UUID randomUUID = UUID.randomUUID();
        User yassine = new User(randomUUID,
                "yassine", "bouzar", MAMLE,
                24, "yassine@gmail.com");
        List<User> userImmutableList = List.of(
                yassine
        );
        given(fakeDataDao.selectAllUsers()).willReturn(userImmutableList);
        List<User> allUsers = userService.getAllUsers();

        assertThat(allUsers).hasSize(1);
        User user = allUsers.get(0);
        assertThat(user.getFirstName()).isEqualTo("yassine");
        assertThat(user.getLastName()).isEqualTo("bouzar");
        assertThat(user.getAge()).isEqualTo(24);
        assertThat(user.getGender()).isEqualTo(MAMLE);
        assertThat(user.getEmail()).isEqualTo("yassine@gmail.com");
        assertThat(user.getUserUid()).isNotNull();
    }

    @Test
    void getUsers() {
        UUID yassineUuid = UUID.randomUUID();
        User yassine = new User(yassineUuid,
                "yassine", "bouzar", MAMLE,
                24, "yassine@gmail.com");
        given(fakeDataDao.selectUsers(yassineUuid)).willReturn(Optional.of(yassine));
        Optional<User> userOptional = userService.getUsers(yassineUuid);
        assertThat(userOptional.isPresent()).isTrue();
        User user = userOptional.get();
        assertThat(user.getFirstName()).isEqualTo("yassine");
        assertThat(user.getLastName()).isEqualTo("bouzar");
        assertThat(user.getAge()).isEqualTo(24);
        assertThat(user.getGender()).isEqualTo(MAMLE);
        assertThat(user.getEmail()).isEqualTo("yassine@gmail.com");
        assertThat(user.getUserUid()).isNotNull();
    }

    @Test
    void updateUSer() {
    }

    @Test
    void removeUSer() {
    }

    @Test
    void insertUSer() {
    }
}