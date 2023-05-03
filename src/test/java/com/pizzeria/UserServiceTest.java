package com.pizzeria;

import com.pizzeria.entity.classes.Address;
import com.pizzeria.entity.classes.User;
import com.pizzeria.repositories.AddressRepository;
import com.pizzeria.repositories.UserRepository;
import com.pizzeria.services.AddressService;
import com.pizzeria.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private AddressRepository addressRepository;
    @InjectMocks
    UserService us = new UserService(userRepository);
    @InjectMocks
    AddressService as = new AddressService(addressRepository, userRepository);
    @Test
    void addUser() {
        User u = new User();
        u.setFirstName("test_first_name");
        u.setLastName("test_last_name");
        u.setEmail("test@mail.ru");
        u.setPassword("test_pass");
        ResponseEntity<User> resp = us.addUser(u);
        when(userRepository.findAll()).thenReturn(List.of(u));
        UserService us = new UserService(userRepository);
        Assertions.assertEquals(resp, ResponseEntity.status(HttpStatus.CREATED));
        Assertions.assertEquals(1, us.getAllUsers().size());
        Assertions.assertEquals("test@mail.ru", us.getAllUsers().get(0).getEmail());
    }

    @Test
    void addAddress() {
        Address a = new Address();
        a.setCountry("Russia");
        a.setRegion("MO");
        a.setCity("Moskva");
        a.setStreet("test_street");
        a.setHouse(1);
    }

//	@Test
//	void getUsers() {
//	}
}
