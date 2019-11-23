package ie.cit.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.cit.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(String name);
}
