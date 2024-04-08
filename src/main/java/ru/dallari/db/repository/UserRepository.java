package ru.dallari.db.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dallari.db.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
