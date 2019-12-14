package project.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.demo.domain.entities.Car;
import project.demo.domain.entities.enums.Fuel;
import project.demo.domain.entities.enums.Status;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,String> {

    List<Car> findAllByManufacturerAndModelAndFuelAndStatusAndYear(String brand, String model, Fuel fuel, Status status, Integer year);

    List<Car> getAllBy();

    List<Car> getAllByManufacturerAndModel(String manufacturer,String model);

    Car getById(String id);

    @Override
    void delete(Car car);
}
