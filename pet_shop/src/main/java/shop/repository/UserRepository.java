package shop.repository;

import org.springframework.data.repository.CrudRepository;

import shop.model.User;

public interface UserRepository extends CrudRepository<User,Integer>{
    User findByUsername(String username);
}
