package ru.dallari.db.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dallari.db.entity.PrinterModel;

import java.util.List;

@Repository
public interface PrinterModelRepository extends CrudRepository<PrinterModel, Long> {
    List<PrinterModel> findPrinterModelByName(String name);
}
