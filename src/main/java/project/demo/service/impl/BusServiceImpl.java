package project.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.demo.domain.entities.Bus;
import project.demo.repositories.BusRepository;
import project.demo.service.BusService;
import project.demo.service.models.BusServiceModel;
import project.demo.service.models.CarServiceModel;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusServiceImpl implements BusService {

    private final BusRepository busRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public BusServiceImpl(BusRepository busRepository, ModelMapper modelMapper) {
        this.busRepository = busRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void publish(BusServiceModel busServiceModel) {
        this.busRepository.saveAndFlush(this.modelMapper.map(busServiceModel, Bus.class));
    }
    @Override
    public List<BusServiceModel> getAllByManufacturerAndModel(String manufacturer, String model) {
        return this.busRepository.getAllByManufacturerAndModel(manufacturer,model)
                .stream().map(bus -> this.modelMapper.map(bus,BusServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public BusServiceModel getById(String id) {
        return this.modelMapper.map(this.busRepository.getById(id),BusServiceModel.class);
    }

    @Override
    public void delete(BusServiceModel busServiceModel) {
        this.busRepository.delete(this.modelMapper.map(busServiceModel,Bus.class));
    }

    @Override
    public List<BusServiceModel> getAllBy() {
        return this.busRepository.getAll().stream().map(bus -> this.modelMapper.map(bus,BusServiceModel.class)).collect(Collectors.toList());
    }
}
