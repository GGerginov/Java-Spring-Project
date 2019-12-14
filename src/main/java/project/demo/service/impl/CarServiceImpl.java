package project.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.demo.domain.entities.Car;
import project.demo.domain.entities.enums.Fuel;
import project.demo.domain.entities.enums.Status;
import project.demo.repositories.CarRepository;
import project.demo.service.CarService;
import project.demo.service.models.CarServiceModel;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final ModelMapper modelMapper;

    private final CarRepository carRepository;

    public CarServiceImpl(ModelMapper modelMapper, CarRepository carRepository) {
        this.modelMapper = modelMapper;
        this.carRepository = carRepository;
    }


    @Override
    public List<CarServiceModel> findAllByBrandAndModelAndFuelAndStatusAndYear(String brand, String model, String fuel, String status, Integer year) {
        return (carRepository
                .findAllByManufacturerAndModelAndFuelAndStatusAndYear(brand,model,Fuel.valueOf(fuel),Status.valueOf(status),year)
                .stream()
                .map(car -> this.modelMapper.map(car,CarServiceModel.class))
                .collect(Collectors.toList()));
    }

    @Override
    public void publish(CarServiceModel carServiceModel) {

        this.carRepository.saveAndFlush(this.modelMapper.map(carServiceModel, Car.class));
    }

    @Override
    public List<CarServiceModel> getAllBy() {
        return this.carRepository.getAllBy().stream().map(car -> this.modelMapper.map(car,CarServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public List<CarServiceModel> getAllByManufacturerAndModel(String manufacturer, String model) {
        return this.carRepository.getAllByManufacturerAndModel(manufacturer,model)
                .stream().map(car -> this.modelMapper.map(car,CarServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public CarServiceModel getById(String id) {
        return this.modelMapper.map(this.carRepository.getById(id),CarServiceModel.class);
    }

    @Override
    public void delete(CarServiceModel carServiceModel) {
        this.carRepository.delete(this.modelMapper.map(carServiceModel,Car.class));
    }
}
