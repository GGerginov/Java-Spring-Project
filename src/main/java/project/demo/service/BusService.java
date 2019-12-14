package project.demo.service;

import project.demo.service.models.BusServiceModel;
import project.demo.service.models.CarServiceModel;

import java.util.List;

public interface BusService {

    void publish(BusServiceModel busServiceModel);

    List<BusServiceModel> getAllByManufacturerAndModel(String manufacturer, String model);

    BusServiceModel getById(String id);

    void delete(BusServiceModel busServiceModel);
}
