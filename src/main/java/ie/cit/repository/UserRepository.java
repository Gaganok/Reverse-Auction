package ie.cit.repository;

import org.springframework.data.repository.CrudRepository;

import ie.cit.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
