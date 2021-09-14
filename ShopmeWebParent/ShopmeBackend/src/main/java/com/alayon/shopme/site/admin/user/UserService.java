package com.alayon.shopme.site.admin.user;

import com.alayon.shopme.common.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> listAll(){
        return (List<User>) userRepository.findAll();
    }
}
