package com.junebookstore;

import com.junebookstore.business.entity.UserEntity;
import com.junebookstore.business.repository.OrderRepository;
import com.junebookstore.business.repository.UserRepository;
import com.junebookstore.business.service.UserService;
import com.junebookstore.common.model.Register;
import com.junebookstore.common.wrapper.PasswordWrapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private PasswordWrapper passwordWrapper;

    @Before
    public void setUp() {
        userService = new UserService(userRepository, orderRepository, passwordWrapper);
    }

    @Test
    public void Register_AddDataIntoDB_UserId() {
        Register data = new Register(
                "john.doe",
                "thisismysecret",
                "01/01/1991",
                "john",
                "doe"
        );

        UserEntity entity = new UserEntity(
                data.getUsername(),
                passwordWrapper.encodePassword(data.getPassword()),
                data.getDateOfBirth(),
                data.getName(),
                data.getSurname()
        );

        UserEntity entityReturn = new UserEntity(
                1,
                entity.getUsername(),
                entity.getPassword(),
                entity.getDateOfBirth(),
                entity.getName(),
                entity.getSurname()
        );

        when(userRepository.save(entity)).thenReturn(entityReturn);

        int userId = userService.register(data);

        assertEquals(1, userId);
    }
}
