package vn.edu.hcmuaf.freshshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.freshshop.model.User;
import vn.edu.hcmuaf.freshshop.repository.UserRepository;

import java.sql.SQLException;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public void register(String username, String password, String lastname, String firstname, String phone, String email, String address, String deliveryAddress, int statusId) {
        User user = userRepository.findByUsername(username);
        if (user == null)
            user = new User(username, password, firstname, lastname, phone, email, address, deliveryAddress, statusId);
            userRepository.save(user);
    }
}
