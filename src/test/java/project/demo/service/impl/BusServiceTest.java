package project.demo.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import project.demo.domain.entities.Bus;
import project.demo.domain.entities.enums.Fuel;
import project.demo.domain.entities.enums.Status;
import project.demo.repositories.BusRepository;
import project.demo.service.BusService;
import project.demo.service.models.BusServiceModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BusServiceTest {

    @MockBean
    BusRepository busRepository;

    @MockBean
    ModelMapper modelMapper;

    @MockBean
    BusServiceImpl busService;


    @Before
    public void initTests() {
//        busServiceModel = new BusServiceModel();
//        busServiceModel.setManufacturer("Vw");
//        busServiceModel.setModel("LT35");
//        busServiceModel.setPrice(new BigDecimal("2000"));
//        busServiceModel.setTitle("dadads");
//        busServiceModel.setDescription("dsadas");
//        busServiceModel.setLocation("sadas");
//        busServiceModel.setStatus(Status.USED);
//        busServiceModel.setFuel(Fuel.DIESEL);
//        busServiceModel.setId("dadkjasdhjka");
//        busServiceModel.setYear(2555);
//        busServiceModel.setMillage(5644465);
        busRepository = Mockito.mock(BusRepository.class);
        modelMapper = new ModelMapper();
        busService = new BusServiceImpl(busRepository,modelMapper);
    }

    @Test
    public void publish() {


    }

    @Test
    public void getAllByManufacturerAndModel() {

    }

    @Test
    public void getById() {
    }

    @Test
    public void delete() {
    }
}