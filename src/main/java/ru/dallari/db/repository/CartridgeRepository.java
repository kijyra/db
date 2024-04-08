package ru.dallari.db.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dallari.db.entity.Cartridge;

import java.util.List;

@Repository
public interface CartridgeRepository extends CrudRepository<Cartridge, Long> {
    List<Cartridge> findCartridgeByName(String name);
}
