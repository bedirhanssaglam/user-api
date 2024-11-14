package com.example.demo;

import com.example.demo.models.ResponseWrapper;
import com.example.demo.models.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.constants.ErrorMessages;
import com.example.demo.utils.constants.SuccessMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

	@Mock
	private UserService userService;

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
		when(userService.createUser(any(User.class)))
				.thenReturn(ResponseWrapper.success(user, SuccessMessages.USER_CREATED));

		ResponseWrapper<User> response = userService.createUser(user);

		assertEquals(SuccessMessages.USER_CREATED, response.getMessage());
		assertNotNull(response.getData());
	}

	@Test
	public void testCreateUser_InvalidEmail() {
		user.setEmail("invalid-email");
		when(userService.createUser(user))
				.thenReturn(ResponseWrapper.error(HttpStatus.BAD_REQUEST.value(), ErrorMessages.INVALID_EMAIL));

		ResponseWrapper<User> response = userService.createUser(user);

		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getCode());
		assertEquals(ErrorMessages.INVALID_EMAIL, response.getMessage());
	}

	@Test
	public void testGetAllUsers_Success() {
		when(userService.getAllUsers())
				.thenReturn(ResponseWrapper.success(Collections.singletonList(user)));

		ResponseWrapper<List<User>> response = userService.getAllUsers();

		assertEquals(HttpStatus.OK.value(), response.getCode());
		assertNotNull(response.getData());
	}

	@Test
	public void testGetAllUsers_EmptyList() {
		when(userService.getAllUsers())
				.thenReturn(ResponseWrapper.error(HttpStatus.NOT_FOUND.value(), ErrorMessages.USERS_NOT_FOUND));

		ResponseWrapper<List<User>> response = userService.getAllUsers();

		assertEquals(HttpStatus.NOT_FOUND.value(), response.getCode());
		assertEquals(ErrorMessages.USERS_NOT_FOUND, response.getMessage());
	}

	@Test
	public void testGetUserById_Success() {
		when(userService.getUserById("1"))
				.thenReturn(ResponseWrapper.success(user));

		ResponseWrapper<User> response = userService.getUserById("1");

		assertEquals(HttpStatus.OK.value(), response.getCode());
		assertNotNull(response.getData());
	}

	@Test
	public void testGetUserById_NotFound() {
		when(userService.getUserById("1"))
				.thenReturn(ResponseWrapper.error(HttpStatus.NOT_FOUND.value(), ErrorMessages.USER_NOT_FOUND));

		ResponseWrapper<User> response = userService.getUserById("1");

		assertEquals(HttpStatus.NOT_FOUND.value(), response.getCode());
		assertEquals(ErrorMessages.USER_NOT_FOUND, response.getMessage());
	}

	@Test
	public void testDeleteUser_Success() {
		when(userService.deleteUserById("1"))
				.thenReturn(ResponseWrapper.success(null, SuccessMessages.USER_DELETED));

		ResponseWrapper<Void> response = userService.deleteUserById("1");

		assertEquals(HttpStatus.OK.value(), response.getCode());
		assertEquals(SuccessMessages.USER_DELETED, response.getMessage());
	}

	@Test
	public void testDeleteUser_NotFound() {
		when(userService.deleteUserById("1"))
				.thenReturn(ResponseWrapper.error(HttpStatus.NOT_FOUND.value(), ErrorMessages.USER_NOT_FOUND));

		ResponseWrapper<Void> response = userService.deleteUserById("1");

		assertEquals(HttpStatus.NOT_FOUND.value(), response.getCode());
		assertEquals(ErrorMessages.USER_NOT_FOUND, response.getMessage());
	}

	@Test
	public void testUpdateUser_Success() {
		User updatedUser = new User();
		when(userService.updateUser("1", updatedUser))
				.thenReturn(ResponseWrapper.success(updatedUser, SuccessMessages.USER_UPDATED));

		ResponseWrapper<User> response = userService.updateUser("1", updatedUser);

		assertEquals(HttpStatus.OK.value(), response.getCode());
		assertEquals(SuccessMessages.USER_UPDATED, response.getMessage());
	}

	@Test
	public void testUpdateUser_NotFound() {
		when(userService.updateUser("1", user))
				.thenReturn(ResponseWrapper.error(HttpStatus.NOT_FOUND.value(), ErrorMessages.USER_NOT_FOUND));

		ResponseWrapper<User> response = userService.updateUser("1", user);

		assertEquals(HttpStatus.NOT_FOUND.value(), response.getCode());
		assertEquals(ErrorMessages.USER_NOT_FOUND, response.getMessage());
	}
}
