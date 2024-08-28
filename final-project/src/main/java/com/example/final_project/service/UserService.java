package com.example.final_project.service;

import com.example.final_project.createuser.CreateUser;
import com.example.final_project.model.Role;
import com.example.final_project.model.User;
import com.example.final_project.repository.RoleRepository;
import com.example.final_project.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(CreateUser user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());

//         roleRepository.findByName(user.getRole()).get();
        Optional<Role> role = roleRepository.findById(Long.parseLong(user.getRoles()));
        // Pretpostavljamo da je user.getRole() ime rola, pretraga u bazi ili kreiranje novog
        Set<Role> roles = Set.of(role.get());
        newUser.setRoles(roles);

        return userRepository.save(newUser);
    }

    public User updateUser(Long id, CreateUser userDetails) {
        User user = userRepository.findById(id).get();
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());

        // Ažuriranje kolekcije rola
        Optional<Role> role = roleRepository.findById(Long.parseLong(userDetails.getRoles()));
        // Pretpostavljamo da je user.getRole() ime rola, pretraga u bazi ili kreiranje novog
        Set<Role> roles = new HashSet<>((Collection) role.get());
        user.setRoles(roles);
        return userRepository.save(user);

    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
