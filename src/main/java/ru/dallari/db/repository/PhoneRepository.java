package ru.dallari.db.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dallari.db.entity.Phone;

import java.util.List;

@Repository
public interface PhoneRepository extends CrudRepository<Phone, Long> {
    List<Phone> findPhoneByIP(String name);

}
