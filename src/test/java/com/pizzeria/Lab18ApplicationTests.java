package com.pizzeria;

import com.pizzeria.entity.classes.User;
import com.pizzeria.repositories.AddressRepository;
import com.pizzeria.repositories.UserRepository;
import com.pizzeria.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;


import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class Lab18ApplicationTests {
	@Mock
	private UserRepository userRepository;
	@Mock
	private AddressRepository addressRepository;

	@Test
	void addUser() {
		User u = new User();
		u.setFirstName("test_first_name");
		u.setLastName("test_last_name");
		u.setEmail("test@mail.ru");
		u.setPassword("test_pass");
		UserService us = new UserService(userRepository);
		ResponseEntity<User> resp = us.addUser(u);
		Mockito.when(userRepository.findAll()).thenReturn(List.of(u));
		//Assertions.assertEquals(resp, ResponseEntity.status(HttpStatus.CREATED));
		Assertions.assertEquals(1, us.getAllUsers().size());
		Assertions.assertEquals("test@mail.ru", us.getAllUsers().get(0).getEmail());
	}


	@Test
	void contextLoads() {
	}
}
