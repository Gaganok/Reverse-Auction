package ie.cit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.cit.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String email);
}