package project.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.demo.domain.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User getByUsernameAndPassword(String username,String password);


}
