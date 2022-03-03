package com.spring.boot.crud.service;

import com.spring.boot.crud.dao.FakeDataDao;
import com.spring.boot.crud.model.User;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.spring.boot.crud.model.User.Gender.MAMLE;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


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
        UUID yassineUuid = UUID.randomUUID();
        User yassine = new User(yassineUuid,
                "yassine", "bouzar", MAMLE,
                24, "yassine@gmail.com");
        given(fakeDataDao.selectUsers(yassineUuid)).willReturn(Optional.of(yassine));
        given(fakeDataDao.updateUSer(yassine)).willReturn(1);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        int updateUSer = userService.updateUSer(yassine);

        verify(fakeDataDao).selectUsers(yassineUuid);
        verify(fakeDataDao).updateUSer(captor.capture());

        User user = captor.getValue();

        assertThat(user.getFirstName()).isEqualTo("yassine");
        assertThat(user.getLastName()).isEqualTo("bouzar");
        assertThat(user.getAge()).isEqualTo(24);
        assertThat(user.getGender()).isEqualTo(MAMLE);
        assertThat(user.getEmail()).isEqualTo("yassine@gmail.com");
        assertThat(user.getUserUid()).isNotNull();
        assertThat(updateUSer).isEqualTo(1);
    }

    @Test
    void removeUSer() {
        UUID yassineUuid = UUID.randomUUID();
        User yassine = new User(yassineUuid,
                "yassine", "bouzar", MAMLE,
                24, "yassine@gmail.com");
        given(fakeDataDao.selectUsers(yassineUuid)).willReturn(Optional.of(yassine));
        given(fakeDataDao.deleteUSer(yassineUuid)).willReturn(1);

        int deleteUser = userService.updateUSer(yassine);

        verify(fakeDataDao).selectUsers(yassineUuid);

        assertThat(deleteUser).isEqualTo(1);

    }

    @Test
    void insertUSer() {
        UUID yassineUuid = UUID.randomUUID();
        User yassine = new User(yassineUuid,
                "yassine", "bouzar", MAMLE,
                24, "yassine@gmail.com");
        given(fakeDataDao.insertUSer(any(UUID.class),eq(yassine))).willReturn(1);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        int insertUSer = userService.insertUSer(yassine);
        verify(fakeDataDao).insertUSer(any(UUID.class), captor.capture());
        User user = captor.getValue();
        assertThat(user.getFirstName()).isEqualTo("yassine");
        assertThat(user.getLastName()).isEqualTo("bouzar");
        assertThat(user.getAge()).isEqualTo(24);
        assertThat(user.getGender()).isEqualTo(MAMLE);
        assertThat(user.getEmail()).isEqualTo("yassine@gmail.com");
        assertThat(user.getUserUid()).isNotNull();
        assertThat(insertUSer).isEqualTo(1);
    }
}