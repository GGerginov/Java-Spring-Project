package project.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.demo.domain.entities.Bus;
import project.demo.domain.entities.Car;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus,String> {

    List<Bus> getAllByManufacturerAndModel(String manufacturer, String model);

    Bus getById(String id);

    @Override
    void delete(Bus bus);

    List<Bus> getAll();

    @Override
    <S extends Bus> S saveAndFlush(S s);
}
