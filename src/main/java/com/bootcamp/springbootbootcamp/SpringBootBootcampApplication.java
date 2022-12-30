package com.bootcamp.springbootbootcamp;

import com.bootcamp.springbootbootcamp.entity.User;
import com.bootcamp.springbootbootcamp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringBootBootcampApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBootcampApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*userRepository.save(User.builder()
				.username("rahul")
				.password(passwordEncoder.encode("rahul"))
				.role("ROLE_USER")
				.build());
		userRepository.save(User.builder()
				.username("admin")
				.password(passwordEncoder.encode("admin"))
				.role("ROLE_ADMIN")
				.build());*/
	}
}
