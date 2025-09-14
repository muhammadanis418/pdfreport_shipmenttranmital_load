package com.example.shipment_transmital_load_pdf.service;

import com.example.shipment_transmital_load_pdf.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
