package project.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.demo.domain.entities.Car;
import project.demo.domain.entities.Motorcycle;
import project.demo.domain.entities.Truck;
import project.demo.domain.entities.enums.Fuel;
import project.demo.domain.entities.enums.Status;

import java.util.List;

@Repository
public interface TruckRepository extends JpaRepository<Truck,String> {

    List<Truck> getAllByManufacturerAndModel(String manufacturer,String model);

    Truck getById(String id);

    @Override
    void delete(Truck truck);

    List<Truck> getAllBy();

}
