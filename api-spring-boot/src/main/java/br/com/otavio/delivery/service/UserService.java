package br.com.otavio.delivery.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.otavio.delivery.model.User;
import br.com.otavio.delivery.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	private static Logger userLLogger = LogManager.getLogger(User.class);

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user.get();
	}
	
	public User findByName(User user) {
		Optional<User> userFound = userRepository.findByName(user);
		return userFound.get();
	}
	
	private void validateRegisterUser(User user) {
		Optional<User> userFound = userRepository.findByName(user);
		if (userFound.isPresent()) {
			userLLogger.info("Já cadastrado");
		}
	}

	public User save(User user) {
		validateRegisterUser(user);
		return userRepository.save(user);
	}
}
