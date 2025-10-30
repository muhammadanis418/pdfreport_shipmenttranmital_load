package com.example.shipment_transmital_load_pdf.service;

import com.example.shipment_transmital_load_pdf.entity.User;
import com.example.shipment_transmital_load_pdf.entity.UserRole;
import com.example.shipment_transmital_load_pdf.repository.RoleRepository;
import com.example.shipment_transmital_load_pdf.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public String save(User user) {
//        UserRole userRole=user.getRole();
//        userRole.setRole(userRole.getRole());
//        user.setRole(userRole);
        userRepository.save(user);
        return "User save successfully";
    }

    public List<User> findAll(int pageNo, int pageSize){
        Sort sort= Sort.by(Sort.Direction.ASC, "userName","email");
        Pageable pageable = PageRequest.of(pageNo-1, pageSize,sort);
        return userRepository.findAll(pageable).getContent();
    }

    public User findByIdAndEmail(int id, String email){
        return userRepository.findByIdAndEmail(id,email);
    }
@Transactional
    public String delete(int id){
      //  if(roleRepository.existsById(id)) {
       //     roleRepository.deleteById(id);
       // }
        userRepository.deleteById(id);
        return "User with id "+ id +" is deleted successfully";
    }
}
