package project.demo.service;

import project.demo.domain.entities.User;
import project.demo.service.models.UserServiceModel;

public interface UserService {

    UserServiceModel getByUserNameAndPassword(String username, String password);

    void register(UserServiceModel model);
}
