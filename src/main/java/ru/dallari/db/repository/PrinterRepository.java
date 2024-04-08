package ru.dallari.db.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dallari.db.entity.Printer;

import java.util.List;

@Repository
public interface PrinterRepository extends CrudRepository<Printer, Long> {
    List<Printer> findPrinterByIP(String name);

}
