package project.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.demo.domain.entities.Truck;
import project.demo.domain.entities.enums.Fuel;
import project.demo.domain.entities.enums.Status;
import project.demo.repositories.CarRepository;
import project.demo.repositories.TruckRepository;
import project.demo.service.CarService;
import project.demo.service.TruckService;
import project.demo.service.models.CarServiceModel;
import project.demo.service.models.TruckServiceModel;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TruckServiceImpl implements TruckService {

    private final ModelMapper modelMapper;

    private final TruckRepository truckRepository;

    public TruckServiceImpl(ModelMapper modelMapper, TruckRepository truckRepository) {
        this.modelMapper = modelMapper;
        this.truckRepository = truckRepository;
    }

    @Override
    public void publish(TruckServiceModel truckServiceModel) {
        this.truckRepository.saveAndFlush(this.modelMapper.map(truckServiceModel, Truck.class));
    }

    @Override
    public List<TruckServiceModel> getAllByManufacturerAndModel(String manufacturer, String model) {
        return this.truckRepository.getAllByManufacturerAndModel(manufacturer,model)
                .stream().map(truck -> this.modelMapper.map(truck,TruckServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public TruckServiceModel getById(String id) {
        return this.modelMapper.map(this.truckRepository.getById(id),TruckServiceModel.class);
    }

    @Override
    public void delete(TruckServiceModel truck) {
        this.truckRepository.delete(this.modelMapper.map(truck,Truck.class));
    }


}
