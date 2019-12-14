package project.demo.service;

import project.demo.domain.entities.Car;
import project.demo.domain.entities.enums.Fuel;
import project.demo.domain.entities.enums.Status;
import project.demo.service.models.CarServiceModel;

import java.util.List;

public interface CarService {

    List<CarServiceModel> findAllByBrandAndModelAndFuelAndStatusAndYear(String brand, String model, String fuel, String status, Integer year);

    void publish(CarServiceModel carServiceModel);

    List<CarServiceModel> getAllBy();

    List<CarServiceModel> getAllByManufacturerAndModel(String manufacturer, String model);

    CarServiceModel getById(String id);

    void delete(CarServiceModel carServiceModel);

}
