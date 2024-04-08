package ru.dallari.db.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dallari.db.entity.Computer;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComputerRepository extends CrudRepository<Computer, Long> {
    List<Computer> findComputerByIP(String name);
}
