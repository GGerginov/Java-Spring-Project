package project.demo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.demo.domain.entities.User;
import project.demo.repositories.UserRepository;
import project.demo.service.UserService;
import project.demo.service.models.UserServiceModel;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }


    @Override
    public UserServiceModel getByUserNameAndPassword(String username, String password) {
       return this.modelMapper.map(this.userRepository.getByUsernameAndPassword(username,password),UserServiceModel.class);
    }

    @Override
    public void register(UserServiceModel model) {
        this.userRepository.saveAndFlush(this.modelMapper.map(model, User.class));
    }
}
