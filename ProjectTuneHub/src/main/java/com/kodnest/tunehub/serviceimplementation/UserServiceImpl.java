package com.kodnest.tunehub.serviceimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.repository.UserRepository;
import com.kodnest.tunehub.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	public String addUser(User user) {
		userRepository.save(user);
		return "user added successfully";

	}

	// to check multiple email entry
	public boolean emailExists(String email) {
		if (userRepository.findByEmail(email) != null) {
//			System.out.println("present");
			return true;
		} else {
//			System.out.println("absent");
			return false;
		}

	}

	public boolean validUser(String email, String password) {
		User user = userRepository.findByEmail(email);
		String dbpwd = user.getPassword();
		if (password.equals(dbpwd)) {
			return true;
		} else {
			return false;
		}
	}

	public String getRole(String email) {
		User user = userRepository.findByEmail(email);

		return user.getRole();
	}
	@Override
	public User getUser(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void updateUser(User user) {
		userRepository.save(user);
		
	}

}
