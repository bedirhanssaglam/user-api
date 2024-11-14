package com.example.demo;

import com.example.demo.models.ResponseWrapper;
import com.example.demo.controller.UserController;
import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.constants.ErrorMessages;
import com.example.demo.utils.constants.SuccessMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserControllerTest {

	@MockBean
	private UserRepository UserRepository;

	@InjectMocks
	private UserController userController;

	private User user;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		user = new User();
		user.setEmail("testuser@example.com");
		user.setName("Test User");
	}

	@Test
	public void testCreateUser_Success() {
		// Mocking the userRepository to save user and return the saved user
		when(UserRepository.save(any(User.class))).thenReturn(user);

		ResponseEntity<ResponseWrapper<User>> response = userController.createUser(user);

		assertEquals(SuccessMessages.USER_CREATED, Objects.requireNonNull(response.getBody()).getMessage());
	}

	@Test
	public void testCreateUser_InvalidEmail() {
		user.setEmail("invalid-email");

		ResponseEntity<ResponseWrapper<User>> response = userController.createUser(user);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	public void testGetAllUsers_Success() {
		when(UserRepository.findAll()).thenReturn(Collections.singletonList(user));

		ResponseEntity<ResponseWrapper<List<User>>> response = userController.getAllUsers();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(Objects.requireNonNull(response.getBody()).getData());
	}

	@Test
	public void testGetAllUsers_EmptyList() {
		when(UserRepository.findAll()).thenReturn(Collections.emptyList());

		ResponseEntity<ResponseWrapper<List<User>>> response = userController.getAllUsers();

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(ErrorMessages.USERS_NOT_FOUND, Objects.requireNonNull(response.getBody()).getMessage());
	}

	@Test
	public void testGetUserById_Success() {
		when(UserRepository.findById("1")).thenReturn(Optional.of(user));

		ResponseEntity<ResponseWrapper<User>> response = userController.getUserById("1");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody().getData());
	}

	@Test
	public void testGetUserById_NotFound() {
		when(UserRepository.findById("1")).thenReturn(Optional.empty());

		ResponseEntity<ResponseWrapper<User>> response = userController.getUserById("1");

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(ErrorMessages.USER_NOT_FOUND, Objects.requireNonNull(response.getBody()).getMessage());
	}

	@Test
	public void testDeleteUser_Success() {
		when(UserRepository.findById("1")).thenReturn(Optional.of(user));

		ResponseEntity<ResponseWrapper<Void>> response = userController.deleteUserById("1");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(SuccessMessages.USER_DELETED, Objects.requireNonNull(response.getBody()).getMessage());
	}

	@Test
	public void testDeleteUser_NotFound() {
		when(UserRepository.findById("1")).thenReturn(Optional.empty());

		ResponseEntity<ResponseWrapper<Void>> response = userController.deleteUserById("1");

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(ErrorMessages.USER_NOT_FOUND, Objects.requireNonNull(response.getBody()).getMessage());
	}

	@Test
	public void testUpdateUser_Success() {
		User updatedUser = new User();
		when(UserRepository.findById("1")).thenReturn(Optional.of(user));
		when(UserRepository.save(any(User.class))).thenReturn(updatedUser);

		ResponseEntity<ResponseWrapper<User>> response = userController.updateUser("1", updatedUser);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(SuccessMessages.USER_UPDATED, Objects.requireNonNull(response.getBody()).getMessage());
	}

	@Test
	public void testUpdateUser_NotFound() {
		when(UserRepository.findById("1")).thenReturn(Optional.empty());

		ResponseEntity<ResponseWrapper<User>> response = userController.updateUser("1", user);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(ErrorMessages.USER_NOT_FOUND, Objects.requireNonNull(response.getBody()).getMessage());
	}
}
