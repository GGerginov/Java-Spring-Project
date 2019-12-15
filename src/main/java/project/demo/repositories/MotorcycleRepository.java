package project.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.demo.domain.entities.Car;
import project.demo.domain.entities.Motorcycle;

import java.util.List;

@Repository
public interface MotorcycleRepository extends JpaRepository<Motorcycle,String> {

    List<Motorcycle> getAllByManufacturerAndModel(String manufacturer, String model);

    Motorcycle getById(String id);

    @Override
    void delete(Motorcycle motorcycle);


}
