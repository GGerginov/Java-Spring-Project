package project.demo.service;

import project.demo.domain.entities.Car;
import project.demo.service.models.CarServiceModel;
import project.demo.service.models.MotorcycleServiceModel;

import java.util.List;

public interface MotorcycleService {

    void publish(MotorcycleServiceModel motorcycleServiceModel);

    List<MotorcycleServiceModel> getAllByManufacturerAndModel(String manufacturer, String model);

    MotorcycleServiceModel getById(String id);

    void delete(MotorcycleServiceModel carServiceModel);


}
