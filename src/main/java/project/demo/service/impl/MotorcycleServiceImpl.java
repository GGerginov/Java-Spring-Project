package project.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.demo.domain.entities.Motorcycle;
import project.demo.repositories.MotorcycleRepository;
import project.demo.service.MotorcycleService;
import project.demo.service.models.CarServiceModel;
import project.demo.service.models.MotorcycleServiceModel;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MotorcycleServiceImpl implements MotorcycleService {

    private final MotorcycleRepository motorcycleRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public MotorcycleServiceImpl(MotorcycleRepository motorcycleRepository, ModelMapper modelMapper) {
        this.motorcycleRepository = motorcycleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void publish(MotorcycleServiceModel motorcycleServiceModel) {
        this.motorcycleRepository.saveAndFlush(this.modelMapper.map(motorcycleServiceModel, Motorcycle.class));
    }

    @Override
    public List<MotorcycleServiceModel> getAllByManufacturerAndModel(String manufacturer, String model) {
        return this.motorcycleRepository.getAllByManufacturerAndModel(manufacturer,model)
                .stream().map(motorcycle -> this.modelMapper.map(motorcycle,MotorcycleServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public MotorcycleServiceModel getById(String id) {
        return this.modelMapper.map(this.motorcycleRepository.getById(id),MotorcycleServiceModel.class);
    }

    @Override
    public void delete(MotorcycleServiceModel carServiceModel) {

    }
}
