package project.demo.service;

import project.demo.domain.entities.Car;
import project.demo.domain.entities.Truck;
import project.demo.service.models.TruckServiceModel;

import java.util.List;

public interface TruckService {

    void publish(TruckServiceModel truckServiceModel);

    List<TruckServiceModel> getAllByManufacturerAndModel(String manufacturer, String model);

    TruckServiceModel getById(String id);

    void delete(TruckServiceModel truck);
}
